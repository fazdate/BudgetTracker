package com.fazdate.budgettracker;

import com.fazdate.budgettracker.Models.Entry;
import com.fazdate.budgettracker.Models.User;
import com.fazdate.budgettracker.Services.CurrencyConverterService;
import com.fazdate.budgettracker.Services.UserCreatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Component
public class BudgetTrackerCommandLineApp implements CommandLineRunner {

    // This is only for temporary testing. In the future, the application will be web based, but for now,
    // it is a command line only application
    @Override
    public void run(String... args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please provide your email address");
        String email = input.readLine();
        System.out.println("Please provide your default currency");
        Currency defaultCurrency = Currency.getInstance(input.readLine().toUpperCase());
        User user = new UserCreatorService().createUser(email, defaultCurrency);
        System.out.println(user);

        // Testing adding new entry with new currency
        user.addEntry(Entry.builder()
                .amount(new BigDecimal(1))
                .timeOfEntry(LocalDate.now())
                .currency(Currency.getInstance("EUR"))
                .build());
        System.out.println(user);

        // Testing adding new entry with existing currency
        user.addEntry(Entry.builder()
                .amount(new BigDecimal(-2))
                .currency(Currency.getInstance("EUR"))
                .timeOfEntry(LocalDate.now())
                .build());
        System.out.println(user);


    }
}
