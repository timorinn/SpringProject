package com.example.SpringProject_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;


@ServletComponentScan
@SpringBootApplication
public class SpringProject1Application {

	public static void displayAllBeans(ApplicationContext applicationContext) {
		String[] allBeans = applicationContext.getBeanDefinitionNames();

		for (int i = 0; i < allBeans.length; i++) {
			System.out.println(i + ") " + allBeans[i]);
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringProject1Application.class, args);
		displayAllBeans(applicationContext);
	}
}
