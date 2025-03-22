package com.efekansalman.Library.dto;

import java.util.Date;

import com.efekansalman.Library.Entity.ReportType;

import lombok.Data;

@Data
public class ReportDTO {
    private Long id; 
    private ReportType type; 
    private Date generatedDate;
    private String content; 
    private Long adminId; 
    private String adminUsername;
}
