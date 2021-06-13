package com.example.SpringProject_1;

import com.example.SpringProject_1.dataModels.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{
	@Modifying
	default Client updateClient(long id, String firstname, String lastname) {
		try {
			Client client = this.findById(id).get();
			client.setFirstname(firstname);
			client.setLastname(lastname);
			this.save(client);
			return client;
		} catch (Exception e) {
			return null;
		}
	}
}
