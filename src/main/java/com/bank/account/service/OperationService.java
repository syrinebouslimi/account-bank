package com.bank.account.service;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.notEmpty;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bank.account.model.Account;
import com.bank.account.model.Operation;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.OperationRepository;

@Service
public class OperationService {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @Inject
    public OperationService(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = requireNonNull(accountRepository);
        this.operationRepository = requireNonNull(operationRepository);
    }

    public List<Operation> findOperations(String accountId,
                                          Instant startOperationDate,
                                          Instant endOperationDate) {

        return operationRepository
            .findOperationsByAccountIdAndDateBetweenOrderByDateDesc(notEmpty(accountId),
                requireNonNull(startOperationDate),
                requireNonNull(endOperationDate));
    }

    public void saveOperation(Operation operation) {

        Account account = getAccount(operation);

        accountRepository.save(account);
        operationRepository.save(requireNonNull(operation));
    }

    private Account getAccount(Operation operation) {
        return requireNonNull(accountRepository.findOne(operation.getAccountId()));
    }
}
