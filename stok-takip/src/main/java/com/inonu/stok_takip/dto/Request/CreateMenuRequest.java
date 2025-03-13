package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;
import java.util.List;

public record CreateMenuRequest(
    LocalDate menuDate,
    Double price,
    String menuType,
    List<Long> menuMealIdList,
    List<Long> menuProductIdList
) {}
