package com.jsp.CloneAPIBookMyShow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.ProductionHouse;
import com.jsp.CloneAPIBookMyShow.repository.ProductionHouseRepo;

@Repository
public class ProductionHouseDao {

	@Autowired
	private ProductionHouseRepo repo;

	public ProductionHouse saveProductionHouse(ProductionHouse house) {
		return repo.save(house);
	
	}
}
