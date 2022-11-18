package com.fazdate.budgettracker.Services;

import com.fazdate.budgettracker.Models.EntriesOfACurrency;
import com.fazdate.budgettracker.Models.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Service
public class UserCreatorService {

    public User createUser(String email, Currency defaultCurrency) {
        return User.builder()
                .email(email)
                .balance(new BigDecimal(0))
                .defaultCurrency(defaultCurrency)
                .entriesOfACurrencies(new ArrayList<>(List.of(getEmptyListOfEntries(defaultCurrency))))
                .build();
    }


    private EntriesOfACurrency getEmptyListOfEntries(Currency defaultCurrency) {
        return EntriesOfACurrency.builder()
                .listOfEntries(new ArrayList<>())
                .balance(new BigDecimal(0))
                .currency(defaultCurrency)
                .build();
    }
}
