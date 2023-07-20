package com.jsp.CloneAPIBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.dto.ScreenDto;
import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.service.ScreenService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/screens")
public class ScreenController {
	@Autowired
	private ScreenService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<Screen>> addScreen(@RequestParam long theatreId,
			@RequestBody ScreenDto screenDto){
		return service.addScreen(theatreId,screenDto);
	}

}
