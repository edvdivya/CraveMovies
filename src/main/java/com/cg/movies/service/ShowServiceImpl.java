package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dao.ShowDao;
import com.cg.movies.dao.ShowDaoImpl;
import com.cg.movies.dto.Show;

public class ShowServiceImpl implements ShowService{

	ShowDao dao = new ShowDaoImpl();
	
	@Override
	public Show save(Show show) {
		
		return dao.save(show);
	}

	@Override
	public List<Show> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Show find(Integer showId) {
		
		return dao.find(showId);
	}

	@Override
	public Show remove(Integer showId) {
		
		return dao.remove(showId);
	}
	 
}
