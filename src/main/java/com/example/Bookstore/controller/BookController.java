package com.example.Bookstore.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class BookController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model) {;
		return "index";
	}

}
