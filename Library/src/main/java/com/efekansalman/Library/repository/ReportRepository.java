package com.efekansalman.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efekansalman.Library.Entity.Admin;
import com.efekansalman.Library.Entity.Report;
import com.efekansalman.Library.Entity.ReportType;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	// Finds reports by admin
	List<Report> findByAdmin(Admin admin);
	
	// Finds reports by type
	List<Report> findByType(ReportType type);	
}
