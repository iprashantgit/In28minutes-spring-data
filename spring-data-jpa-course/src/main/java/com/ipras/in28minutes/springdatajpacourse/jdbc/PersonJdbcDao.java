package com.ipras.in28minutes.springdatajpacourse.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", mapPersonFromDb());
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id = ?", mapPersonFromDb(), new Object[]{id});
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id = ?", new Object[]{id});
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person values(?, ?, ?, ?);",
                new Object[]{person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime())});
    }

    public int update(Person person) {
        return jdbcTemplate.update("update person set name = ?, location = ?, date_of_birth=? where id = ?;",
                new Object[]{person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId()});
    }

    private RowMapper<Person> mapPersonFromDb() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String location = resultSet.getString("location");
            Date dob = resultSet.getDate("date_of_birth");
            return new Person(id, name, location, dob);
        };
    }

}
