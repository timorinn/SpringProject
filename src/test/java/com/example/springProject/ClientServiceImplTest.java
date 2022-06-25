package com.example.springProject;

import com.example.springProject.configurations.JPAConfig;
import com.example.springProject.services.ClientService;
import com.example.springProject.services.ClientServiceImpl;
import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

	@Autowired
	ClientService clientService;

	@Test
	public void serviceIsNull() {
		Assert.assertNotNull(clientService);
	}
}
