package com.jsp.CloneAPIBookMyShow.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestControllerAdvice
public class BookMyShowExceptionHandler extends ResponseEntityExceptionHandler{
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
       List<ObjectError> list=ex.getAllErrors();
       Map<String, String> map=new HashMap<String, String>();
       for(ObjectError error:list) {
    	   String message=error.getDefaultMessage();
    	   String fieldname=((FieldError)error).getField();
    	   map.put(fieldname, message);
       }
       return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ownerIdNoTFound(OwnerIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for owner");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ScreenIdNoTFound(ScreenIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Screen");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TicketCannotbeCancel(TicketCannotBeCancelledEXception ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Sorry Show is on going so you can't cancel a ticket now");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TicketCannotbeCancel(TicketAlreadyCancelledException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Sorry Ticket already cancelled");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TicketCannotbeCancel(TicketAlreadyExpiredException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Sorry Ticket already Expired");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketIdNoTFound(TicketIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Ticket");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SHowIdNoTFound(ShowIdNOtFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Show");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SeatIdNoTFound(SeatIdNotFoundEXception ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Seat");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ScreenAlreadyAllotted(ScreenAlreadyAlloted ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Screen may be alloted to other shows");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ScreenisnotActive(ShowIsNotActiveException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Show is not active in this time");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MovieIdNoTFound(MovieIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Movie");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreIdNoTFound(TheatreIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Theatre");
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
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNoTFound(AddressIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Id NOt Found for Address");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFound(CustomerIdNotFoundException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Customer Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> TheatrealreadyexistNoTFound(TheatreAlreadyPresentInThisAddressException ex){
		ResponseStructure<String>  structure=new ResponseStructure<String>();
		structure.setMessage("Theatre already present in this address");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
