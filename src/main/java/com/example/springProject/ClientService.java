package com.example.springProject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import com.example.springProject.dataModels.Client;

import javax.persistence.*;
import java.util.List;

@Service
public class ClientService {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	private ClientRepository repository;

	Logger logger = LogManager.getLogger(ClientService.class);

	public void addNewClient(Client client) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(client);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Modifying
	private Client updateClient(long id, String firstname, String lastname) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Client client = entityManager.find(Client.class, id);

		if (client == null) {
			entityManager.close();
			return null;
		}

		client.setFirstname(firstname);
		client.setLastname(lastname);
		entityManager.merge(client);
		entityManager.getTransaction().commit();
		entityManager.close();
		return client;
	}

	public Client findById(long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Client client = entityManager.find(Client.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return client;
	}

	public long count() {
		//todo
		return repository.count();
	}

	public Client updateById(long id, String firstname, String lastname) {
		return updateClient(id, firstname, lastname);
	}

	public List<Client> findAll() {
		//todo
		return repository.findAll();
	}
}
