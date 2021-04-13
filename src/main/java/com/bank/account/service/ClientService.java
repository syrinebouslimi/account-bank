package com.bank.account.service;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.notEmpty;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bank.account.model.Client;
import com.bank.account.repository.ClientRepository;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Inject
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = requireNonNull(clientRepository);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findClient(String id) {
        return clientRepository.findOne(id);
    }

}
