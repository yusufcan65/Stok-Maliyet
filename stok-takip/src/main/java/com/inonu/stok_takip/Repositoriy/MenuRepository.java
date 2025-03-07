package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
