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
public class ValidationServiceForSavingRequest {

    @InjectMocks
    ValidatorService validatorService;

    @Mock
    CaptchaService captchaService;

    @Test
    public void shouldValidate(){

        BookDto bookDto=mock(BookDto.class);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(bookDto.getAuthorname()).thenReturn("Dostoyevski");
        when(bookDto.getBookname()).thenReturn("Suç ve Ceza");
        when(bookDto.getCaptchaResponse()).thenReturn("OK");
        when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(true);
        BookDto validatedBook=validatorService.validateBookSaveFrom(bookDto,httpServletRequest,true);
        Assert.assertEquals(validatedBook.isHasError(),false);

    }

    @Test
    public void shouldFailValidateWhenAuthorNameNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname(null);
        bookDto.setBookname("Suç ve Ceza");
        bookDto.setCaptchaResponse("OK");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(true);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("authornameErrorMessage",bookDto.getErrorMessage().get(0));

    }

    @Test
    public void shouldFailValidateWhenBookNameNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname(null);
        bookDto.setCaptchaResponse("OK");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(true);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("bookNameErrorMessage",bookDto.getErrorMessage().get(0));
    }

    @Test
     public void shouldFailValidateWhenCaptchaNotClicked(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname("Suç ve Ceza");
        bookDto.setCaptchaResponse(null);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        // when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(false);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(true,bookDto.isHasError());
        Assert.assertEquals("recaptchaErrorMessage",bookDto.getErrorMessage().get(0));
    }

    @Test
    public void shouldFailValidateWhenCaptchaServerAnswerIsWrong(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname("Dostoyevski");
        bookDto.setBookname("Suç ve Ceza");
        bookDto.setCaptchaResponse("OK");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
         when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(false);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(true,bookDto.isHasError());
        Assert.assertEquals("recaptchaErrorMessage",bookDto.getErrorMessage().get(0));
    }

    @Test
       public void shouldFailValidateWhenBookNameAndAuthorNameNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname(null);
        bookDto.setBookname(null);
        bookDto.setCaptchaResponse("OK");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(true);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("authornameErrorMessage",bookDto.getErrorMessage().get(0));
        Assert.assertEquals("bookNameErrorMessage",bookDto.getErrorMessage().get(1));

    }

    @Test
    public void shouldFailValidateWhenBookNameAndAuthorNameAndCaptchaNull(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname(null);
        bookDto.setBookname(null);
        bookDto.setCaptchaResponse(null);
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(true);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("authornameErrorMessage",bookDto.getErrorMessage().get(0));
        Assert.assertEquals("bookNameErrorMessage",bookDto.getErrorMessage().get(1));
        Assert.assertEquals("recaptchaErrorMessage",bookDto.getErrorMessage().get(2));

    }

    @Test
    public void shouldFailValidateWhenBookNameAndAuthorNameNullAndCaptchaServerAnswerIsWrong(){

        BookDto bookDto=new BookDto();
        bookDto.setAuthorname(null);
        bookDto.setBookname(null);
        bookDto.setCaptchaResponse("OK");
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        when(captchaService.isResponseValid(httpServletRequest,bookDto.getCaptchaResponse())).thenReturn(false);
        bookDto=validatorService.validateBookSaveFrom(bookDto, httpServletRequest,true);
        Assert.assertEquals(bookDto.isHasError(),true);
        Assert.assertEquals("authornameErrorMessage",bookDto.getErrorMessage().get(0));
        Assert.assertEquals("bookNameErrorMessage",bookDto.getErrorMessage().get(1));
        Assert.assertEquals("recaptchaErrorMessage",bookDto.getErrorMessage().get(2));

    }

}
