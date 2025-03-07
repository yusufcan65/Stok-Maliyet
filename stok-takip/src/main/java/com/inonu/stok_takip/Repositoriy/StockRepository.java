package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
