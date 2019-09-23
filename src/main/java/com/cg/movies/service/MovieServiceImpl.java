package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dao.MovieDao;
import com.cg.movies.dao.MovieDaoImpl;
import com.cg.movies.dto.Movie;

public class MovieServiceImpl implements MovieService{

	MovieDao dao = new MovieDaoImpl();
	
	@Override
	public Movie save(Movie novie) {
		
		return dao.save(novie);
	}

	@Override
	public List<Movie> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Movie find(Integer movieId) {
		
		return dao.find(movieId);
	}

	@Override
	public Movie remove(Integer movieId) {
		
		return dao.remove(movieId);
	}

	
}
