package com.fazdate.budgettracker.Models;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

@Getter
@Builder
public class EntriesOfACurrency {
    private ArrayList<Entry> listOfEntries;
    private Currency currency;
    private BigDecimal balance;

    public void addEntry(Entry entry) {
        listOfEntries.add(entry);
        balance = balance.add(entry.getAmount());
    }

    @Override
    public String toString() {
        return "EntriesOfACurrency{" +
                "listOfEntries=" + listOfEntries +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }
}
