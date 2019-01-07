package com.springapp.mvc.dto;

import com.springapp.mvc.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tncdg on 6.01.2019.
 */
public class BookDto {

    private String id;
    private String bookname;
    private String authorname;
    private String captchaResponse;
    private boolean hasError=false;
    private List<String> errorMessage=new ArrayList<String>();

    public String getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(String captchaResponse) {
        this.captchaResponse = captchaResponse;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Book createBookForSave(BookDto bookDto){
        Book book=new Book();
        book.setBookname(bookDto.getBookname());
        book.setAuthorname(bookDto.getAuthorname());
        return book;
    }
    public Book createBookForUpdate(BookDto bookDto){
        Book book=new Book();
        book.setId(bookDto.getId());
        book.setBookname(bookDto.getBookname());
        book.setAuthorname(bookDto.getAuthorname());
        return book;
    }
}
