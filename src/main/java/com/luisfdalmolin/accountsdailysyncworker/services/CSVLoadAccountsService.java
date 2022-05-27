package com.luisfdalmolin.accountsdailysyncworker.services;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.LoadAccounts;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVLoadAccountsService implements LoadAccounts {

    private static final int COL_AGENCY  = 0;
    private static final int COL_NUMBER  = 1;
    private static final int COL_BALANCE = 2;
    private static final int COL_STATUS  = 3;

    private DecimalFormat decimalFormat = new DecimalFormat();

    @Override
    public List<Account> load(File file) throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        List<String> rows = Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8)
                                 .stream()
                                 .skip(1)
                                 .collect(Collectors.toList());

        if (!rows.isEmpty()) {
            rows.forEach(r -> {
                try {
                    accounts.add(parse(r));
                } catch (ParseException e) {
                    System.err.println("Error while reading account " + r.split(";")[1] + ": " + e);
                    e.printStackTrace();
                }
            });
        }

        return accounts;
    }

    private Account parse(String row) throws ParseException {
        String[] data = row.split(";");

        if (data.length > 0) {
            return new Account(data[COL_AGENCY],
                    data[COL_NUMBER],
                    decimalFormat.parse(data[COL_BALANCE]).doubleValue(),
                    data[COL_STATUS]);
        }

        return null;
    }
}
