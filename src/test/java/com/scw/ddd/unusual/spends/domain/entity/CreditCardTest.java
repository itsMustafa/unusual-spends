package com.scw.ddd.unusual.spends.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void shouldBeAbleToInstantiateCreditCardWithGivenValue() {
        var creditCardNumber = "123-456";
        var userId = "111";

        var creditCard = new CreditCard(creditCardNumber, userId);

        assertEquals(creditCardNumber, creditCard.getNumber());
        assertEquals(userId, creditCard.getUserId());
    }

    @Test
    void shouldBeAbleToInstantiateCreditCardWithEmptyTransaction() {
        // Arrange
        var creditCardNumber = "123-456";
        var userId = "111";
        var creditCard = new CreditCard(creditCardNumber, userId);

        // Act
        var actual = creditCard.getTransaction();

        // Assert
        assertTrue(actual.isEmpty());
    }
}