package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.PurchaseForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseFormRepository extends JpaRepository<PurchaseForm, Long> {
}
