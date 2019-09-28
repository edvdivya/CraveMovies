package com.cg.movies.dao;

import java.util.List;

import javax.persistence.*;

import com.cg.movies.dto.Theatre;

public class TheatreDaoImpl implements TheatreDao {

	public static int flag=0;
	EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("Movies");
	
	public Theatre save(Theatre theatre) {
		
		EntityManager em = entityFactory.createEntityManager();
		EntityTransaction tran=em.getTransaction();
			tran.begin();
			em.persist(theatre);
			tran.commit();
			return theatre;
		}
		

	public List<Theatre> findAll() {
		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("FROM Theatre");
		
		@SuppressWarnings("unchecked")
		List<Theatre> theatreList=query.getResultList();
		
		if(theatreList.isEmpty()) {
			System.out.println("No theatres in the database.");
			return null;
		}
		else {
			return theatreList;
		}
	}

	public Theatre find(Integer theatreId) {
		
		EntityManager em = entityFactory.createEntityManager();
		Theatre theatre=em.find(Theatre.class, theatreId);
		
		if(theatre == null) {
			System.out.println("Theatre not found!!");
			return null;
		}
		else {
			return theatre;
		}
	}

	public Theatre remove(Integer theatreId) {
		EntityManager em = entityFactory.createEntityManager();
		Theatre theatre=em.find(Theatre.class, theatreId);
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		flag=1;
		tran.commit();
		System.out.println("Theatre has been removed");
		return theatre;
		
	}
	
}