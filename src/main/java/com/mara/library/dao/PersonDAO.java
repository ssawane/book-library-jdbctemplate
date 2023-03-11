package com.mara.library.dao;

import com.mara.library.models.Book;
import com.mara.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name}, new PersonMapper())
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, date_of_birth) VALUES (?, ?)",
                person.getName(), person.getDateOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, date_of_birth=? WHERE person_id=?",
                person.getName(), person.getDateOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public List<Book> bookList(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id}, new BookMapper());
    }
}
