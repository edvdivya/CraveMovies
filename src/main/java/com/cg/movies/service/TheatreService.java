package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dto.Theatre;

public interface TheatreService {

	public Theatre save(Theatre theatre);
	public List<Theatre> findAll();
	public Theatre find(Integer theatreId);
	public Theatre remove(Integer theatreId);
		
}


