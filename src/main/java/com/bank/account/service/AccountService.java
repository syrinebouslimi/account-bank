package com.bank.account.service;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.notEmpty;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bank.account.model.Account;
import com.bank.account.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Inject
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = requireNonNull(accountRepository);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAccountsByClient(String clientId) {
        return accountRepository.findAccountsByClientId(clientId);
    }
}
