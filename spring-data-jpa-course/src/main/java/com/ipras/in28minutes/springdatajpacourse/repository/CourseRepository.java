package com.ipras.in28minutes.springdatajpacourse.repository;

import com.ipras.in28minutes.springdatajpacourse.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }

    public void save(Course course){
        if(course.getId() == null)
            entityManager.persist(course);
        else
            entityManager.merge(course);
    }

    public void deleteById(Long id){
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager(){
        Course course = new Course("Web Service in 100 Steps");
        entityManager.persist(course);

        course.setName(null);
    }

}
