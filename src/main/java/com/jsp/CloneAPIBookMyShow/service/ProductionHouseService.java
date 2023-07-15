package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.OwnerDao;
import com.jsp.CloneAPIBookMyShow.dao.ProductionHouseDao;
import com.jsp.CloneAPIBookMyShow.dto.ProductionHouseDto;
import com.jsp.CloneAPIBookMyShow.entity.Owner;
import com.jsp.CloneAPIBookMyShow.entity.ProductionHouse;
import com.jsp.CloneAPIBookMyShow.exception.OwnerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ProductionHouseIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class ProductionHouseService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductionHouseDao houseDao;
	@Autowired
	private OwnerDao ownerDao;
	
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(long ownerId, ProductionHouseDto houseDto){
		Owner dbOwner=ownerDao.fineOwnerById(ownerId);
		if(dbOwner!=null) {	
		  ProductionHouse house=this.modelMapper.map(houseDto, ProductionHouse.class);
     	if(dbOwner.getHouses().isEmpty()) {
			List<ProductionHouse> list=new ArrayList<ProductionHouse>();
			list.add(house);
			dbOwner.setHouses(list);	
		}else {
			List<ProductionHouse> list=dbOwner.getHouses();
			list.add(house);
			dbOwner.setHouses(list);
		
		}
     
        house.setOwner(dbOwner);
         
        ProductionHouse dbProductionHouse= houseDao.saveProductionHouse(house);
        ResponseStructure<ProductionHouse> structure=new ResponseStructure<ProductionHouse>();
        structure.setMessage("ProductionHouse Added Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(dbProductionHouse);
        return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.CREATED);
			
		}else {
//			Raise one exception ownerIdisnot present
			throw new OwnerIdNotFoundException("Sorry failed to add productionHouse");
		}
	}
	
	

	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(long houseId,
			ProductionHouseDto houseDto) {
		ProductionHouse house=this.modelMapper.map(houseDto, ProductionHouse.class);
		
		ProductionHouse dbHouse=houseDao.updateProductionHouse(houseId,house);
		if(dbHouse!=null) {
			ResponseStructure<ProductionHouse> structure=new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHouse Update successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.OK);
		}else {
			throw new  ProductionHouseIdNotFoundException("Sorry failed to update ProductionHouse");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
