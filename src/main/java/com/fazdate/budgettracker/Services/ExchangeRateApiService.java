package com.fazdate.budgettracker.Services;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;


public class ExchangeRateApiService {
    private static final String EXCHANGE_RATE_BASE_URL = "https://api.exchangerate.host/convert?from";


    public double getExchangeRateBetweenTwoCurrencies(Currency currency1, Currency currency2) throws IOException {
        String queryUrl = String.format(EXCHANGE_RATE_BASE_URL+"=%s&to=%s",currency1.getCurrencyCode(), currency2.getCurrencyCode());
        URL url = new URL(queryUrl);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return Double.parseDouble(jsonObject.get("result").toString());
    }

}
