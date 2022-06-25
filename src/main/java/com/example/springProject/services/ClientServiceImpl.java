package com.example.springProject.services;

import com.example.springProject.repositories.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import com.example.springProject.entities.Client;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	private final EntityManagerFactory entityManagerFactory;
	private final ClientRepository repository;

//	@PersistenceContext
//	private EntityManager entityManager;

	private final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

	@Autowired
	public ClientServiceImpl(EntityManagerFactory entityManagerFactory, ClientRepository repository) {
		this.entityManagerFactory = entityManagerFactory;
		this.repository = repository;
	}

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

	@Transactional(readOnly = true)
	public Client findById(long id) {
		return repository.findById(id).orElse(null);
	}


	@Modifying
	@Transactional
	public Client saveClient(Client client) {
		repository.save(client);
		return client;
	}

	public Client updateClient(long id, String newFirstname, String newLastname) {
		Client client = repository.findById(id).orElse(null);
		if (client == null) {
			return null;
		}
		client.setFirstname(newFirstname);
		client.setLastname(newLastname);
		client = saveClient(client);
		return client;
	}

	@Transactional
	public Client deleteClientById(long id) {
		Client client = repository.findById(id).orElse(null);
//		logger.debug("Client with id={} {}found.", id, client == null ? "not " : "");
		if (client != null)
			repository.delete(client);
		return client;
	}

	@Transactional(readOnly = true)
	public long count() {
		//todo
		return repository.count();
	}

	@Transactional(readOnly = true)
	public List<Client> findAll() {
		//todo
		return repository.findAll();
	}

	@PreDestroy
	void destroy() {
		logger.info("Service's destroy method called.");
	}
}
