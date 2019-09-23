package com.cg.movies.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.cg.movies.dto.Show;

public class ShowDaoImpl implements ShowDao {
	public static int flag = 0;
	EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("Movies");

	@Transactional
	public Show save(Show show) {

		EntityManager em = entityFactory.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		em.persist(show);
		tran.commit();
		System.out.println("Show has been added successfully");
		return show;

	}

	public List<Show> findAll() {
		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("FROM Show");

		@SuppressWarnings("unchecked")
		List<Show> showList = query.getResultList();

		if (showList.isEmpty()) {
			System.out.println("No Shows in the database.");
			return null;
		} else {
			return showList;
		}
	}

	public Show find(Integer showId) {

		EntityManager em = entityFactory.createEntityManager();
		Show show = em.find(Show.class, showId);

		if (show == null) {
			System.out.println("Show not found!!");
			return null;
		} else {
			return show;
		}
	}

	public Show remove(Integer showId) {
		EntityManager em = entityFactory.createEntityManager();
		Show show = em.find(Show.class, showId);
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		flag = 1;
		tran.commit();
		System.out.println("Show has been removed");
		return show;

	}

	@Override
	public Show addMovieToShow(Integer movieId) {
		// TODO Auto-generated method stub
		return null;
	}

}
