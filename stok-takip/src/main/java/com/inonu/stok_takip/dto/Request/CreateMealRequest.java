package com.inonu.stok_takip.dto.Request;

import java.util.List;

public record CreateMealRequest(
    Double mealAmount,
    String name,
    int cookingTime,
    Double servingSize,
    String cookingMethod,
    List<Long> mealItemIdList
){}
