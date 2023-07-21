package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.ScreenDao;
import com.jsp.CloneAPIBookMyShow.dao.TheatreDao;
import com.jsp.CloneAPIBookMyShow.dto.ScreenDto;
import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.entity.Seat;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneAPIBookMyShow.enums.ScreenStatus;
import com.jsp.CloneAPIBookMyShow.enums.SeatType;
import com.jsp.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

import net.bytebuddy.asm.Advice.This;

@Service
public class ScreenService {

	@Autowired
	private ScreenDao screenDao;
	@Autowired
	private TheatreDao theatreDao;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(long theatreId, ScreenDto screenDto) {
		Theatre theatre = theatreDao.getTheatreById(theatreId);
		if (theatre != null) {
			Screen screen = this.modelMapper.map(screenDto, Screen.class);
//             screen variable you are having no of classic seat ,gold,premium seat
//             screen is having seat object???not present and i want to add it
//             screen is having theatre ?no but we are having theatre object themn i will set it(theatre)

			List<Seat> seats = new ArrayList<Seat>();
			for (int a = screen.getNoOfClassicSeat(); a > 0; a--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.CLASSIC);
				seat.setScreen(screen);
				seats.add(seat);

			}
			for (int b = screen.getNoOfPlatinumSeat(); b > 0; b--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.PLATINUM);
				seat.setScreen(screen);
				seats.add(seat);

			}
			for (int c = screen.getNoOfGoldSeat(); c > 0; c--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.GOLD);
				seat.setScreen(screen);
				seats.add(seat);

			}
			screen.setTheatre(theatre);
			screen.setSeats(seats);
			screen.setAvailability(ScreenAvailability.NOT_ALLOTED);
			screen.setStatus(ScreenStatus.AVAILABLE);
			Screen dbsScreen = screenDao.saveScreen(screen);
//            update the theatre
			if (theatre.getScreen().isEmpty()) {
				List<Screen> screens = new ArrayList<Screen>();
				screens.add(dbsScreen);
				theatre.setScreen(screens);
				theatreDao.updateTheatre(theatreId, theatre);
			} else {
				List<Screen> screens = theatre.getScreen();
				screens.add(dbsScreen);
				theatre.setScreen(screens);
				theatreDao.updateTheatre(theatreId, theatre);
			}
			ScreenDto dto = this.modelMapper.map(dbsScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new TheatreIdNotFoundException("failed to add screen");
		}

	}

	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(long screenId, ScreenDto dto) {
		Screen screen = this.modelMapper.map(dto, Screen.class);
		Screen dbScreen = screenDao.updateScreen(screenId, screen);
		if (dbScreen != null) {
			ScreenDto dto1 = this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Updated successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto1);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new ScreenIdNotFoundException("Sorry failed to update screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(long screenId) {
		Screen dbScreen=screenDao.getSceenById(screenId);
		if(dbScreen!=null) {
			ScreenDto dto1=this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure=new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto1);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new ScreenIdNotFoundException("Sorry failed to get screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(long screenId) {
		Screen dbScreen=screenDao.deleteScreenById(screenId);
		if(dbScreen!=null) {
			ScreenDto dto1=this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure=new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto1);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new ScreenIdNotFoundException("Sorry failed to Delete screen");
		}
	}


	

}
