package com.ciro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ciro.converter.DozerConverter;
import com.ciro.data.model.Person;
import com.ciro.data.vo.PersonVO;
import com.ciro.exception.ResourceNotFoundException;
import com.ciro.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public List<PersonVO> findAll(Pageable pageable){
		
		var entities = repository.findAll(pageable).getContent();
		
		return DozerConverter.parseListObjects(entities, PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	
	public PersonVO update(PersonVO person) {
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		var vo =  DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		repository.delete(entity);
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePersons(id);
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
}
