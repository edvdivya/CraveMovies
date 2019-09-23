package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dto.Show;

public interface ShowService {
	
	public Show save(Show show);
	public List<Show> findAll();
	public Show find(Integer showId);
	public Show remove(Integer showId);
}
