package com.scw.ddd.unusual.spends.domain.entity;

import java.time.LocalDateTime;
import java.time.Month;

public class Transaction {

    private final String id;
    private final Double amount;
    private final String merchantId;
    private final String creditCardId;
    private final LocalDateTime timestamp;

    public Transaction(String id, Double amount, String merchantId, String creditCardId) {
        this.id = id;
        this.amount = amount;
        this.merchantId = merchantId;
        this.creditCardId = creditCardId;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMonth() {
        return timestamp.getMonth().toString();
    }
}
