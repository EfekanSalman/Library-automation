package com.efekansalman.Library.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Admin;
import com.efekansalman.Library.Entity.Lending;
import com.efekansalman.Library.Entity.Report;
import com.efekansalman.Library.Entity.ReportType;
import com.efekansalman.Library.exception.InvalidRequestException;
import com.efekansalman.Library.exception.ResourceNotFoundException;
import com.efekansalman.Library.repository.AdminRepository;
import com.efekansalman.Library.repository.LendingRepository;
import com.efekansalman.Library.repository.ReportRepository;
import com.efekansalman.Library.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final AdminRepository adminRepository;
    private final LendingRepository lendingRepository;

    public ReportServiceImpl(ReportRepository reportRepository, AdminRepository adminRepository,
			LendingRepository lendingRepository) {
		super();
		this.reportRepository = reportRepository;
		this.adminRepository = adminRepository;
		this.lendingRepository = lendingRepository;
	}

	@Override
    public Report generateReport(Long adminId, ReportType type) {
        // Find the admin
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));

        // Create a new report
        Report report = new Report();
        report.setAdmin(admin);
        report.setType(type);
        report.setGeneratedDate(new Date());

        // Generate report content based on type
        String content;
        switch (type) {
            case MOST_BORROWED:
                // Fetch all lendings and group by book, then sort by frequency
                List<Lending> allLendings = lendingRepository.findAll();
                Map<String, Long> bookBorrowCount = allLendings.stream()
                    .collect(Collectors.groupingBy(
                        lending -> lending.getBook().getTitle(),
                        Collectors.counting()
                    ));
                List<String> mostBorrowedBooks = bookBorrowCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(5) // Top 5 most borrowed books
                    .map(entry -> entry.getKey() + ": " + entry.getValue() + " times")
                    .collect(Collectors.toList());
                content = "Most borrowed books:\n" + String.join("\n", mostBorrowedBooks);
                break;

            case OVERDUE_USERS:
                // Fetch overdue lendings with pagination and detailed info
                Pageable pageable = PageRequest.of(0, 10); // First 10 overdue users
                List<Lending> overdueLendings = lendingRepository.findByReturnDateIsNullAndDueDateBefore(new Date(), pageable);
                List<String> overdueDetails = overdueLendings.stream()
                    .map(lending -> String.format(
                        "%s (Book: %s, Due: %s)",
                        lending.getCustomer().getUsername(),
                        lending.getBook().getTitle(),
                        lending.getDueDate().toString()
                    ))
                    .collect(Collectors.toList());
                content = "Overdue users:\n" + String.join("\n", overdueDetails);
                break;

            default:
                throw new InvalidRequestException("Unsupported report type: " + type);
        }
        report.setContent(content);

        // Save and return the report
        return reportRepository.save(report);
    }

    @Override
    public List<Report> findReportsByAdmin(Long adminId) {
        // Find the admin
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        // Return all reports by the admin, sorted by generatedDate (descending)
        List<Report> reports = reportRepository.findByAdmin(admin);
        reports.sort(Comparator.comparing(Report::getGeneratedDate).reversed());
        return reports;
    }

    @Override
    public List<Report> findReportsByType(ReportType type) {
        // Validate ReportType input
        if (type == null) {
            throw new InvalidRequestException("Report type cannot be null");
        }
        // Return all reports of the specified type with pagination
        Pageable pageable = PageRequest.of(0, 50); // First 50 reports
        return reportRepository.findByType(type, pageable);
    }
}