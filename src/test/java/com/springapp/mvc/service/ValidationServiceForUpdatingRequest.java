package com.springapp.mvc.service;

import com.springapp.mvc.dto.BookDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by tncdg on 7.01.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceForUpdatingRequest {
    @InjectMocks
    ValidatorService validatorService;

    @Mock
    CaptchaService captchaService;

    @Test
    public void shouldValidate(){

        BookDto bookDto=new BookDto();
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname("Suç ve Ceza");
        BookDto validatedBook=validatorService.validateBookSaveFrom(bookDto,httpServletRequest,false);
        Assert.assertEquals(validatedBook.isHasError(), false);

    }

    @Test
    public void shouldFailValidateWhenAuthorNameNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname(null);
        bookDto.setBookname("Suç ve Ceza");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,false);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("authornameErrorMessage",bookDto.getErrorMessage().get(0));

    }

    @Test
    public void shouldFailValidateWhenBookNameNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname(null);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,false);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("bookNameErrorMessage",bookDto.getErrorMessage().get(0));
    }





    @Test
    public void shouldFailValidateWhenBookNameAndAuthorNameNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname(null);
        bookDto.setBookname(null);
        bookDto.setCaptchaResponse("OK");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,false);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("authornameErrorMessage",bookDto.getErrorMessage().get(0));
        Assert.assertEquals("bookNameErrorMessage",bookDto.getErrorMessage().get(1));

    }




}
