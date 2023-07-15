package com.jsp.CloneAPIBookMyShow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.OwnerDao;
import com.jsp.CloneAPIBookMyShow.dto.OwnerDto;
import com.jsp.CloneAPIBookMyShow.entity.Owner;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;
@Service
public class OwnerService {

	@Autowired
	private OwnerDao ownerDao;
	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(Owner owner) {
		Owner dbOwner=ownerDao.saveOwner(owner);
		OwnerDto dto=new OwnerDto();
		dto.setOwnerId(owner.getOwnerId());
		dto.setOwnerName(owner.getOwnerName());
		dto.setOwnerEmail(owner.getOwnerEmail());
		dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
		
		ResponseStructure<OwnerDto> structure=new ResponseStructure<OwnerDto>();
		structure.setMessage("owner saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<OwnerDto>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<OwnerDto>> findOwnerById(long ownerId) {	
		Owner dbOwner=ownerDao.fineOwnerById(ownerId);
		if(dbOwner!=null) {
			OwnerDto dto=new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());
			
			ResponseStructure<OwnerDto> structure=new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure,HttpStatus.FOUND);
		}else {
//			raise ownerIdnot found exception
			return null;
		}
	}
	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(long ownerId) {
		Owner dbOwner=ownerDao.deleteOwnerById(ownerId);
		if(dbOwner!=null) {
			OwnerDto dto=new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());
			
			ResponseStructure<OwnerDto> structure=new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData Deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure,HttpStatus.FOUND);
		}else {
//			raise ownerIdnot found exception
			return null;
		}
	
	}
	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwner(long ownerId, Owner owner) {
		Owner dbOwner=ownerDao.upadateOwner(ownerId,owner);
		if(dbOwner!=null) {
			OwnerDto dto=new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());
			ResponseStructure<OwnerDto> structure=new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure,HttpStatus.OK);
		}else {
//			raise ownerIdnot found exception
			return null;
		}
	
	}

}
