package com.ipras.in28minutes.springdatajpacourse.repository;

import com.ipras.in28minutes.springdatajpacourse.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

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

}
