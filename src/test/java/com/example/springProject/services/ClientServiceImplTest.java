package com.example.springProject.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class ClientServiceImplTest {

	@Autowired
	ClientService clientService;

	@Test
	public void serviceIsNull() {
		Assert.assertNotNull(clientService);
	}
}
