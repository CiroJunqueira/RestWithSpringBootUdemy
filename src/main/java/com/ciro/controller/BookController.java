package com.ciro.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciro.data.vo.BookVO;
import com.ciro.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController{
	
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
	public BookVO findById(@PathVariable("id") Long id){
		return bookService.findById(id);
	}

	@GetMapping(produces = {"application/json", "application/xml"})
	public List<BookVO>findAll(){
		return bookService.findAll();
		
	}
	
	@PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public BookVO create(@RequestBody BookVO book) {
		return bookService.create(book);
	}
	
	@PutMapping
	public BookVO update(@RequestBody BookVO book) {
		return bookService.update(book);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		bookService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
}