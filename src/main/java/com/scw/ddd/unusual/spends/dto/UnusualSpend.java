package com.scw.ddd.unusual.spends.dto;

import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;

public class UnusualSpend {

    private final SpendingCategory category;
    private final Double amount;

    public UnusualSpend(SpendingCategory category, Double amount) {
        this.category = category;
        this.amount = amount;
    }

    public SpendingCategory getCategory() {
        return category;
    }

    public Double getAmount() {
        return amount;
    }
}
