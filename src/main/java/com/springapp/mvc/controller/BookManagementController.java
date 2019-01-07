package com.springapp.mvc.controller;

import com.springapp.mvc.dto.BookDto;
import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import com.springapp.mvc.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookManagementController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ValidatorService validatorService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "bookManagement";
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody BookDto save(@RequestBody BookDto bookDto, ServletRequest servletRequest){

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		bookDto=validatorService.validateBookSaveFrom(bookDto,request,true);
		if (!bookDto.isHasError()){
			Book book=bookDto.createBookForSave(bookDto);
			bookService.insertBook(book);
		}
		return bookDto;

	}

	@RequestMapping(value="/getAllBooks",method=RequestMethod.GET)
	public @ResponseBody List<Book> getAllBook( ServletRequest request){

		List<Book> books = null;
		try{
			books=	bookService.getAllBooks();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return books;

	}

	@RequestMapping(value="/updateBook",method=RequestMethod.PUT)
	public @ResponseBody BookDto updateBook(@RequestBody BookDto bookDto,ServletRequest servletRequest){

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		bookDto=validatorService.validateBookSaveFrom(bookDto,request,false);
		if (!bookDto.isHasError()){
				Book book=bookDto.createBookForUpdate(bookDto);
				bookService.updateBook(book);
		}
		return bookDto;
	}

	@RequestMapping(value="/deleteBook/{id}",method=RequestMethod.DELETE)
	public @ResponseBody boolean deleteBook(@PathVariable("id") String id){

		boolean result;
		try{
			bookService.deleteBook(id);
			result= true;
		}
		catch (Exception e){
			result= false;
		}
		return result;

	}
}