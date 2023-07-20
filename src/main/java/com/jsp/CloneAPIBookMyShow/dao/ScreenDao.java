package com.jsp.CloneAPIBookMyShow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.repository.ScreenRepo;

@Repository
public class ScreenDao {

	@Autowired
	private ScreenRepo screenRepo;
	public Screen saveScreen(Screen screen) {
		return screenRepo.save(screen);
	}
}
