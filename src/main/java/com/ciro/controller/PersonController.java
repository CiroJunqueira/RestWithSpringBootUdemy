package com.ciro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ciro.data.vo.PersonVO;
import com.ciro.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController{
	
	@Autowired
	private PersonService personService;

	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
	public PersonVO findById(@PathVariable("id") Long id){
		return personService.findById(id);
	}

	@GetMapping(produces = {"application/json", "application/xml"})
	public List<PersonVO>findAll(@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "3") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		return personService.findAll(pageable);
		
	}
	
	@PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	public PersonVO create(@RequestBody PersonVO person) {
		return personService.create(person);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) {
		return personService.update(person);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping(value = "/{id}", produces = {"application/json", "application/xml"})
	public PersonVO disablePerson(@PathVariable("id") Long id){
		return personService.disablePerson(id);
	}
	
	
}