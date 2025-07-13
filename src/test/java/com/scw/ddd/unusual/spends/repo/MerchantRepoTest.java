package com.scw.ddd.unusual.spends.repo;

import com.scw.ddd.unusual.spends.db.MerchantDB;
import com.scw.ddd.unusual.spends.domain.entity.Merchant;
import com.scw.ddd.unusual.spends.domain.valueobject.SpendingCategory;
import com.scw.ddd.unusual.spends.exception.MerchantNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MerchantRepoTest {

    // 1. Mock - Duplicate that is fully under control - (when given `this` return `that`)
    // 2. Stub - Mini Duplicate that is under our control - (when given `this` return `that`) -
    //      we can configure one `this` and `that` scenario


    @Mock
    private MerchantDB mockMerchantDB;

    @InjectMocks
    private MerchantRepo testSubject;

    @Test
    void shouldBeAbleToReturn0Merchant() {
        var actual = testSubject.getAllMerchant();

        assertTrue(actual.isEmpty());
    }

    @Test
    void shouldBeAbleToReturnAllMerchant() {
        List<Merchant> expected = List.of(
                new Merchant("1", "Mustafa", SpendingCategory.GROCERIES),
                new Merchant("2", "Akash", SpendingCategory.FOOD)
        );
        when(
                mockMerchantDB.getMerchants()
        ).thenReturn(expected);

        var actual = testSubject.getAllMerchant();

        assertTrue(actual.containsAll(expected));
    }

    @Test
    void shouldBeAbleToGetMerchantById() {
        var expected = new Merchant("1", "Mustafa", SpendingCategory.GROCERIES);
        when(
                mockMerchantDB.getMerchantById(anyString())
        ).thenReturn(expected);

        var actual = testSubject.getMerchantById("1");

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowMerchantNotFoundException() {
        var merchantId = "123";
        var exceptionMessage = "Merchant with id: " + merchantId + "not found";
        when(
                mockMerchantDB.getMerchantById(merchantId)
        ).thenReturn(null);

        var ex = assertThrows(
                MerchantNotFoundException.class, () -> testSubject.getMerchantById(merchantId)
        );

        assertEquals(exceptionMessage, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConnectingToDB() {
        var merchantId = "123";
        var exceptionMessage = "Unable to connect to DB";
        when(
                mockMerchantDB.getMerchantById(anyString())
        ).thenThrow(new RuntimeException(exceptionMessage));

        var ex = assertThrows(
                RuntimeException.class, () -> testSubject.getMerchantById(merchantId)
        );

        assertEquals(exceptionMessage, ex.getMessage());
    }
}