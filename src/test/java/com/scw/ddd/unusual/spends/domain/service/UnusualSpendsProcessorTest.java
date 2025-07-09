package com.scw.ddd.unusual.spends.domain.service;

import com.scw.ddd.unusual.spends.config.UnusualSpendsConfig;
import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;
import com.scw.ddd.unusual.spends.dto.SpendingByCategoryAndAmount;
import com.scw.ddd.unusual.spends.dto.UnusualSpend;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UnusualSpendsProcessorTest {

    private final UnusualSpendsProcessor testSubject = new UnusualSpendsProcessor();

    private static List<SpendingByCategoryAndAmount> getSpendingByCategory(SpendingCategory category, Double amount) {
        return List.of(
                new SpendingByCategoryAndAmount(category, amount)
        );
    }

    @Test
    void shouldBeAbleToGetUnusualSpendsWhenExceedThreshold() {
        // Arrange
        var lastMonthSpendingByCategoryAndAmountList = getSpendingByCategory(SpendingCategory.GROCERIES, 100.0);
        var currentMonthSpendingByCategoryAndAmountList = getSpendingByCategory(SpendingCategory.GROCERIES, 200.0);
        var unusualSpendsConfig = new UnusualSpendsConfig(50.0);
        var expectedUnusualSpend1 = new UnusualSpend(SpendingCategory.GROCERIES, 200.0);
        var expected = List.of(expectedUnusualSpend1);

        // Act
        var actual = testSubject.getUnusualSpending(
                lastMonthSpendingByCategoryAndAmountList,
                currentMonthSpendingByCategoryAndAmountList,
                unusualSpendsConfig
        );

        // Assert
        assertTrue(expected.containsAll(actual));
    }

    @Test
    void shouldBeAbleToReturnEmptyUnusualSpendsIfNotExceededThreshold() {
        // Arrange
        var lastMonthSpendingByCategoryAndAmountList = getSpendingByCategory(SpendingCategory.FOOD, 200.0);
        var currentMonthSpendingByCategoryAndAmountList = getSpendingByCategory(SpendingCategory.FOOD, 250.0);
        var unusualSpendsConfig = new UnusualSpendsConfig(50.0);

        // Act
        var actual = testSubject.getUnusualSpending(
                lastMonthSpendingByCategoryAndAmountList,
                currentMonthSpendingByCategoryAndAmountList,
                unusualSpendsConfig
        );

        // Assert
        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldBeAbleToReturnUnusualSpendsIfMetThreshold() {
        // Arrange
        var lastMonthSpendingByCategoryAndAmountList = getSpendingByCategory(SpendingCategory.FOOD, 100.0);
        var currentMonthSpendingByCategoryAndAmountList = getSpendingByCategory(SpendingCategory.FOOD, 150.0);
        var unusualSpendsConfig = new UnusualSpendsConfig(50.0);
        var expected = List.of(
                new UnusualSpend(SpendingCategory.FOOD, 150.0)
        );

        // Act
        var actual = testSubject.getUnusualSpending(
                lastMonthSpendingByCategoryAndAmountList,
                currentMonthSpendingByCategoryAndAmountList,
                unusualSpendsConfig
        );

        // Assert
        assertTrue(actual.containsAll(expected));
    }
}