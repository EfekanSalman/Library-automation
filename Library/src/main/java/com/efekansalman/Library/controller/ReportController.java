package com.efekansalman.Library.controller;

import java.util.List;

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
import com.efekansalman.Library.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public ResponseEntity<Report> generateReport(@RequestParam Long adminId, @RequestParam ReportType type) {
		// Generates a report by an admin
		Report report = reportService.generateReport(adminId, type);
		return ResponseEntity.ok(report);
	}
	
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<List<Report>> findReportsByAdmin(@PathVariable Long adminId) {
		// Finds all reports by an admin
		List<Report> reports = reportService.findReportsByAdmin(adminId);
		return ResponseEntity.ok(reports);
	}
	
	@GetMapping("/type")
	public ResponseEntity<List<Report>> findReportsByType(@RequestParam ReportType type) {
		// Finds reports by type
        List<Report> reports = reportService.findReportsByType(type);
		return ResponseEntity.ok(reports);
	}
}
