package com.example.Bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

import com.example.Bookstore.domain.Book;


@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// all - login
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	// all
	@RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
	
	//RESTful service to get all books
	@RequestMapping(value="/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}
	
	//RESTful service to get a book by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return brepository.findById(bookId);
	}
	
//	add new book Admin
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("student", new Book());
    	model.addAttribute("departments", crepository.findAll());
        return "addbook";
    }   
     
    
    // save
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }    

    // delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
	}

}
