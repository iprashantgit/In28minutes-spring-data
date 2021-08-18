package com.ipras.in28minutes.springdatajpacourse.repository;

import com.ipras.in28minutes.springdatajpacourse.entity.Course;
import com.ipras.in28minutes.springdatajpacourse.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic(){
        Course course = courseRepository.findById(10001L);
        assertThat(course.getName()).isEqualTo("JPA in 50 Steps");
    }

    @Test
    @DirtiesContext
    public void deleteById_basic(){
        courseRepository.deleteById(10002L);
        assertThat(courseRepository.findById(10002L)).isNull();
    }

    @Test
    @DirtiesContext
    public void save_basic() {

        // get a course
        Course course = courseRepository.findById(10001L);
        assertThat(course.getName()).isEqualTo("JPA in 50 Steps");

        // update details
        course.setName("JPA in 50 Steps - Updated");
        courseRepository.save(course);

        // check the value
        Course course1 = courseRepository.findById(10001L);
        assertThat(course1.getName()).isEqualTo("JPA in 50 Steps - Updated");
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("{}",course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        logger.info("{}",review.getCourse());
    }

}
