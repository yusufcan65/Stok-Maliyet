package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.MaterialExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MaterialExitRepository extends JpaRepository<MaterialExit, Long> {

    // günlük yapılan yemek sayısı
    @Query("SELECT SUM(DISTINCT t.totalPerson) FROM MaterialExit t WHERE t.exitDate = :date")
    Integer findTotalPersonsByDay(@Param("date") LocalDate date);

    // Aylık yapılan yemek sayısı
    @Query(value = "SELECT SUM(DISTINCT total_person) FROM material_exit " +
            "WHERE EXTRACT(MONTH FROM exit_date) = EXTRACT(MONTH FROM CAST(:date AS DATE)) " +
            "AND EXTRACT(YEAR FROM exit_date) = EXTRACT(YEAR FROM CAST(:date AS DATE))",
            nativeQuery = true)
    Integer findTotalPersonsByMonth(@Param("date") LocalDate date);

    // yıllık bazdaa yapılan yemek sayısı
    @Query(value = """
    SELECT SUM(DISTINCT total_person) FROM material_exit
    WHERE EXTRACT(YEAR FROM exit_date) = EXTRACT(YEAR FROM CAST(:date AS DATE))
""", nativeQuery = true)
    Integer findTotalPersonsByYear(@Param("date") LocalDate date);


    // 1- Günlük, sadece temizlik harici toplam
    @Query("SELECT SUM(m.totalPrice) FROM MaterialExit m WHERE m.exitDate = :date AND m.product.category.name <> 'Temizlik'")
    Double findNonCleaningTotalByExitDate(@Param("date") LocalDate date);

    // 2- Aylık toplam (LocalDate parametresiyle - sadece ay ve yılına bakar)
    @Query("SELECT SUM(m.totalPrice) FROM MaterialExit m WHERE EXTRACT(MONTH FROM m.exitDate) = EXTRACT(MONTH FROM :date) AND EXTRACT(YEAR FROM m.exitDate) = EXTRACT(YEAR FROM :date)")
    Double findTotalByMonth(@Param("date") LocalDate date);

    // 3- Yıllık toplam (LocalDate parametresiyle - sadece yılına bakar)
    @Query("SELECT SUM(m.totalPrice) FROM MaterialExit m WHERE EXTRACT(YEAR FROM m.exitDate) = EXTRACT(YEAR FROM :date)")
    Double findTotalByYear(@Param("date") LocalDate date);


    @Query("SELECT t FROM MaterialExit t WHERE t.exitDate BETWEEN :startDate AND :endDate")
    List<MaterialExit> findByMaterialDateBetween(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(t.totalPrice) FROM MaterialExit t " +
            "JOIN t.product p " +
            "WHERE p.category.name = 'Temizlik' " +
            "AND t.exitDate BETWEEN :startDate AND :endDate")
    Double findTotalPriceForCleaningCategoryBetweenDates(@Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);



    //  bundan sonrası tamamen raporlama için yazılmış
   /* @Query("SELECT m FROM MaterialExit m WHERE m.exitDate = :date")
    List<MaterialExit> findByExitDate(@Param("date") LocalDate date);
*/

    /*
    @Query("SELECT SUM(m.totalPrice) FROM MaterialExit m WHERE m.exitDate = :date AND m.product.category.name <> 'Temizlik'")
    Double findNonCleaningTotalByExitDate(@Param("date") LocalDate date);


    @Query("SELECT SUM(m.totalPrice) FROM MaterialExit m WHERE MONTH(m.exitDate) = :month AND YEAR(m.exitDate) = :year")
    Double findTotalByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);


    @Query("SELECT SUM(m.totalPrice) FROM MaterialExit m WHERE YEAR(m.exitDate) = :year")
    Double findTotalByYear(@Param("year") Integer year);


     */
}

