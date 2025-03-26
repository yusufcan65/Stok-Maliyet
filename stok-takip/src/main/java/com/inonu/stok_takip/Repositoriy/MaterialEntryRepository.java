package com.inonu.stok_takip.Repositoriy;


import com.inonu.stok_takip.entitiy.MaterialEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MaterialEntryRepository extends JpaRepository<MaterialEntry, Long> {


    List<MaterialEntry> findMaterialEntryByProductId(Long productId);

    @Query("SELECT m FROM MaterialEntry m WHERE m.entryDate BETWEEN :startDate AND :endDate")
    List<MaterialEntry> findEntriesWithinPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
