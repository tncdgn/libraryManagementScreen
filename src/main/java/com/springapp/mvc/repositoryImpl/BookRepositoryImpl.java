package com.springapp.mvc.repositoryImpl;

import com.springapp.mvc.model.Book;

import java.util.List;

/**
 * Created by tncdg on 6.01.2019.
 */

public interface BookRepositoryImpl {

    public void insertBook(Book book);
    public List<Book> getAllBooks();
    public void deleteBook(String id);
    public void updateBook(Book book);
}
