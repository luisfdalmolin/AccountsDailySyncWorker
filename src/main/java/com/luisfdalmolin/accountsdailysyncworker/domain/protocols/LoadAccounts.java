package com.luisfdalmolin.accountsdailysyncworker.domain.protocols;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;

import java.io.File;
import java.util.List;

public interface LoadAccounts {

    List<Account> load(File file) throws Exception;
}
