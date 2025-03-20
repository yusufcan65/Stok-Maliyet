package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.MaterialDemand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDemandRepository extends JpaRepository<MaterialDemand, Long> {
}
