package com.efekansalman.Library.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efekansalman.Library.Entity.Admin;
import com.efekansalman.Library.Entity.Report;
import com.efekansalman.Library.Entity.ReportType;
import com.efekansalman.Library.repository.AdminRepository;
import com.efekansalman.Library.repository.LendingRepository;
import com.efekansalman.Library.repository.ReportRepository;
import com.efekansalman.Library.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private LendingRepository lendingRepository;
	
    @Override
    public Report generateReport(Long adminId, ReportType type) {
        // Find the admin
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + adminId));

        // Create a new report
        Report report = new Report();
        report.setAdmin(admin);
        report.setType(type);
        report.setGenerateDate(new Date());

        // Generate report content based on type
        String content;
        switch (type) {
            case MOST_BORROWED:
                content = "Report for most borrowed books (placeholder)";
                // TODO: Logic to fetch most borrowed books
                break;
            case OVERDUE_USERS:
                List<String> overdueUsers = lendingRepository.findByReturnDateIsNullAndDueDateBefore(new Date())
                    .stream()
                    .map(lending -> lending.getCustomer().getUsername())
                    .toList();
                content = "Overdue users: " + String.join(", ", overdueUsers);
                break;
            default:
                content = "Unknown report type";
        }
        report.setContent(content);

        // Save and return the report
        return reportRepository.save(report);
    }

	@Override
	public List<Report> findReportsByAdmin(Long adminId) {
        // Find the admin
        Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + adminId));
        // Return all reports by the admin
        return reportRepository.findByAdmin(admin);
	}

	@Override
	public List<Report> findReportsByType(ReportType type) {
        // Return all reports of the specified type
        return reportRepository.findByType(type);
	}

}
