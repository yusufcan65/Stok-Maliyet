package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.MaterialExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MaterialExitRepository extends JpaRepository<MaterialExit, Long> {

    @Query("SELECT SUM(DISTINCT t.totalPerson) FROM MaterialExit t WHERE t.exitDate BETWEEN :startDate AND :endDate")
    int findTotalPersonsSumBetweenDates(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);


    @Query("SELECT t FROM MaterialExit t WHERE t.exitDate BETWEEN :startDate AND :endDate")
    List<MaterialExit> findByMaterialDateBetween(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(t.totalPrice) FROM MaterialExit t " +
            "JOIN t.product p " +
            "WHERE p.category.name = 'Temizlik' " +
            "AND t.exitDate BETWEEN :startDate AND :endDate")
    Double findTotalPriceForCleaningCategoryBetweenDates(@Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);
}
