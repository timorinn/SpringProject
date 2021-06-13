package com.example.SpringProject_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

@ServletComponentScan
@SpringBootApplication
public class SpringProject1Application {

	public static void displayAllBeans(ApplicationContext applicationContext) {
		String[] allBeans = applicationContext.getBeanDefinitionNames();

		for (String s : allBeans) {
			System.out.println("1) " + s);
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringProject1Application.class, args);
		JpaProperties jpaProperties = new JpaProperties();
		//System.out.println(jpaProperties.getDatabase().name());
		//displayAllBeans(applicationContext);
	}

}
