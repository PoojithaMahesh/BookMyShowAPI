package com.jsp.CloneAPIBookMyShow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestControllerAdvice
public class BookMyShowExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ownerIdNoTFound(OwnerIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for owner");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> houseIdNoTFound(ProductionHouseIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for ProductionHouse");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
}
