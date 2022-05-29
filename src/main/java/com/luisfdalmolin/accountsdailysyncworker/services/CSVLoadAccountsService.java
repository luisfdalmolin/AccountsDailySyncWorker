package com.luisfdalmolin.accountsdailysyncworker.services;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.LoadAccounts;
import com.luisfdalmolin.accountsdailysyncworker.util.CSVUtils;
import com.luisfdalmolin.accountsdailysyncworker.util.NumberUtils;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVLoadAccountsService implements LoadAccounts {

    private static final int COL_AGENCY  = 0;
    private static final int COL_NUMBER  = 1;
    private static final int COL_BALANCE = 2;
    private static final int COL_STATUS  = 3;

    @Override
    public List<Account> load(File file) throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        List<String> rows = CSVUtils.readCSV(file);

        if (!rows.isEmpty()) {
            rows.forEach(r -> {
                try {
                    Account account = parse(CSVUtils.getColumns(r));

                    if (account != null) {
                        accounts.add(account);
                    }
                } catch (ParseException e) {
                    System.err.println("Error while reading account " + CSVUtils.getColumns(r)[1] + ": " + e);
                    e.printStackTrace();
                }
            });
        }

        return accounts;
    }

    private Account parse(String[] columns) throws ParseException {
        if (columns.length > 0) {
            return new Account(columns[COL_AGENCY],
                    columns[COL_NUMBER],
                    NumberUtils.parseValue(columns[COL_BALANCE]).doubleValue(),
                    columns[COL_STATUS]);
        }

        return null;
    }
}
