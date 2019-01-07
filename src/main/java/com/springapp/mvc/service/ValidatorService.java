package com.springapp.mvc.service;

import com.springapp.mvc.dto.BookDto;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tncdg on 6.01.2019.
 */
@Component
public class ValidatorService {

    @Autowired
   private CaptchaService captchaService;

    public BookDto validateBookSaveFrom(BookDto bookDto,HttpServletRequest  httpServletRequest,boolean isAddBook) {

        List<String> messageName=new ArrayList<String>();
        if (bookDto.getAuthorname()==null || bookDto.getAuthorname().trim().length() == 0) {
            bookDto.setHasError(true);
            messageName.add("authornameErrorMessage");
        }
        if (bookDto.getBookname()==null || bookDto.getBookname().trim().length() == 0) {
            messageName.add("bookNameErrorMessage");
            bookDto.setHasError(true);
        }


        if (isAddBook){
            String reCaptchaResponse=bookDto.getCaptchaResponse();

            if (reCaptchaResponse!=null){
                boolean isValidCapcha=captchaService.isResponseValid(httpServletRequest,reCaptchaResponse);
                if (!isValidCapcha){
                    messageName.add("recaptchaErrorMessage");
                    bookDto.setHasError(true);
                }
            }
            else {
                bookDto.setHasError(true);
                messageName.add("recaptchaErrorMessage");

            }
        }

        if (messageName.size()>0){
            bookDto.setErrorMessage(messageName);
        }
        return bookDto;
    }


}
