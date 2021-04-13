package com.bank.account.service;

import com.bank.account.BaseBankAccountTest;
import com.bank.account.model.Client;
import javax.inject.Inject;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceTest extends BaseBankAccountTest {

    @Inject
    private ClientService clientService;

    @Test
    public void addAndFindClient() {
        Client client = new Client("Syrine","BOUSLIMI");

        Client clientCreated = clientService.createClient(client);

        assertThat(clientCreated.getId()).isNotEmpty();
        assertThat(clientCreated.getFirstname()).isEqualTo(client.getFirstname());
        assertThat(clientCreated.getLastname()).isEqualTo(client.getLastname());
    }

}
