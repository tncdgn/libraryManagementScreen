package com.springapp.mvc.repository;

import com.springapp.mvc.model.Book;
import com.springapp.mvc.repositoryImpl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tncdg on 6.01.2019.
 */
@Repository
public class BookRepository implements BookRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertBook(Book book) {
        if (!mongoTemplate.collectionExists(Book.class)) {
            mongoTemplate.createCollection(Book.class);
        }
        mongoTemplate.insert(book, "books");
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> list = mongoTemplate.findAll(Book.class, "books");
        return list;
    }

    public void deleteBook(String id) {
        for (Book book : getAllBooks()) {
            if (book.getId().equals(id)) {
                mongoTemplate.remove(book, "books");
                break;
            }
        }
    }

    public void updateBook(Book book) {
        mongoTemplate.save(book, "books");
    }
}
