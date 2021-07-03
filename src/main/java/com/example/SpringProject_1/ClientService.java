package com.example.SpringProject_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import com.example.SpringProject_1.dataModels.Client;
import javax.persistence.*;
import java.util.List;

@Service
public class ClientService {

	@Autowired
	EntityManagerFactory emf;

	@Autowired
	private ClientRepository repository;

	public void addNewClient(Client client) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		em.close();
	}

	@Modifying
	private Client updateClient(long id, String firstname, String lastname) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Client client = em.find(Client.class, id);
		if (client == null) return null;

		client.setFirstname(firstname);
		client.setLastname(lastname);
		em.merge(client);
		em.getTransaction().commit();
		em.close();
		return client;
	}

	public Client findById(long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Client client = em.find(Client.class, id);
		em.getTransaction().commit();
		em.close();
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
