package com.scw.ddd.unusual.spends.db;

import com.scw.ddd.unusual.spends.domain.entity.Merchant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MerchantDB {

    private final List<Merchant> merchants;

    public MerchantDB() {
        this.merchants = new ArrayList<>();
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        this.merchants.add(merchant);
    }

    public Merchant getMerchantById(String merchantId) {
        return this.merchants
                .stream()
                .filter(merchant ->
                        Objects.equals(merchantId, merchant.getId())
                )
                .findFirst().get();
    }
}
