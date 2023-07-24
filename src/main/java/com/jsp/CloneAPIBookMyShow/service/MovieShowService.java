package com.jsp.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.MovieDao;
import com.jsp.CloneAPIBookMyShow.dao.MovieShowDao;
import com.jsp.CloneAPIBookMyShow.dao.ScreenDao;
import com.jsp.CloneAPIBookMyShow.dao.TheatreDao;
import com.jsp.CloneAPIBookMyShow.dto.MovieShowDto;
import com.jsp.CloneAPIBookMyShow.entity.Movie;
import com.jsp.CloneAPIBookMyShow.entity.MoviewShow;
import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.exception.MovieIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ScreenAlreadyAlloted;
import com.jsp.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class MovieShowService {

	@Autowired
	private MovieShowDao showDao;
	@Autowired
	private TheatreDao theatreDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ScreenDao  screenDao;
	@Autowired
	private MovieDao movieDao;

	public ResponseEntity<ResponseStructure<MovieShowDto>> addShow(long theatreId, MovieShowDto showDto) {
		Theatre dbTheatre=theatreDao.getTheatreById(theatreId);
		if(dbTheatre!=null) {
            MoviewShow moviewShow=this.modelMapper.map(showDto, MoviewShow.class);
            long screenId=moviewShow.getScreenId();
            Screen dbScreen=screenDao.getSceenById(screenId);
            if(dbScreen!=null) {
              if( dbScreen.getAvailability().equals("NOT_ALLOTED")) {
//            	  ADD Show
            	  long movieId=moviewShow.getMovieId();
            	  Movie dbMovie=movieDao.getMovieById(movieId);
            	  if(dbMovie!=null) {
//            		  add movie to that show
            		  return null;
//            		  to avoid this error im returning null will change this
            	  }else {
            		  throw new MovieIdNotFoundException("Sorry failed to add show");
            	  }
            	  
              }else{
            	  throw new ScreenAlreadyAlloted("Sorry failed to add show");
              }
            }else {
            	throw new ScreenIdNotFoundException("Sorry failed to add show");
            }
            
		}else {
			throw new TheatreIdNotFoundException("Sorry failed to add show");
		}
		
	}
}
