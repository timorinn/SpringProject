package com.example.SpringProject_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringProject_1.dataModels.Client;

import java.util.List;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client findById(long id) {
		Client client;

		try {
			client = repository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return client;
	}

	public long count() {
		return repository.count();
	}

	public Client updateById(long id, String firstname, String lastname) {
		return repository.updateClient(id, firstname, lastname);
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
}
