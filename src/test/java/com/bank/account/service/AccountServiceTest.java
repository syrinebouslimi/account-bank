package com.bank.account.service;

import com.bank.account.BaseBankAccountTest;
import com.bank.account.model.Account;
import com.bank.account.model.Client;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest extends BaseBankAccountTest {

    private static final String CLIENT_ID = UUID.randomUUID().toString();

    @Inject
    private AccountService accountService;

    @Before
    public void before() {

        Client newclient = new Client(CLIENT_ID,"Syrine","BOUSLIMI");

        mongoTemplate.save(newclient);
    }

    @Test
    public void createAndFindAccounts() {
        Account account1 = new Account("Account 1",CLIENT_ID);
        Account account2 = new Account("Account 2",CLIENT_ID);

        Account accountCreated1 = accountService.createAccount(account1);
        Account accountCreated2 = accountService.createAccount(account2);
        List<Account> accounts = accountService.findAccountsByClient(CLIENT_ID);

        assertAccount(accountCreated1, account1);
        assertAccount(accountCreated2, account2);
    }

    private void assertAccount(Account actual, Account expected) {
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getClientId()).isEqualTo(expected.getClientId());
        assertThat(actual.getAmount()).isEqualTo(expected.getAmount());
        assertThat(actual.isAllowNegativeAmount()).isEqualTo(expected.isAllowNegativeAmount());
    }

}
