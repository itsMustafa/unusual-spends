package com.scw.ddd.unusual.spends.repo;

import com.scw.ddd.unusual.spends.db.MerchantDB;
import com.scw.ddd.unusual.spends.domain.entity.Merchant;
import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;
import com.scw.ddd.unusual.spends.exception.MerchantNotFoundException;

import java.util.List;

public class MerchantRepo {

    private final MerchantDB merchantDB;


    public MerchantRepo(MerchantDB merchantDB) {
        this.merchantDB = merchantDB;
    }

    public List<Merchant> getAllMerchant() {
        return this.merchantDB.getMerchants();
    }

    public void addMerchant(Merchant merchant) {
        this.merchantDB.addMerchant(merchant);
    }

    public Merchant getMerchantById(String merchantId) {
        var merchant = this.merchantDB.getMerchantById(merchantId);
        if (merchant == null) {
            throw new MerchantNotFoundException(merchantId);
        }
        return merchant;
    }


    public SpendingCategory getMerchantCategoryById(String merchantId) {
        var merchant = getMerchantById(merchantId);
        return merchant.getCategory();
    }
}
