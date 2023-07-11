package com.jsp.CloneAPIBookMyShow.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductionHouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productionId;
	private String productionName;
	private LocalDate establishment;
	@ManyToOne
	private Owner owner;
	
	@OneToMany
	private List<Movie> movies;
	
	

}
