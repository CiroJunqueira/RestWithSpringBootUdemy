package com.ciro.data.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	
	private String author;
	
	
	private Date launchdate;
	
	
	private Double price;
	
	
	private String title;
	

}
