package com.jsp.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.repository.ScreenRepo;

@Repository
public class ScreenDao {

	@Autowired
	private ScreenRepo screenRepo;
	public Screen saveScreen(Screen screen) {
		return screenRepo.save(screen);
	}
	public Screen updateScreen(long screenId, Screen screen) {
		Optional<Screen> optional=screenRepo.findById(screenId);
		if(optional.isPresent()) {
			screen.setScreenId(screenId);
			screen.setSeats(optional.get().getSeats());
			screen.setTheatre(optional.get().getTheatre());
			return screenRepo.save(screen);

		}
		return null;
	}
	public Screen getSceenById(long screenId) {
     Optional<Screen> optional=screenRepo.findById(screenId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public Screen deleteScreenById(long screenId) {
		Optional<Screen> optional=screenRepo.findById(screenId);
		if(optional.isPresent()) {
			Screen screen=optional.get();
			screen.setTheatre(null);
			screenRepo.delete(screen);
			return screen;
		}
		return null;
	}
}
