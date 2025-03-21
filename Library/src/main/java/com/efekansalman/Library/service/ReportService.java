package com.efekansalman.Library.service;

import java.util.List;

import com.efekansalman.Library.Entity.Report;
import com.efekansalman.Library.Entity.ReportType;

public interface ReportService {
	// Generates a report by an admin
	Report generateReport(Long adminId, ReportType type);
	
	// Finds all reports by an admin
	List<Report> findReportsByAdmin(Long adminId);
	
	// Finds reports by type
	List<Report> findReportsByType(ReportType type);
}
