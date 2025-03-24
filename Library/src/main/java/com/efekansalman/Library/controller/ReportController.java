package com.efekansalman.Library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efekansalman.Library.Entity.Report;
import com.efekansalman.Library.Entity.ReportType;
import com.efekansalman.Library.dto.ReportDTO;
import com.efekansalman.Library.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}

	@PostMapping
    public ResponseEntity<ReportDTO> generateReport(@RequestParam Long adminId, @RequestParam ReportType type) {
        // Generate a report and convert to DTO
        Report report = reportService.generateReport(adminId, type);
        ReportDTO reportDTO = convertToDTO(report);
        return ResponseEntity.ok(reportDTO);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<ReportDTO>> findReportsByAdmin(@PathVariable Long adminId) {
        // Find reports by admin and convert to DTO
        List<Report> reports = reportService.findReportsByAdmin(adminId);
        List<ReportDTO> reportDTOs = reports.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOs);
    }

    @GetMapping("/type")
    public ResponseEntity<List<ReportDTO>> findReportsByType(@RequestParam ReportType type) {
        // Find reports by type and convert to DTO
        List<Report> reports = reportService.findReportsByType(type);
        List<ReportDTO> reportDTOs = reports.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOs);
    }

    // Helper method to convert Report entity to ReportDTO
    private ReportDTO convertToDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setId(report.getId());
        dto.setType(report.getType());
        dto.setGeneratedDate(report.getGeneratedDate());
        dto.setContent(report.getContent());
        dto.setAdminId(report.getAdmin().getId());
        dto.setAdminUsername(report.getAdmin().getUsername());
        return dto;
    }
}
