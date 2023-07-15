package com.jsp.CloneAPIBookMyShow.service;

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
         house.setOwner(dbOwner);
        ProductionHouse dbProductionHouse= houseDao.saveProductionHouse(house);
        ResponseStructure<ProductionHouse> structure=new ResponseStructure<ProductionHouse>();
        structure.setMessage("ProductionHouse Added Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(dbProductionHouse);
        return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure,HttpStatus.CREATED);
			
		}else {
//			Raise one exception ownerIdisnot present
			return null;
		}
	}
	

}
