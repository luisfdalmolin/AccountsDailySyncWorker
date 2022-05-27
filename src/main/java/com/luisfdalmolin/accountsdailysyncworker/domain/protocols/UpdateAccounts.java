package com.luisfdalmolin.accountsdailysyncworker.domain.protocols;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;

import java.io.File;
import java.util.List;

public interface UpdateAccounts {

    void update(File file, List<Account> accounts) throws Exception;
}
