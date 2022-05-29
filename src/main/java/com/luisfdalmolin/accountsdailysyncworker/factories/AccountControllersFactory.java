package com.luisfdalmolin.accountsdailysyncworker.factories;

import com.luisfdalmolin.accountsdailysyncworker.controllers.AccountsDailySyncController;
import com.luisfdalmolin.accountsdailysyncworker.services.CSVLoadAccountsService;
import com.luisfdalmolin.accountsdailysyncworker.services.CSVUpdateAccountsService;
import com.luisfdalmolin.accountsdailysyncworker.services.ReceitaService;

public class AccountControllersFactory {

    public static AccountsDailySyncController makeCSVAccountsDailySyncController() {

        return new AccountsDailySyncController(new CSVLoadAccountsService(),
                                               new ReceitaService(),
                                               new CSVUpdateAccountsService());
    }
}
