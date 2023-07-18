package com.jsp.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.repository.TheatreRepo;

@Repository
public class TheatreDao {

	@Autowired
	private TheatreRepo repo;
	
	public Theatre saveTheatre(Theatre theatre) {
		return repo.save(theatre);
	}

	public Theatre updateTheatre(long theatreId, Theatre theatre) {
	Optional<Theatre> optional=repo.findById(theatreId);
	if(optional.isPresent()) {
		Theatre oldTheatre=optional.get();
		theatre.setTheatreId(theatreId);
		theatre.setAddress(oldTheatre.getAddress());
		theatre.setMoviewShows(oldTheatre.getMoviewShows());
		theatre.setOwner(oldTheatre.getOwner());
		theatre.setScreen(oldTheatre.getScreen());
		repo.save(theatre);
		return theatre;
	}return null;
	}
}
