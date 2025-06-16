package com.scw.ddd.unusual.spends.domain.service;

import com.scw.ddd.unusual.spends.config.UnusualSpendsConfig;
import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;
import com.scw.ddd.unusual.spends.dto.SpendingByCategoryAndAmount;
import com.scw.ddd.unusual.spends.dto.UnusualSpend;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UnusualSpendsProcessor {

    private static double getPercentage(Double currentMonthSpendingAmount, Double lastMonthSpendingAmount) {
        return (currentMonthSpendingAmount - lastMonthSpendingAmount) * 100 / lastMonthSpendingAmount;
    }

    public List<UnusualSpend> getUnusualSpending(
            List<SpendingByCategoryAndAmount> lastMonthSpendingByCategoryAndAmounts,
            List<SpendingByCategoryAndAmount> currentMonthSpendingByCategoryAndAmount,
            UnusualSpendsConfig unusualSpendsConfig
    ) {

        List<UnusualSpend> unusualSpends = new ArrayList<>();

        for (SpendingCategory category : SpendingCategory.values()) {
            var currentMonthSpendingOptional = getSpendingByCategory(currentMonthSpendingByCategoryAndAmount, category);

            var lastMonthSpendingOptional = getSpendingByCategory(lastMonthSpendingByCategoryAndAmounts, category);

            if (currentMonthSpendingOptional.isPresent() && lastMonthSpendingOptional.isPresent()) {
                var currentMonthSpending = currentMonthSpendingOptional.get();
                var lastMonthSpending = lastMonthSpendingOptional.get();

                var percentage = getPercentage(
                        currentMonthSpending.getAmount(),
                        lastMonthSpending.getAmount()
                );

                if (percentage >= unusualSpendsConfig.getPercentage()) {
                    var unusualSpend = new UnusualSpend(currentMonthSpending.getCategory(), currentMonthSpending.getAmount());
                    unusualSpends.add(unusualSpend);
                }

            }
        }

        return unusualSpends;
    }

    private static Optional<SpendingByCategoryAndAmount> getSpendingByCategory(List<SpendingByCategoryAndAmount> spendingByCategoryAndAmount, SpendingCategory category) {
        return spendingByCategoryAndAmount
                .stream()
                .filter(spendingByCategoryAndAmountForMonth -> category == spendingByCategoryAndAmountForMonth.getCategory())
                .findFirst();
    }

}
