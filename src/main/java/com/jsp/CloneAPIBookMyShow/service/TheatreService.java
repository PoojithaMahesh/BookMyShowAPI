package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.AddressDao;
import com.jsp.CloneAPIBookMyShow.dao.OwnerDao;
import com.jsp.CloneAPIBookMyShow.dao.TheatreDao;
import com.jsp.CloneAPIBookMyShow.dto.TheatreDto;
import com.jsp.CloneAPIBookMyShow.entity.Address;
import com.jsp.CloneAPIBookMyShow.entity.Owner;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.exception.AddressIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.OwnerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreAlreadyPresentInThisAddressException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class TheatreService {
	@Autowired
	private TheatreDao theatreDao;
	@Autowired
	private OwnerDao  ownerDao;
	@Autowired
	private AddressDao  addressDao;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(long ownerId, long addressId, TheatreDto theatreDto) {
		Owner owner=ownerDao.fineOwnerById(ownerId);
		if(owner!=null) {

			Address address=addressDao.getAddressById(addressId);
			if(address!=null) {
               Theatre addressTheatre=address.getTheatre();
               if(addressTheatre!=null) {
            	   throw new TheatreAlreadyPresentInThisAddressException("Sorry address is mappedto other theatre");
               }
				Theatre theatre=this.modelMapper.map(theatreDto, Theatre.class);
				theatre.setOwner(owner);
				theatre.setAddress(address);
//				update owner
			     if(owner.getTheatres().isEmpty()) {
			    	 List<Theatre> list=new ArrayList<Theatre>();
			    	 list.add(theatre);
			    	 owner.setTheatres(list);
			     }else {
			    	 List<Theatre> list=owner.getTheatres();
			    	 list.add(theatre);
			    	 owner.setTheatres(list);
			     }
//				update address
			     address.setTheatre(theatre);
			     
//			     add theatre
			     Theatre dbTheatre=theatreDao.saveTheatre(theatre);
			     ResponseStructure<Theatre>  structure=new ResponseStructure<Theatre>();
			     structure.setMessage("theatre added successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.CREATED);
			}else {

				throw new AddressIdNotFoundException("Sorry failed to add theatre");
			}
		}else {

			throw new OwnerIdNotFoundException("Sorry failed to add theatre");
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(long theatreId, TheatreDto theatreDto) {
		Theatre theatre=this.modelMapper.map(theatreDto, Theatre.class);
		Theatre dbTheatre=theatreDao.updateTheatre(theatreId,theatre);
		if(dbTheatre!=null) {
			  ResponseStructure<Theatre>  structure=new ResponseStructure<Theatre>();
			     structure.setMessage("theatre Updated successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}else {
//			raise one exception
			throw new TheatreIdNotFoundException("sorry failed to update theatre");
		}
	}
	
	
	
	

}
