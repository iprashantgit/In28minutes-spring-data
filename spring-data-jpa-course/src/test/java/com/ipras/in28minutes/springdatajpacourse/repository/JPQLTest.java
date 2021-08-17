package com.ipras.in28minutes.springdatajpacourse.repository;

import com.ipras.in28minutes.springdatajpacourse.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void jpql_bsic(){
        // Query query = entityManager.createQuery("select * from Course c");
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select  c  From Course c -> {}",resultList);
    }

    @Test
    public void jpql_typed() {
        // TypedQuery<Course> query = entityManager.createQuery("Select  c  From Course c", Course.class);
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Select  c  From Course c -> {}",resultList);
    }

    @Test
    public void jpql_where() {
        // TypedQuery<Course> query = entityManager.createQuery("Select  c  From Course c where name like '%100 Steps'", Course.class);
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("Select  c  From Course c where name like '%100 Steps'-> {}",resultList);
        //[Course[Web Services in 100 Steps], Course[Spring Boot in 100 Steps]]
    }

}
