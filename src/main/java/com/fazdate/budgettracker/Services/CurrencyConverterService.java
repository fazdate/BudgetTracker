package com.fazdate.budgettracker.Services;

import com.fazdate.budgettracker.Models.Entry;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

@Service
@Data
public final class CurrencyConverterService {

    private final ExchangeRateApiService exchangeRateApiService = new ExchangeRateApiService();

    public CurrencyConverterService() {

    }

    public BigDecimal getAmountInAnotherCurrency(Entry entry, Currency currency) throws IOException {
        return convertCurrency(entry.getAmount(), exchangeRateApiService.getExchangeRateBetweenTwoCurrencies(entry.getCurrency(), currency));
    }

    private BigDecimal convertCurrency(BigDecimal amount, double exchangeRate) {
        return new BigDecimal(amount.toString()).multiply(new BigDecimal(exchangeRate));
    }
}
