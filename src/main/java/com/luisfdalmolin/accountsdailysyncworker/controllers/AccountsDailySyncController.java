package com.luisfdalmolin.accountsdailysyncworker.controllers;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.LoadAccounts;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.SynAccount;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.UpdateAccounts;

import java.io.File;
import java.util.List;

public class AccountsDailySyncController {

    private final LoadAccounts loadAccounts;
    private final SynAccount synAccount;
    private final UpdateAccounts updateAccounts;

    public AccountsDailySyncController(LoadAccounts loadAccounts, SynAccount synAccount, UpdateAccounts updateAccounts) {
        this.loadAccounts = loadAccounts;
        this.synAccount = synAccount;
        this.updateAccounts = updateAccounts;
    }

    public void sync(File file) throws Exception {
        if (file != null && file.isFile()) {
            List<Account> accounts = loadAccounts.load(file);

            if (!accounts.isEmpty()) {
                accounts.forEach(a -> {
                    try {
                        synAccount.sync(a);
                    } catch (Exception e) {
                        System.err.println("Error while updating account " + a.getNumber() + ": " + e);
                    }
                });

                File resultFile = new File(System.getProperty("user.home")
                                           + File.separator
                                           + "result-" + System.currentTimeMillis() + ".csv");

                if (resultFile.createNewFile()) {
                    updateAccounts.update(resultFile, accounts);
                }
            }
        }
    }
}
