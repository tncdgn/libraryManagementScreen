package com.springapp.mvc.dto;

import com.springapp.mvc.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by tncdg on 7.01.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class BookDtoTest {

    @Test
    public void checkValidBookModelCreationForSaving(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname("Suç ve Ceza");

        Book book=bookDto.createBookForSave(bookDto);
        Assert.assertEquals(book.getAuthorname(),bookDto.getAuthorname());
        Assert.assertEquals(book.getBookname(),bookDto.getBookname());


    }

    @Test
    public void checkValidBookModelCreationForUpdating(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname("Suç ve Ceza");
        bookDto.setId("1");
        Book book=bookDto.createBookForUpdate(bookDto);
        Assert.assertEquals(book.getId(),"1");
        Assert.assertEquals(book.getAuthorname(),bookDto.getAuthorname());
        Assert.assertEquals(book.getBookname(),bookDto.getBookname());


    }
}
