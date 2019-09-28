package com.cg.movies.dao;

import java.util.Date;
import java.util.List;

import com.cg.movies.dto.Admin;
import com.cg.movies.dto.Movie;
import com.cg.movies.exception.UserException;

public interface AdminDao {
	
	public boolean addShowToTheatre(Integer showId, Integer theatreId);
	public Admin validateAdminLogin(String userName, String userPass) throws UserException;
	public Admin save(Admin admin);
	public Admin find(Integer adminId);
	public List<Admin> findAll();
	public boolean addMovieToTheatre(Integer movieId, Integer showId, Integer theatreId);
	public Admin remove(Integer adminId);
	public List<Movie> getMovies();
	public List<String> getTheatreByMovieId(Integer movieId);
	Date getReleaseDate(Integer movieID); 
}
