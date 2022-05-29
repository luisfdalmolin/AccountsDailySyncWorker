package com.luisfdalmolin.accountsdailysyncworker.domain.protocols;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;

public interface SynAccount {

    void sync(Account account) throws Exception;
}
