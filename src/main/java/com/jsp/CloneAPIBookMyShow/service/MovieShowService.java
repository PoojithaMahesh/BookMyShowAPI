package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.jsp.CloneAPIBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneAPIBookMyShow.exception.MovieIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ScreenAlreadyAlloted;
import com.jsp.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.ShowIdNOtFoundException;
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

	public ResponseEntity<ResponseStructure<MoviewShow>> addShow(long theatreId, MovieShowDto showDto) {
		Theatre dbTheatre=theatreDao.getTheatreById(theatreId);
		if(dbTheatre!=null) {
            MoviewShow moviewShow=this.modelMapper.map(showDto, MoviewShow.class);
            
            long screenId=moviewShow.getScreenId();
            Screen dbScreen=screenDao.getSceenById(screenId);
            if(dbScreen!=null) {
              if( dbScreen.getAvailability().equals(ScreenAvailability.NOT_ALLOTED)) {
//            	  ADD Show
            	  long movieId=moviewShow.getMovieId();
            	  Movie dbMovie=movieDao.getMovieById(movieId);
            	  if(dbMovie!=null) {
            		 moviewShow.setMovieDescription(dbMovie.getMoviedescription());
            		 moviewShow.setMovieDuration(dbMovie.getMovieDuration());
            		 moviewShow.setMovieLanguage(dbMovie.getLanguage());
            		 moviewShow.setMovieName(dbMovie.getMovieName());
            		 moviewShow.setScreeName(dbScreen.getScreenName());
            		 moviewShow.setTheatre(dbTheatre);
            		 MoviewShow  dbShow=showDao.addShow(moviewShow);
            		if(dbTheatre.getMoviewShows().isEmpty()) {
//            			this is the first show
            			List<MoviewShow> list=new ArrayList<MoviewShow>();
            			list.add(moviewShow);
            			dbTheatre.setMoviewShows(list);
            			theatreDao.updateTheatre(theatreId, dbTheatre);
            		}else {
//            			show already present
            			List<MoviewShow> list=dbTheatre.getMoviewShows();
            			list.add(moviewShow);
            			dbTheatre.setMoviewShows(list);
            			theatreDao.updateTheatre(theatreId, dbTheatre);
            		}
            		ResponseStructure<MoviewShow> structure=new ResponseStructure<MoviewShow>();
            		structure.setMessage("added show successfully");
            		structure.setStatus(HttpStatus.CREATED.value());
            		structure.setData(dbShow);
            		return new ResponseEntity<ResponseStructure<MoviewShow>>(structure,HttpStatus.CREATED);
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

	public ResponseEntity<ResponseStructure<MoviewShow>> updateSHow(long showId, MovieShowDto showDto) {
		MoviewShow moviewShow=this.modelMapper.map(showDto, MoviewShow.class );
		MoviewShow dbMoviewShow=showDao.updateShow(showId,moviewShow);
		if(dbMoviewShow!=null) {
			ResponseStructure<MoviewShow> structure=new ResponseStructure<MoviewShow>();
    		structure.setMessage("Updated show successfully");
    		structure.setStatus(HttpStatus.CREATED.value());
    		structure.setData(dbMoviewShow);
    		return new ResponseEntity<ResponseStructure<MoviewShow>>(structure,HttpStatus.CREATED);
		}else {
			throw new ShowIdNOtFoundException("Sorry failed to update Show");
		}
		
		
	}

	public ResponseEntity<ResponseStructure<MoviewShow>> getShowById(long showId) {
		MoviewShow dbMoviewShow=showDao.getShowById(showId);
		if(dbMoviewShow!=null) {
			ResponseStructure<MoviewShow> structure=new ResponseStructure<MoviewShow>();
    		structure.setMessage("show data fetched successfully");
    		structure.setStatus(HttpStatus.FOUND.value());
    		structure.setData(dbMoviewShow);
    		return new ResponseEntity<ResponseStructure<MoviewShow>>(structure,HttpStatus.FOUND);
		}else {
			throw new ShowIdNOtFoundException("Sorry failed to get Show");
		}
	}

	public ResponseEntity<ResponseStructure<MovieShowDto>> deleteShowById(long showId) {
		MoviewShow dbMoviewShow=showDao.deleteShowById(showId);
		if(dbMoviewShow!=null) {
			ResponseStructure<MovieShowDto> structure=new ResponseStructure<MovieShowDto>();
    		structure.setMessage("show data fetched successfully");
    		structure.setStatus(HttpStatus.FOUND.value());
    		structure.setData(dbMoviewShow);
    		return new ResponseEntity<ResponseStructure<MovieShowDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new ShowIdNOtFoundException("Sorry failed to Delete Show");
		}
	}
}
