package com.scw.ddd.unusual.spends.domain.entity;

import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;

public class Merchant {
    private final String id;
    private final String name;
    private final SpendingCategory category;


    public Merchant(String id, String name, SpendingCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpendingCategory getCategory() {
        return category;
    }
}
