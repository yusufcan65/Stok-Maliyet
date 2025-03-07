package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
