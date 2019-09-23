package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dao.TheatreDao;
import com.cg.movies.dao.TheatreDaoImpl;
import com.cg.movies.dto.Theatre;

public class TheatreServiceImpl implements TheatreService {
	
	TheatreDao dao = new TheatreDaoImpl();
	
	@Override
	public Theatre save(Theatre theatre) {
		
		return dao.save(theatre);
	}

	@Override
	public List<Theatre> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Theatre find(Integer theatreId) {
		
		return dao.find(theatreId);
	}

	@Override
	public Theatre remove(Integer theatreId) {
		
		return dao.remove(theatreId);
	}

	 
	
}