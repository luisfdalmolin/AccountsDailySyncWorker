package com.luisfdalmolin.accountsdailysyncworker.controllers;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.LoadAccounts;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.UpdateAccounts;
import com.luisfdalmolin.accountsdailysyncworker.services.ReceitaService;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class AccountsDailySyncController {
    private LoadAccounts loadAccounts;
    private ReceitaService receitaService;
    private UpdateAccounts updateAccounts;

    public AccountsDailySyncController(LoadAccounts loadAccounts, ReceitaService receitaService, UpdateAccounts updateAccounts) {
        this.loadAccounts = loadAccounts;
        this.receitaService = receitaService;
        this.updateAccounts = updateAccounts;
    }

    public void sync(File file) throws Exception {
        List<Account> accounts = loadAccounts.load(file);

        if (!accounts.isEmpty()) {
            accounts.forEach(a -> {
                try {
                    receitaService.atualizarConta(a);
                } catch (Exception e) {
                    System.err.println("Error while updating account " + a.getNumber() + ": " + e);
                }
            });

            File resultFile = new File(System.getProperty("user.home")
                                       + File.separator
                                       + "result-" + UUID.randomUUID() + ".csv");

            if (resultFile.createNewFile()) {
                updateAccounts.update(resultFile, accounts);
            }
        }
    }
}
