package com.ipras.in28minutes.springdatajpacourse;

import com.ipras.in28minutes.springdatajpacourse.entity.FullTimeEmployee;
import com.ipras.in28minutes.springdatajpacourse.entity.PartTimeEmployee;
import com.ipras.in28minutes.springdatajpacourse.entity.Person;
import com.ipras.in28minutes.springdatajpacourse.repository.EmployeeRepository;
import com.ipras.in28minutes.springdatajpacourse.repository.PersonRepository;
import com.ipras.in28minutes.springdatajpacourse.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	Logger logger = LoggerFactory.getLogger(SpringJpaDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Jack FullTimeEmployee salary - 10000$
		// Jill PartTimeEmployee - 50$ per hour
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

		logger.info("Full Time Employees -> {}",
				employeeRepository.retrieveAllFullTimeEmployees());

		logger.info("Part Time Employees -> {}",
				employeeRepository.retrieveAllPartTimeEmployees());

		// studentRepository.saveStudentWithPassport();

//		logger.info("All Users -> {}", personRepository.findAll());
//		logger.info("Get User -> {}", personRepository.findById(10001));
//		personRepository.delete(10002);
//		logger.info("Inserting User -> {}", personRepository.update(new Person("Prabhat", "Berlin", new Date())));
//		logger.info("Updating User -> {}", personRepository.update(new Person(10003,"James Gordon", "London", new Date())));
//		logger.info("All Users -> {}", personRepository.findAll());

	}
}
