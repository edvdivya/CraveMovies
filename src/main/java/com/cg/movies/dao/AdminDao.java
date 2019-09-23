package com.cg.movies.dao;

import java.util.List;

import com.cg.movies.dto.Admin;
import com.cg.movies.exception.UserException;

public interface AdminDao {
	
	public boolean addShowToTheatre(Integer showId, Integer theatreId);
	public Admin validateAdminLogin(String userName, String userPass) throws UserException;
	public Admin save(Admin admin);
	public Admin find(Integer adminId);
	public List<Admin> findAll();
	public boolean addMovieToTheatre(Integer movieId, Integer showId, Integer theatreId);
	public Admin remove(Integer adminId); 
}
