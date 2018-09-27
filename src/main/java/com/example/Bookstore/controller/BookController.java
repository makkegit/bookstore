package com.example.Bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.Book;


@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// all
	@RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
  
	// add
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
    
    // save
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }    

    // delete
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
	}

}
