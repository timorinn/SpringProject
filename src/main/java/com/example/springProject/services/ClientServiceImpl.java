package com.example.springProject.services;

import com.example.springProject.exceptions.NotFoundException;
import com.example.springProject.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.springProject.entities.Client;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
	private final EntityManagerFactory entityManagerFactory;
	private final ClientRepository repository;


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
			throw new NotFoundException("Client not found!");
		}
		client.setFirstname(newFirstname);
		client.setLastname(newLastname);
		client = saveClient(client);
		return client;
	}

	@Transactional
	public Client deleteClientById(long id) {
		Client client = repository.findById(id).orElse(null);
		if (client != null) repository.delete(client);
		return client;
	}

	@Transactional(readOnly = true)
	public long count() {
		//todo
		return repository.count();
	}

	@Transactional(readOnly = true)
	public ResponseEntity<List<Client>> getAll() {
		List<Client> clients = repository.findAll();
		Collections.sort(clients);
		return ResponseEntity.ok().body(clients);
	}

	@Override
	public ResponseEntity<Long> countClients() {
		return ResponseEntity.ok().body(repository.count());
	}

	@PreDestroy
	void destroy() {
		log.info("Service's destroy method called.");
	}
}
