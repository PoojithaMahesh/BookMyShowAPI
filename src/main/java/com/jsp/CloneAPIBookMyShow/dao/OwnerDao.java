package com.jsp.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Owner;
import com.jsp.CloneAPIBookMyShow.repository.OwnerRepo;

@Repository
public class OwnerDao {
	
	@Autowired
	private OwnerRepo repo;

	public Owner saveOwner(Owner owner) {
		return repo.save(owner);
		
	}

	public Owner fineOwnerById(long ownerId) {
		Optional<Owner>  optional=repo.findById(ownerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
