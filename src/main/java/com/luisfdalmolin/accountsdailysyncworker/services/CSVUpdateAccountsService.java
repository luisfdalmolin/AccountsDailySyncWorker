package com.luisfdalmolin.accountsdailysyncworker.services;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;
import com.luisfdalmolin.accountsdailysyncworker.domain.protocols.UpdateAccounts;
import com.luisfdalmolin.accountsdailysyncworker.util.CSVUtils;

import java.io.File;
import java.util.List;

public class CSVUpdateAccountsService implements UpdateAccounts {

    @Override
    public void update(File file, List<Account> accounts) throws Exception {
        StringBuilder sb = new StringBuilder();

        // headers
        sb.append("agencia").append(";")
          .append("conta").append(";")
          .append("saldo").append(";")
          .append("status").append(";")
          .append("resultado")
          .append(";").append(CSVUtils.CSV_LINE_ENDING_CHAR);

        accounts.forEach(a-> sb.append(a.getAgency()).append(";")
                               .append(a.getNumber()).append(";")
                               .append(a.getBalance()).append(";")
                               .append(a.getStatus()).append(";")
                               .append(a.isUpdated() ? "SUCESSO" : "FALHA")
                               .append(";").append(CSVUtils.CSV_LINE_ENDING_CHAR));

        CSVUtils.writeCSV(file, sb);
    }
}
