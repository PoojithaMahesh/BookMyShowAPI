package com.jsp.CloneAPIBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.dto.ProductionHouseDto;
import com.jsp.CloneAPIBookMyShow.entity.ProductionHouse;
import com.jsp.CloneAPIBookMyShow.service.ProductionHouseService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/house")
public class ProductionHouseController {
 
	@Autowired
	private ProductionHouseService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProsuctionHouse(@RequestParam long ownerId,@RequestBody ProductionHouseDto houseDto){
		return service.saveProductionHouse(ownerId,houseDto);
	}
}
