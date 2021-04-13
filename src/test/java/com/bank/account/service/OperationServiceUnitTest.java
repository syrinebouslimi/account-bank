package com.bank.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import com.bank.account.model.Account;
import com.bank.account.model.Operation;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.OperationRepository;

public class OperationServiceUnitTest {

    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private OperationRepository operationRepositoryMock;

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    private OperationService operationService;

    @Before
    public void before() {
        initMocks(this);
        operationService = new OperationService(accountRepositoryMock, operationRepositoryMock);
    }

    @Test
    public void saveDepositOperation() {
        Account
            account =
            new Account(UUID.randomUUID().toString(),"My account",100);

        Operation
            operation =
            new Operation(account.getId(),"Operation",100);

        when(accountRepositoryMock.findOne(account.getId())).thenReturn(account);

        operationService.saveOperation(operation);

        verify(operationRepositoryMock).save(operation);
        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account accountCaptured = accountCaptor.getValue();
        assertThat(accountCaptured.getId()).isEqualTo(account.getId());
    }

    @Test
    public void saveWithdrawalOperation() {
        Account
            account =
            new Account(UUID.randomUUID().toString(),"My account",100);

        Operation
            operation =
            new Operation(account.getId(),"OperationLabel",-200);

        when(accountRepositoryMock.findOne(account.getId())).thenReturn(account);

        operationService.saveOperation(operation);

        verify(operationRepositoryMock).save(operation);
        verify(accountRepositoryMock).save(accountCaptor.capture());

        Account accountCaptured = accountCaptor.getValue();
        assertThat(accountCaptured.getId()).isEqualTo(account.getId());
    }


}
