package com.ciro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciro.converter.DozerConverter;
import com.ciro.data.model.Book;
import com.ciro.data.vo.BookVO;
import com.ciro.exception.ResourceNotFoundException;
import com.ciro.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll(){
		return DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);
	}
	
	public BookVO findById(Long id) {
		var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	
	public BookVO update(BookVO book) {
		var entity = bookRepository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchdate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		var vo =  DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this id."));
		bookRepository.delete(entity);
	}
}
