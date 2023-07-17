package com.jsp.CloneAPIBookMyShow.dao;

import java.util.Optional;

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

	public ProductionHouse updateProductionHouse(long houseId, ProductionHouse house) {
		Optional<ProductionHouse> optional=repo.findById(houseId);
		if(optional.isPresent()) {
//			update the data
			house.setProductionId(houseId);
			house.setOwner(optional.get().getOwner());
			house.setMovies(optional.get().getMovies());
			repo.save(house);
			return house;
		}
		
		return null;
	}

	public ProductionHouse getProductionHouseById(long houseId) {
		Optional<ProductionHouse> optional=repo.findById(houseId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public ProductionHouse deleteProductionHouseById(long houseId) {
		Optional<ProductionHouse> optional=repo.findById(houseId);
		if(optional.isPresent()) {
		ProductionHouse house=optional.get();
		
		house.setOwner(null);
		house.setMovies(null);
		
		repo.delete(house);
		return optional.get();
		
		}
		return null;
	}
}
