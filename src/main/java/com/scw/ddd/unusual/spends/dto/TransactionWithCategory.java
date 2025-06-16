package com.scw.ddd.unusual.spends.dto;

import com.scw.ddd.unusual.spends.domain.entity.Transaction;
import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;

public class TransactionWithCategory {

    private final Transaction transaction;
    private final SpendingCategory spendingCategory;

    public TransactionWithCategory(Transaction transaction, SpendingCategory spendingCategory) {
        this.transaction = transaction;
        this.spendingCategory = spendingCategory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public SpendingCategory getSpendingCategory() {
        return spendingCategory;
    }
}
