package com.bank.account.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.bank.account.BaseBankAccountTest;
import com.bank.account.model.Account;
import com.bank.account.model.Client;
import com.bank.account.model.Operation;
import com.bank.account.repository.AccountRepository;

public class OperationServiceTest extends BaseBankAccountTest {

    private static final String CLIENT_ID = UUID.randomUUID().toString();
    private static final String ACCOUNT_ID = UUID.randomUUID().toString();

    @Inject
    private OperationService operationService;

    @Inject
    private AccountRepository accountRepository;

    @Before
    public void before() {

        Client client = new Client(CLIENT_ID,"Syrine","BOUSLIMI");
        Account
            account =
            new Account(ACCOUNT_ID,"Account1",CLIENT_ID);

        mongoTemplate.save(client);
        mongoTemplate.save(account);
    }

    @Test
    public void depositAmountOperation() {
        Operation
            operation =
            new Operation(ACCOUNT_ID,"Operation1",1337);

        operationService.saveOperation(operation);
        List<Operation>
           operations =
            operationService.findOperations(ACCOUNT_ID,
                Instant.now().minus(1, ChronoUnit.DAYS),
                Instant.now().plus(1, ChronoUnit.DAYS));

        Account account = accountRepository.findOne(ACCOUNT_ID);
    }

    @Test
    public void depositWithDrawalOperation() {
        Operation
            operation =
            new Operation(ACCOUNT_ID,"OperationWithDrawal",-1337);

        operationService.saveOperation(operation);
        List<Operation>
            operations =
            operationService.findOperations(ACCOUNT_ID,
                Instant.now().minus(1, ChronoUnit.DAYS),
                Instant.now().plus(1, ChronoUnit.DAYS));

        Account account = accountRepository.findOne(ACCOUNT_ID);
    }


    @Test
    public void getOperations() {
        Operation
            todayOperation =
            new Operation(ACCOUNT_ID,"OperationLabel",-1337);
        Operation
            yesterdayOperation =
            new Operation(ACCOUNT_ID,Instant.now().minus(1, ChronoUnit.DAYS),"Operation1",1234);
        Operation
            lastWeekOperation =
            new Operation(ACCOUNT_ID,Instant.now().minus(7, ChronoUnit.DAYS),"Operation2",10);

        mongoTemplate.save(todayOperation);
        mongoTemplate.save(yesterdayOperation);
        mongoTemplate.save(lastWeekOperation);

        List<Operation>
            lastTwoDaysOperations =
            operationService
                .findOperations(ACCOUNT_ID, Instant.now().minus(2, ChronoUnit.DAYS), Instant.now());

        System.out.println(lastTwoDaysOperations.toString());
    }
}
