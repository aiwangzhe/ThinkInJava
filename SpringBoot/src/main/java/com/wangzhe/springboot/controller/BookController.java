package com.wangzhe.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wangzhe.springboot.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

}
