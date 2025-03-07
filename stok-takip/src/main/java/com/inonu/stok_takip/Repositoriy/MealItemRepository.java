package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealItemRepository extends JpaRepository<MealItem, Long> {
}
