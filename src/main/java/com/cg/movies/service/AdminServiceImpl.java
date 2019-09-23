package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dao.AdminDao;
import com.cg.movies.dao.AdminDaoImpl;
import com.cg.movies.dto.Admin;
import com.cg.movies.exception.UserException;


public class AdminServiceImpl implements AdminService {

	AdminDao dao = new AdminDaoImpl();
	
	@Override
	public boolean addShowToTheatre(Integer showId, Integer theatreId) {
		
		return dao.addShowToTheatre(showId, theatreId);
	}

	@Override
	public boolean addMovieToTheatre(Integer movieId, Integer showId, Integer theatreId) {
		
		return dao.addMovieToTheatre(movieId, showId, theatreId);
	}

	@Override
	public Admin save(Admin admin) {
		
		return dao.save(admin);
	}

	@Override
	public List<Admin> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Admin find(Integer adminId) {
		
		return dao.find(adminId);
	}

	@Override
	public Admin remove(Integer adminId) {
		
		return dao.remove(adminId);
	}
	
	public Admin validateAdminLogin(String userName, String userPass) throws UserException {
		return dao.validateAdminLogin(userName, userPass);
	}


}
