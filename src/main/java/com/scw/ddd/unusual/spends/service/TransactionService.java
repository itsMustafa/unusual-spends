package com.scw.ddd.unusual.spends.service;

import com.scw.ddd.unusual.spends.domain.entity.Transaction;
import com.scw.ddd.unusual.spends.dto.TransactionWithCategory;
import com.scw.ddd.unusual.spends.repo.MerchantRepo;
import com.scw.ddd.unusual.spends.repo.TransactionRepo;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final MerchantRepo merchantRepo;
    private final List<TransactionWithCategory> transactionsWithCategory;

    public TransactionService(TransactionRepo transactionRepo, MerchantRepo merchantRepo) {
        this.transactionRepo = transactionRepo;
        this.merchantRepo = merchantRepo;
        transactionsWithCategory = new ArrayList<>();
    }

    public List<TransactionWithCategory> createAndAddTransactionWithCategory() {
        for (Transaction transaction : transactionRepo.getAllTransactions()) {
            var category = merchantRepo.getMerchantCategoryById(transaction.getMerchantId());
            var transactionWithCategory = new TransactionWithCategory(transaction, category);
            transactionsWithCategory.add(transactionWithCategory);
        }

        return transactionsWithCategory;
    }
}
