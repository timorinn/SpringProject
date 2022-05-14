package com.example.springProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class SpringProject {

	public static void displayAllBeans(ApplicationContext applicationContext) {
		String[] allBeans = applicationContext.getBeanDefinitionNames();

		for (int i = 0; i < allBeans.length; i++) {
			System.out.println(i + ") " + allBeans[i]);
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringProject.class, args);

		//		displayAllBeans(applicationContext);
	}
}
