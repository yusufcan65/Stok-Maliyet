package com.inonu.stok_takip.Repositoriy;

import com.inonu.stok_takip.entitiy.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuProductRepository extends JpaRepository<MenuProduct, Long> {
}
