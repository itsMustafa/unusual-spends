package com.scw.ddd.unusual.spends.exception;

public class MerchantNotFoundException extends RuntimeException {
    public MerchantNotFoundException(String merchantId) {
        super("Merchant with id: " + merchantId + "not found");
    }
}
