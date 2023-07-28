package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.BookingDao;
import com.jsp.CloneAPIBookMyShow.dao.CustomerDao;
import com.jsp.CloneAPIBookMyShow.dao.MovieShowDao;
import com.jsp.CloneAPIBookMyShow.dao.SeatDao;
import com.jsp.CloneAPIBookMyShow.dao.TicketDao;
import com.jsp.CloneAPIBookMyShow.entity.Booking;
import com.jsp.CloneAPIBookMyShow.entity.Customer;
import com.jsp.CloneAPIBookMyShow.entity.MoviewShow;
import com.jsp.CloneAPIBookMyShow.entity.Seat;
import com.jsp.CloneAPIBookMyShow.entity.Ticket;
import com.jsp.CloneAPIBookMyShow.enums.BookingStatus;
import com.jsp.CloneAPIBookMyShow.enums.SeatType;
import com.jsp.CloneAPIBookMyShow.enums.ShowStatus;
import com.jsp.CloneAPIBookMyShow.enums.TicketStatus;
import com.jsp.CloneAPIBookMyShow.exception.CustomerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.SeatIdNotFoundEXception;
import com.jsp.CloneAPIBookMyShow.exception.ShowIdNOtFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ShowIsNotActiveException;
import com.jsp.CloneAPIBookMyShow.exception.TicketAlreadyCancelledException;
import com.jsp.CloneAPIBookMyShow.exception.TicketAlreadyExpiredException;
import com.jsp.CloneAPIBookMyShow.exception.TicketCannotBeCancelledEXception;
import com.jsp.CloneAPIBookMyShow.exception.TicketIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class TicketService {
@Autowired
private TicketDao ticketDao;
@Autowired
private CustomerDao customerDao;
@Autowired
private MovieShowDao showDao;
@Autowired
private SeatDao seatDao;
@Autowired
private BookingDao bookingDao;

public ResponseEntity<ResponseStructure<Ticket>> saveTicket(long customerId, long showId, long seatId) {
Customer dbCustomer=customerDao.getCustomerById(customerId);
Ticket ticket=new Ticket();
if (dbCustomer != null) {
	ticket.setCustomer(dbCustomer);
} else {
	throw new CustomerIdNotFoundException("Sorry failed to book ticket");
}
MoviewShow dbMoviewShow=showDao.getShowById(showId);
if(dbMoviewShow!=null) {
	if(dbMoviewShow.getShowStatus().equals(ShowStatus.ACTIVE)) {
		ticket.setMoviewShow(dbMoviewShow);
	}else {
		throw new ShowIsNotActiveException("Sorry failed to book ticket");
	}
}else {
	throw new ShowIdNOtFoundException("Soryy failed to book ticket");
}
List<Booking> bookings=new ArrayList<Booking>();
List<Seat> seats=new ArrayList<Seat>();
double totalprice=0;
Seat dbSeat=seatDao.getSeatById(seatId);
if(dbSeat!=null) {
	Booking booking=new Booking();
	booking.setSeatId(dbSeat.getSeatId());
	booking.setSeatType(dbSeat.getSeatType());
	booking.setStatus(BookingStatus.ACTIVE);
	booking.setBookingFromTime(dbMoviewShow.getShowStartTime());
	booking.setBookingTillTime(dbMoviewShow.getShowEndTime());
	
	SeatType seatType=booking.getSeatType();
	switch(seatType) {
	case CLASSIC:
		booking.setSeatprice(dbMoviewShow.getClassicSeatPrice());
		totalprice+=dbMoviewShow.getClassicSeatPrice();
		break;
		
	case GOLD:
		booking.setSeatprice(dbMoviewShow.getGoldSeatPrice());
		totalprice+=dbMoviewShow.getGoldSeatPrice();
		break;
		
	case PLATINUM:
		booking.setSeatprice(dbMoviewShow.getPremiumSeatPrice());
		totalprice+=dbMoviewShow.getPremiumSeatPrice();
		break;
		
	}
	bookings.add(booking);
	seats.add(dbSeat);
	 bookingDao.saveBookig(booking);
	
	 
	 ticket.setBookings(bookings);
     ticket.setTotalPrice(totalprice);
     ticket.setTicketStatus(TicketStatus.ACTIVE);
     Ticket dbTicket=ticketDao.saveTticket(ticket);
     ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
     structure.setMessage("Ticket Booked successfully");
     structure.setStatus(HttpStatus.CREATED.value());
     structure.setData(dbTicket);
     return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.CREATED);
     
	 
}else {
	throw new SeatIdNotFoundEXception("sorry failed to book ticket");
}
}

public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(long ticketId) {
   Ticket dbTicket=ticketDao.getTicketById(ticketId);
   if(dbTicket!=null) {
      if(dbTicket.getMoviewShow().getShowStatus().equals(ShowStatus.ON_GOING)) {
    	  throw new TicketCannotBeCancelledEXception("Sorry failed to cancel ticket");
      }else {
    	  if(dbTicket.getTicketStatus().equals(TicketStatus.EXPIRED)) {
    		  throw new TicketAlreadyExpiredException("Sorry failed to cancel ticket");
    	
    	  }else {
    		  if(dbTicket.getTicketStatus().equals(TicketStatus.CANCELLED)) {
    			  throw new TicketAlreadyCancelledException("Sorry failed to cancel ticket");
    		  }else {
    			  List<Booking> bookings=dbTicket.getBookings();
    			  for(Booking b:bookings) {
    				  b.setStatus(BookingStatus.CANCELLED);
    				  bookingDao.saveBookig(b);
    			  }
    			  dbTicket.setTicketStatus(TicketStatus.CANCELLED);
    			  ticketDao.saveTticket(dbTicket);
    			  ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
    			  structure.setMessage("Ticket cancelled successfullu");
    			  structure.setStatus(HttpStatus.FOUND.value());
    			  structure.setData(dbTicket);
    			  return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.FOUND);
    		  }
    	  }}     
   }else {
	   throw new TicketIdNotFoundException("Sorry failed to cancel ticket");
   }
}
}
