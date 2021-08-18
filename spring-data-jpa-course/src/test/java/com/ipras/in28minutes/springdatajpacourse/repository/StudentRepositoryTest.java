package com.ipras.in28minutes.springdatajpacourse.repository;

import com.ipras.in28minutes.springdatajpacourse.entity.Passport;
import com.ipras.in28minutes.springdatajpacourse.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails(){
        Student dbStudent = entityManager.find(Student.class, 20001L);

        Student testStudent = new Student(20001L, "Ranga", new Passport("E123456"));

        // in lazy evaluation passport will be fetched on line 32 only
        // please note this is enabled via Persistence Context, dbStudent will be
        // in persistence context and by using which lazy operation is performed. ie. running
        // passport query when we try to get passport details
        // also for persistence context and lazy evaluation, @transactional is required
        System.out.println("--------------------------" + dbStudent.getName());

        Assertions.assertThat(dbStudent.toString()).isEqualTo(testStudent.toString());
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent(){
        Passport dbPassport = entityManager.find(Passport.class, 40001L);
        Student dbStudent = dbPassport.getStudent();

        Student testStudent = new Student(20001L, "Ranga", new Passport("E123456"));

        Assertions.assertThat(dbStudent.toString()).isEqualTo(testStudent.toString());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        Student student = entityManager.find(Student.class, 20001L);

        logger.info("student -> {}", student);
        logger.info("courses -> {}", student.getCourses());
    }

}
