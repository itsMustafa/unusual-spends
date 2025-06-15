package com.scw.ddd.unusual.spends.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String mobile;
    private final List<CreditCard> creditCards;

    public User(String id, String name, String email, String mobile) {
        // Need to add validations here.
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.creditCards = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
    }
}
