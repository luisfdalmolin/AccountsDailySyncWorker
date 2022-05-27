package com.luisfdalmolin.accountsdailysyncworker.services;

import com.luisfdalmolin.accountsdailysyncworker.domain.models.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReceitaServiceTest {

    private static ReceitaService receitaService;

    @BeforeAll
    public static void beforeAll() {
        receitaService = new ReceitaService();
    }

    // SUT
    private Account createAccount(String agency, String number, double balance, String status) {
        return new Account(agency, number, balance, status);
    }

    @Test
    public void shouldReturnFalseIfAgencyNumberIsNull() throws InterruptedException {
        Account account = createAccount(null, "123456", 1000, "A");
        receitaService.atualizarConta(account);
        assertFalse(account.isUpdated());
    }

    @Test
    public void shouldReturnFalseIfAgencyNumberLengthIsNot4() throws InterruptedException {
        Account account = createAccount("12345", "123456", 1000, "A");
        receitaService.atualizarConta(account);
        assertFalse(account.isUpdated());
    }

    @Test
    public void shouldReturnFalseIfAccountNumberIsNull() throws InterruptedException {
        Account account = createAccount("1234", null, 1000, "A");
        receitaService.atualizarConta(account);
        assertFalse(account.isUpdated());
    }

    @Test
    public void shouldReturnFalseIfAccountNumberLengthIsNot6() throws InterruptedException {
        Account account = createAccount("1234", "1234567", 1000, "A");
        receitaService.atualizarConta(account);
        assertFalse(account.isUpdated());
    }

    @Test
    public void shouldReturnFalseIfStatusIsInvalid() throws InterruptedException {
        Account account = createAccount("1234", "123456", 1000, "X");
        receitaService.atualizarConta(account);
        assertFalse(account.isUpdated());
    }
}
