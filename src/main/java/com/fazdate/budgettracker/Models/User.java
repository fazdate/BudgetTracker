package com.fazdate.budgettracker.Models;

import com.fazdate.budgettracker.Services.CurrencyConverterService;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Data
@Builder
public class User {
    private String email;
    private BigDecimal balance;
    private ArrayList<EntriesOfACurrency> entriesOfACurrencies;
    private Currency defaultCurrency;

    public void addEntry(Entry entry) throws IOException {
        balance = balance.add(getAmountInDefaultCurrency(entry));
        if (checkIfUserHasAListOfEntriesWithACurrency(entry.getCurrency())) {
            addEntryToListWithExistingCurrency(entry);
        } else {
            addEntryToListWithNewCurrency(entry);
        }
    }

    private BigDecimal getAmountInDefaultCurrency(Entry entry) throws IOException {
        if (entry.getCurrency().equals(defaultCurrency)) {
            return entry.getAmount();
        }

        BigDecimal amount = new CurrencyConverterService().getAmountInAnotherCurrency(entry, defaultCurrency).setScale(2, RoundingMode.HALF_UP);
        return amount;
    }

    private boolean checkIfUserHasAListOfEntriesWithACurrency(Currency currency) {
        for (EntriesOfACurrency entries : entriesOfACurrencies) {
            if (entries.getCurrency().equals(currency)) {
                return true;
            }
        }
        return false;
    }

    private void addEntryToListWithExistingCurrency(Entry entry) {
        for (EntriesOfACurrency entries : entriesOfACurrencies) {
            if (entries.getCurrency().equals(entry.getCurrency())) {
                entries.addEntry(entry);
            }
        }
    }

    private void addEntryToListWithNewCurrency(Entry entry) {
        entriesOfACurrencies.add(new EntriesOfACurrency.EntriesOfACurrencyBuilder()
                .currency(entry.getCurrency())
                .listOfEntries(new ArrayList<>(List.of(entry)))
                .balance(entry.getAmount())
                .build());
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", balance=" + balance +
                ", entriesOfACurrencies=" + entriesOfACurrencies +
                ", defaultCurrency=" + defaultCurrency +
                '}';
    }
}
