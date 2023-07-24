package com.jsp.CloneAPIBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.dto.MovieShowDto;
import com.jsp.CloneAPIBookMyShow.dto.TheatreDto;
import com.jsp.CloneAPIBookMyShow.service.MovieShowService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/show")
public class MovieShowController {
	@Autowired
    private MovieShowService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<MovieShowDto>> saveShow(@RequestParam long theatreId,@RequestBody MovieShowDto showDto){
		return service.addShow(theatreId,showDto);
	}
}
