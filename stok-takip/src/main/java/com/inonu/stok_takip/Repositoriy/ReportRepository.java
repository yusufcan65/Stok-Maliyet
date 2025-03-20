package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
