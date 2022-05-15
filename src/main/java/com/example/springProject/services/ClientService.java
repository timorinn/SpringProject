package com.example.springProject.services;

import com.example.springProject.repositories.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import com.example.springProject.entities.Client;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	private ClientRepository repository;

	private final Logger logger = LogManager.getLogger(ClientService.class);


	/**
	 *		Alternative form through EntityManager
	 */
	public void addNewClient(Client client) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(client);
		entityManager.getTransaction().commit();
		entityManager.close();
	}


	public Client findById(long id) {
		return repository.findById(id).orElse(null);
	}


	@Modifying
	public Client updateClient(long id, String newFirstname, String newLastname) {
		Client client = repository.findById(id).orElse(null);
		if (client == null) {
			return null;
		}
		client.setFirstname(newFirstname);
		client.setLastname(newLastname);
		repository.save(client);
		repository.flush();
		return client;
	}


	public Client deleteClient(long id) {
		Client client = repository.findById(id).orElse(null);
		logger.debug("Client with id={} {}found.", id, client == null ? "not " : "");
		if (client != null)
			repository.delete(client);
		return client;
	}


	public long count() {
		//todo
		return repository.count();
	}


	public List<Client> findAll() {
		//todo
		return repository.findAll();
	}

	@PreDestroy
	void destroy() {
		logger.info("Service's destroy method is called.");
	}
}
