package com.springapp.mvc.bookcontroller;

import com.springapp.mvc.controller.BookManagementController;
import com.springapp.mvc.dto.BookDto;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.repository.BookRepository;
import com.springapp.mvc.service.BookService;
import com.springapp.mvc.service.CaptchaService;
import com.springapp.mvc.service.ValidatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Mockito.*;

/**
 * Created by tncdg on 7.01.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    BookManagementController bookManagementController;

    @Mock
    ValidatorService validatorService;

    @Mock
    CaptchaService captchaService;

    @Mock
    BookService bookService;

    @Mock
    BookRepository bookRepository;



    @Test
    public void shouldAddBookIfValadionIsOk(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn("Dostoyevski");
        when(bookDto.getBookname()).thenReturn("Suç ve Ceza");
        when(bookDto.getCaptchaResponse()).thenReturn("OK");
        when(bookDto.isHasError()).thenReturn(false);
       // when(captchaService.isResponseValid(httpServletRequest, bookDto.getCaptchaResponse())).thenReturn(true);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, true)).thenReturn(bookDto);
        bookDto=bookManagementController.save(bookDto,httpServletRequest);
        verify(bookService,times(1)).insertBook(any(Book.class));
        Assert.assertEquals(bookDto.isHasError(),false);

    }

    @Test
    public void shouldNotAddBookIfValadionIsNotOk(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn(null);
        when(bookDto.getBookname()).thenReturn(null);
        when(bookDto.getCaptchaResponse()).thenReturn(null);
        when(bookDto.isHasError()).thenReturn(true);
        // when(captchaService.isResponseValid(httpServletRequest, bookDto.getCaptchaResponse())).thenReturn(true);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, true)).thenReturn(bookDto);
        bookDto=bookManagementController.save(bookDto,httpServletRequest);
        verify(bookService,never()).insertBook(any(Book.class));
        Assert.assertEquals(bookDto.isHasError(),true);

    }

    @Test
    public void shouldNotAddBookIfAuthorNameIsNull(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn(null);
        when(bookDto.getBookname()).thenReturn("Suç ve Ceza");
        when(bookDto.getCaptchaResponse()).thenReturn("OK");
        when(bookDto.isHasError()).thenReturn(true);
        // when(captchaService.isResponseValid(httpServletRequest, bookDto.getCaptchaResponse())).thenReturn(true);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, true)).thenReturn(bookDto);
        bookDto=bookManagementController.save(bookDto,httpServletRequest);
        verify(bookService,never()).insertBook(any(Book.class));
        Assert.assertEquals(bookDto.getAuthorname(),null);
        Assert.assertEquals(bookDto.isHasError(),true);

    }

    @Test
    public void shouldNotAddBookIfBookNameIsNull(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn("Dostoyevski");
        when(bookDto.getBookname()).thenReturn(null);
        when(bookDto.getCaptchaResponse()).thenReturn("OK");
        when(bookDto.isHasError()).thenReturn(true);
        // when(captchaService.isResponseValid(httpServletRequest, bookDto.getCaptchaResponse())).thenReturn(true);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, true)).thenReturn(bookDto);
        bookDto=bookManagementController.save(bookDto,httpServletRequest);
        verify(bookService,never()).insertBook(any(Book.class));
        Assert.assertEquals(bookDto.getBookname(),null);
        Assert.assertEquals(bookDto.isHasError(),true);

    }

    @Test
    public void shouldNotAddBookIfCaptchaIsNotClicked(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn("Dostoyevski");
        when(bookDto.getBookname()).thenReturn(null);
        when(bookDto.getCaptchaResponse()).thenReturn(null);
        when(bookDto.isHasError()).thenReturn(true);
        // when(captchaService.isResponseValid(httpServletRequest, bookDto.getCaptchaResponse())).thenReturn(true);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, true)).thenReturn(bookDto);
        bookDto=bookManagementController.save(bookDto,httpServletRequest);
        verify(bookService,never()).insertBook(any(Book.class));
        Assert.assertEquals(bookDto.getBookname(),null);
        Assert.assertEquals(bookDto.isHasError(),true);

    }

    @Test
    public void shouldGetAllBooks(){

        Book book=new Book();
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book));
        List<Book> bookList=bookManagementController.getAllBook(httpServletRequest);
        Assert.assertEquals(bookList.size(),1);
        Assert.assertEquals(bookList.contains(book),true);

    }

    @Test
    public void shouldUpdateBook(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn("Dostoyevski");
        when(bookDto.getBookname()).thenReturn("Suç ve Ceza");
        when(bookDto.getCaptchaResponse()).thenReturn("OK");
        when(bookDto.isHasError()).thenReturn(false);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, false)).thenReturn(bookDto);
        bookDto=bookManagementController.updateBook(bookDto, httpServletRequest);

        verify(bookService,times(1)).updateBook(any(Book.class));

        Assert.assertEquals(bookDto.isHasError(),false);

    }

    @Test
    public void shouldNotUpdateBook(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn(null);
        when(bookDto.getBookname()).thenReturn("Suç ve Ceza");
        when(bookDto.getCaptchaResponse()).thenReturn("OK");
        when(bookDto.isHasError()).thenReturn(true);
        when(validatorService.validateBookSaveFrom(bookDto, httpServletRequest, false)).thenReturn(bookDto);
        bookDto=bookManagementController.updateBook(bookDto,httpServletRequest);

        verify(bookService,never()).updateBook(any(Book.class));

        Assert.assertEquals(bookDto.isHasError(),true);

    }

    @Test
    public void shouldDeleteBook(){

        boolean result=  bookManagementController.deleteBook("1");

        verify(bookService,times(1)).deleteBook(any(String.class));

        Assert.assertEquals(result,true);

    }


    @Test
    public void shouldNotDeleteBook(){

        doThrow(Exception.class) .when(bookService) .deleteBook(anyString());
        boolean result= bookManagementController.deleteBook("1");
        Assert.assertEquals(result,false);

    }
}
