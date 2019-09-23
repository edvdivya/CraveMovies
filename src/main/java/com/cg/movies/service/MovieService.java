package com.cg.movies.service;

import java.util.List;

import com.cg.movies.dto.Movie;

public interface MovieService {
	public Movie save(Movie novie);
	public List<Movie> findAll();
	public Movie find(Integer movieId);
	public Movie remove(Integer movieId);
}
