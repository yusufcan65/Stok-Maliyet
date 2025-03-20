package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Report extends BaseEntity{

    private String reportType; // günlük - aylık - yıllık
    private LocalDate reportCreateDate; // rapor oluşturulma tarihi
    private int ticketQuantity; // satılan toplam bilet sayısı
    private Double totalTicketPrice; // satılan biletlerin toplam tutarı
    private int totalPersonQuantity; // toplam mevcut ( yapılan yemek adedi)
    private Double totalMaterialPrice; // toplam malzeme giderleri
    private Double totalCleanPrice; // toplam temizlik malzemeleri gideri  (bu günlük raporda olmayacak)
    private Double averagePersonCost; // mevcuda göre ortalama bir tabak yemek maliyeti
    private Double averageTicketCot; // satılan bilete göre ortalama 1 tabak yemek maliyeti



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
