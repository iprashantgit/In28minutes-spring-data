package com.ipras.in28minutes.springdatajpacourse;

import com.ipras.in28minutes.springdatajpacourse.entity.Person;
import com.ipras.in28minutes.springdatajpacourse.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;

	Logger logger = LoggerFactory.getLogger(SpringJpaDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All Users -> {}", personRepository.findAll());
		logger.info("Get User -> {}", personRepository.findById(10001));
		personRepository.delete(10002);
		logger.info("Inserting User -> {}", personRepository.update(new Person("Prabhat", "Berlin", new Date())));
		logger.info("Updating User -> {}", personRepository.update(new Person(10003,"James Gordon", "London", new Date())));
		logger.info("All Users -> {}", personRepository.findAll());

	}
}
