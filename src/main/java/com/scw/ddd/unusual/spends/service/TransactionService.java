package com.scw.ddd.unusual.spends.service;

import com.scw.ddd.unusual.spends.domain.entity.Transaction;
import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;
import com.scw.ddd.unusual.spends.dto.SpendingByCategoryAndAmount;
import com.scw.ddd.unusual.spends.dto.TransactionWithCategory;
import com.scw.ddd.unusual.spends.repo.MerchantRepo;
import com.scw.ddd.unusual.spends.repo.TransactionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<TransactionWithCategory> getAllTransactionWithCategory() {
        return this.transactionsWithCategory;
    }

    public List<SpendingByCategoryAndAmount> getSpendingByCategoryAndAmountFor(
            Set<String> creditCardIds,
            String month,
            List<TransactionWithCategory> transactionWithCategoryList
    ) {
        List<SpendingByCategoryAndAmount> spendingByCategoryAndAmountList = new ArrayList<>();
        List<TransactionWithCategory> validTransactions = new ArrayList<>();

        for (TransactionWithCategory transactionWithCategory : transactionWithCategoryList) {
            String transactionMonth = transactionWithCategory.getTransaction().getMonth();
            if (transactionMonth == month) {

                String creditCardIdFromTransaction = transactionWithCategory.getTransaction().getCreditCardId();
                if (creditCardIds.contains(creditCardIdFromTransaction)) {

                    validTransactions.add(transactionWithCategory);
                }
            }
        }

        for (TransactionWithCategory transactionWithCategory : validTransactions) {
            SpendingCategory spendingCategory = transactionWithCategory.getSpendingCategory();
            Double amount = transactionWithCategory.getTransaction().getAmount();

            var spendingByCategoryAndAmount = new SpendingByCategoryAndAmount(spendingCategory, amount);

            spendingByCategoryAndAmountList.add(spendingByCategoryAndAmount);
        }

        return spendingByCategoryAndAmountList;
    }
}
