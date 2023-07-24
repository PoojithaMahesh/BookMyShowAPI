package com.jsp.CloneAPIBookMyShow.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.jsp.CloneAPIBookMyShow.enums.Genre;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MovieDto {
	private long movieId;
	private String movieName;
//	genresss
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	@DateTimeFormat(style = "HH:mm")
	private LocalDateTime movieDuration;
	private String moviedescription;
	private String language;
}
