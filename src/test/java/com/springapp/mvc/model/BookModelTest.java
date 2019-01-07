package com.springapp.mvc.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by tncdg on 7.01.2019.
 */
   @RunWith(MockitoJUnitRunner.class)
   @WebAppConfiguration
   @ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class BookModelTest {

    @Test
    public void returnTrueWhenAuthorHasValue(){

        Book book1=mock(Book.class);
        when(book1.getAuthorname()).thenReturn("tunc");
        Assert.assertEquals(book1.getAuthorname(),"tunc");

    }

    @Test
    public void returnTrueWhenBookNameHasValue(){

        Book book1=mock(Book.class);
        when(book1.getBookname()).thenReturn("tunc");
        Assert.assertEquals(book1.getBookname(),"tunc");

    }
}
