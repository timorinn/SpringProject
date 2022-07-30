package com.example.springProject.services;

import com.example.springProject.entities.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ClientService {

    public void addNewClient(Client client);
    public Client findById(long id);
    public Client updateClient(long id, String newFirstname, String newLastname);
    public Client deleteClientById(long id);
    public ResponseEntity<List<Client>> getAll();
    public ResponseEntity<Long> countClients();
}
