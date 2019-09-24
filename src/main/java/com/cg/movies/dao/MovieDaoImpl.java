package com.cg.movies.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.cg.movies.dto.Movie;

public class MovieDaoImpl implements MovieDao{

	public static int flag=0;
	EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("Movies");
	
	@Transactional
	public Movie save(Movie movie) {
		
		EntityManager em = entityFactory.createEntityManager();
			EntityTransaction tran=em.getTransaction();
			tran.begin();
			em.persist(movie);
			movie.setFlag(0);
			tran.commit();
			return movie;
	}

	public List<Movie> findAll() {
		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("FROM Movie");
		
		@SuppressWarnings("unchecked")
		List<Movie> movieList=query.getResultList();
		
		if(movieList.isEmpty()) {
			System.out.println("No movies in the database.");
			return null;
		}
		else {
			return movieList;
		}
	}

	public Movie find(Integer movieId) {
		
		EntityManager em = entityFactory.createEntityManager();
		Movie movie=em.find(Movie.class, movieId);
		
		if(movie == null) {
			System.out.println("Movie not found!!");
			return null;
		}
		else {
			return movie;
		}
	}

	public Movie remove(Integer movieId) {
		EntityManager em = entityFactory.createEntityManager();
		Movie movie=em.find(Movie.class, movieId);
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		flag=1;
		tran.commit();
		System.out.println("Movie has been removed");
		return movie;
		
	}

}
