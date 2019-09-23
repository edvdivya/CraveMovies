package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dto.Admin;
import com.cg.movies.exception.UserException;

public interface AdminService {
	public Admin save(Admin admin);
	public List<Admin> findAll();
	public Admin find(Integer adminId);
	public Admin remove(Integer adminId);
	
	public boolean addShowToTheatre(Integer showId, Integer theatreId);
	public boolean addMovieToTheatre(Integer movieId, Integer showId, Integer theatreId);
	public Admin validateAdminLogin(String userName, String userPass) throws UserException;
}
