package com.jsp.CloneAPIBookMyShow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Movie;
import com.jsp.CloneAPIBookMyShow.repository.MovieRepo;

@Repository
public class MovieDao {

	@Autowired
	private MovieRepo repo;

	public Movie saveMovie(Movie movie) {

		return repo.save(movie);
	}
}
