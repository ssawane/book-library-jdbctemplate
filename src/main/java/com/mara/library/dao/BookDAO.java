package com.mara.library.dao;

import com.mara.library.models.Book;
import com.mara.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author, year, person_id) VALUES (?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear(), null);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete( int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public List<Person> personList() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public void makeRent(Person person, int id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person.getId(), id);
    }

    public void unRent(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?", id);
    }

    public Optional<Person> bookRenter(int id) {
        return jdbcTemplate.query("SELECT person.* FROM person INNER JOIN book USING (person_id) WHERE book_id=?", new Object[]{id},
                new PersonMapper()).stream().findAny();
    }

}
