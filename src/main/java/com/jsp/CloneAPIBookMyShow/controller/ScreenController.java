package com.jsp.CloneAPIBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(@RequestParam long theatreId,
			@RequestBody ScreenDto screenDto) {
		return service.addScreen(theatreId, screenDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(@RequestParam long screenId,
			@RequestBody ScreenDto dto) {
		return service.updateScreen(screenId, dto);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(@RequestParam long screenId){
		return service.getScreenById(screenId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(@RequestParam long screenId){
		return service.deleteScreenById(screenId);
	}

}
