package com.scw.ddd.unusual.spends.domain.entity;

import com.scw.ddd.unusual.spends.exception.InvalidCreditCardNumberException;
import com.scw.ddd.unusual.spends.exception.InvalidUserIdException;

import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private final String number;
    private final String userId;
    private final List<String> transactionIds;

    public CreditCard(String number, String userId) {
        if (number == null || number.isBlank()) {
            throw new InvalidCreditCardNumberException(number);
        }
        if (userId == null || userId.isBlank()) {
            throw new InvalidUserIdException(userId);
        }
        this.number = number;
        this.userId = userId;
        transactionIds = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public String getUserId() {
        return userId;
    }

    public void addTransaction(String transactionId) {
        transactionIds.add(transactionId);
    }

    public List<String> getTransaction() {
        return transactionIds;
    }
}
