package com.inonu.stok_takip.dto.Response;

import java.time.LocalDate;

public class ReportResponse {
    private Long id;
    private String reportType;
    private LocalDate reportCreateDate;
    private int ticketQuantity;
    private Double totalTicketPrice;
    private int totalPersonQuantity; 
    private Double totalMaterialPrice;
    private Double totalCleanPrice; 
    private Double averagePersonCost; 
    private Double averageTicketCot;

    public ReportResponse() {
    }

    public ReportResponse(Long id, String reportType, LocalDate reportCreateDate, int ticketQuantity, Double totalTicketPrice, int totalPersonQuantity, Double totalMaterialPrice, Double totalCleanPrice, Double averagePersonCost, Double averageTicketCot) {
        this.id = id;
        this.reportType = reportType;
        this.reportCreateDate = reportCreateDate;
        this.ticketQuantity = ticketQuantity;
        this.totalTicketPrice = totalTicketPrice;
        this.totalPersonQuantity = totalPersonQuantity;
        this.totalMaterialPrice = totalMaterialPrice;
        this.totalCleanPrice = totalCleanPrice;
        this.averagePersonCost = averagePersonCost;
        this.averageTicketCot = averageTicketCot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getReportCreateDate() {
        return reportCreateDate;
    }

    public void setReportCreateDate(LocalDate reportCreateDate) {
        this.reportCreateDate = reportCreateDate;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Double getTotalTicketPrice() {
        return totalTicketPrice;
    }

    public void setTotalTicketPrice(Double totalTicketPrice) {
        this.totalTicketPrice = totalTicketPrice;
    }

    public int getTotalPersonQuantity() {
        return totalPersonQuantity;
    }

    public void setTotalPersonQuantity(int totalPersonQuantity) {
        this.totalPersonQuantity = totalPersonQuantity;
    }

    public Double getTotalMaterialPrice() {
        return totalMaterialPrice;
    }

    public void setTotalMaterialPrice(Double totalMaterialPrice) {
        this.totalMaterialPrice = totalMaterialPrice;
    }

    public Double getTotalCleanPrice() {
        return totalCleanPrice;
    }

    public void setTotalCleanPrice(Double totalCleanPrice) {
        this.totalCleanPrice = totalCleanPrice;
    }

    public Double getAveragePersonCost() {
        return averagePersonCost;
    }

    public void setAveragePersonCost(Double averagePersonCost) {
        this.averagePersonCost = averagePersonCost;
    }

    public Double getAverageTicketCot() {
        return averageTicketCot;
    }

    public void setAverageTicketCot(Double averageTicketCot) {
        this.averageTicketCot = averageTicketCot;
    }
}
