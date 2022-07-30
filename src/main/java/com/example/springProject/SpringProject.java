package com.example.springProject;

import com.example.springProject.configurations.SchedulingConfig;
import com.example.springProject.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;



@SpringBootApplication
@Import(value = {
		SchedulingConfig.class
})
@Slf4j
public class SpringProject {

	public static void displayAllBeans(ApplicationContext applicationContext) {
		String[] allBeans = applicationContext.getBeanDefinitionNames();

		for (int i = 0; i < allBeans.length; i++) {
			System.out.println(i + ") " + allBeans[i]);
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringProject.class, args);
		TaskScheduler taskScheduler = applicationContext.getBean(TaskScheduler.class);
		log.info("TaskScheduler.class : " + taskScheduler.getClass().getName()
				+ " | pool_size : " + ((ThreadPoolTaskScheduler) taskScheduler).getPoolSize()
		);
	}
}
