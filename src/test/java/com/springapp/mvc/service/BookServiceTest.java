package com.springapp.mvc.service;

import com.springapp.mvc.model.Book;
import com.springapp.mvc.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by tncdg on 7.01.2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    MongoTemplate mongoTemplate;
    @Captor
    private ArgumentCaptor< Book >bookCaptor;

    @Test
    public void shouldInsertBook(){

        Book book=new Book();
        book.setAuthorname("Dostoyevski");
        book.setBookname("Suç ve Ceza");
        bookService.insertBook(book);
        verify(bookRepository).insertBook(bookCaptor.capture());
        verify(bookRepository,times(1)).insertBook(book);
        Book capturedBook=bookCaptor.getValue();
        Assert.assertEquals(capturedBook.getAuthorname(),"Dostoyevski");
        Assert.assertEquals(capturedBook.getBookname(),"Suç ve Ceza");
    }

    @Test
    public void shouldGetAllBooks(){

        Book book=new Book();
        when(bookRepository.getAllBooks()).thenReturn(Arrays.asList(book));
        List<Book> books= bookService.getAllBooks();
        Assert.assertEquals(books.size(),1);
        Assert.assertEquals(books.contains(book),true);
    }
    @Test
    public void shouldDeleteBook(){

        bookService.deleteBook("1");
        verify(bookRepository,times(1)).deleteBook("1");

    }

    @Test
    public void shouldUpdateBook(){

        Book book=mock(Book.class);

        when(book.getAuthorname()).thenReturn("Dostoyevski");
        when(book.getBookname()).thenReturn("Suç ve Ceza");
        when(book.getId()).thenReturn("1");

        Query query=new Query(Criteria.where("_id").is(book.getId()));
        when(mongoTemplate.findOne(query, Book.class, "books")).thenReturn(book);
        bookService.updateBook(book);

        verify(bookRepository).updateBook(bookCaptor.capture());
        verify(bookRepository, times(1)).updateBook(book);
        Book updatedBook=bookCaptor.getValue();
        Assert.assertEquals(book.getAuthorname(),updatedBook.getAuthorname());
        Assert.assertEquals(book.getBookname(),updatedBook.getBookname());
        Assert.assertEquals(book.getId(),updatedBook.getId());

    }


}
