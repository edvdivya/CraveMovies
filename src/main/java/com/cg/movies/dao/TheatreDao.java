package com.cg.movies.dao;

import java.util.List;

import com.cg.movies.dto.Theatre;

public interface TheatreDao {

	public Theatre save(Theatre theatre);
	public List<Theatre> findAll();
	public Theatre find(Integer theatreId);
	public Theatre remove(Integer theatreId);
		
}