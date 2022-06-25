package com.example.springProject.services;

import com.example.springProject.entities.Client;

import java.util.List;

public interface ClientService {

    public void addNewClient(Client client);
    public Client findById(long id);
    public Client updateClient(long id, String newFirstname, String newLastname);
    public Client deleteClientById(long id);
    public List<Client> findAll();
    public long count();

}
