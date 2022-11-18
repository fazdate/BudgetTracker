package com.fazdate.budgettracker.Models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;


@Data
@Builder
public class Entry {
    private BigDecimal amount;
    private LocalDate timeOfEntry;
    private Currency currency;
}
