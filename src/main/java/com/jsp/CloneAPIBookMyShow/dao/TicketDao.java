package com.jsp.CloneAPIBookMyShow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Ticket;
import com.jsp.CloneAPIBookMyShow.repository.TicketRepo;

@Repository
public class TicketDao {
@Autowired
private TicketRepo repo;

public Ticket saveTticket(Ticket ticket) {
	
	return repo.save(ticket);
}

}
