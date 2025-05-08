package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.Enum.ReportType;
import com.inonu.stok_takip.entitiy.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportCreateDateAndReportType(LocalDate reportCreateDate, ReportType reportType);
}
