package com.ipras.in28minutes.springdatajpacourse.repository;

import com.ipras.in28minutes.springdatajpacourse.entity.Course;
import com.ipras.in28minutes.springdatajpacourse.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void addHardcodedReviewsForCourse() {
        //get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review("5", "Great Hands-on Stuff.");
        Review review2 = new Review("5", "Hatsoff.");

        //setting the relationship
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        //save it to the database
        entityManager.persist(review1);
        entityManager.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());
        for(Review review:reviews)
        {
            //setting the relationship
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
        }
    }

}
