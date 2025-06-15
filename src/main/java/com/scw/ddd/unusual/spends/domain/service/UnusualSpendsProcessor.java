package com.scw.ddd.unusual.spends.domain.service;

import com.scw.ddd.unusual.spends.config.UnusualSpendsConfig;
import com.scw.ddd.unusual.spends.dto.SpendingByCategoryAndAmount;
import com.scw.ddd.unusual.spends.dto.UnusualSpend;

import java.util.ArrayList;
import java.util.List;

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

        for (SpendingByCategoryAndAmount currentMonthSpending: currentMonthSpendingByCategoryAndAmount) {

            for (SpendingByCategoryAndAmount lastMonthSpending: lastMonthSpendingByCategoryAndAmounts) {

                if (currentMonthSpending.getCategory() == lastMonthSpending.getCategory()) {

                    var percentage = getPercentage(
                            currentMonthSpending.getAmount(),
                            lastMonthSpending.getAmount()
                    );

                    if (percentage >= unusualSpendsConfig.getPercentage()) {
                        UnusualSpend unusualSpend = new UnusualSpend(currentMonthSpending.getCategory(), currentMonthSpending.getAmount());
                        unusualSpends.add(unusualSpend);
                    }
                }
            }
        }

        return unusualSpends;
    }

}
