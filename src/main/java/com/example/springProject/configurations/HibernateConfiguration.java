package com.example.springProject.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateConfiguration {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Bean
	EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
