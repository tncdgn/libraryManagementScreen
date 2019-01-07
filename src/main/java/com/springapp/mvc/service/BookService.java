package com.springapp.mvc.service;

import com.springapp.mvc.repository.BookRepository;
import com.springapp.mvc.repositoryImpl.BookRepositoryImpl;
import com.springapp.mvc.serviceImpl.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.springapp.mvc.model.Book;

import java.util.List;

/**
 * Created by tncdg on 6.01.2019.
 */
@Service
public class BookService implements BookServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertBook(Book book) {
        bookRepository.insertBook(book);

    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books=bookRepository.getAllBooks();
        return books;
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        Query query=new Query(Criteria.where("_id").is(book.getId()));
        Book book1=mongoTemplate.findOne(query,Book.class,"books");
        book1.setAuthorname(book.getAuthorname());
        book1.setBookname(book.getBookname());
        bookRepository.updateBook(book);
    }
}
