package com.ipras.in28minutes.springdatajpacourse;

import com.ipras.in28minutes.springdatajpacourse.jdbc.Person;
import com.ipras.in28minutes.springdatajpacourse.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringDataJpaCourseApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(SpringDataJpaCourseApplication.class);

	@Autowired
	PersonJdbcDao personDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All Users -> {}", personDao.findAll());
		logger.info("Get User -> {}", personDao.findById(10001));
		logger.info("Delete User, Rows affected -> {}", personDao.deleteById(10002));
		logger.info("Inserting User -> {}", personDao.insert(new Person(10004, "Prabhat", "Berlin", new Date())));
		logger.info("Updating User -> {}", personDao.update(new Person(10003, "James Gordon", "London", new Date())));
		logger.info("All Users -> {}", personDao.findAll());
	}
}
