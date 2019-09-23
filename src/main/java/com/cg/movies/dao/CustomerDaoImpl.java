package com.cg.movies.dao;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.cg.movies.dto.Booking;
import com.cg.movies.dto.Customer;
import com.cg.movies.dto.Movie;
import com.cg.movies.dto.Show;
import com.cg.movies.dto.Theatre;
import com.cg.movies.exception.UserException;

public class CustomerDaoImpl implements CustomerDao {

	EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("Movies");
	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		EntityManager em=entityFactory.createEntityManager();
		Query query = em.createQuery("From Customer where customerName = :first or contactNumber = :second");
		query.setParameter("first",customer.getCustomerName());
		query.setParameter("second",customer.getContactNumber());
		List<Customer> customerList=query.getResultList();
		if(customerList.isEmpty()) {
			//save customer
			EntityTransaction tran=em.getTransaction();
			tran.begin();
			em.persist(customer);
			tran.commit();
			System.out.println("You've been Succesfully Registered");
			return customer;
		}
		System.out.println("Phone number or the username is already Registered");
		System.out.println("Couldn't Register");
		return null;
	}

	@Override
	public Boolean validateCustomer(String userName, String userPass) throws UserException {
		// TODO Auto-generated method stub
		EntityManager em=entityFactory.createEntityManager();
		Query query = em.createQuery("From Customer where customerName = :first and customerPassword = :second");
		query.setParameter("first", userName);
		query.setParameter("second", userPass);
		List<Customer> customerList=query.getResultList();
		if(customerList.isEmpty()) {
			return false;
		}
		return true;			

	}

	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		EntityManager em=entityFactory.createEntityManager();
//		List<Employee> empList=new ArrayList<Employee>();
//		empList.add(em.find(Employee.class, 1001));
		Query query = em.createQuery("From Movie");
		List<Movie> movieList=query.getResultList();
		return movieList;
	
	}

	@Override
	public List<String> getTheatreByMovieId(Integer movieId) {
		// TODO Auto-generated method stub
		EntityManager em=entityFactory.createEntityManager();
			Movie movie = em.find(Movie.class, movieId);
			if(movie != null) {
				List<Theatre> theatresList = movie.getTheatre();
				List<String> nameIdList = new ArrayList<String>();
				theatresList.forEach(theatre -> {
					nameIdList.add(theatre.getTheatreId() + " " + theatre.getTheatreName());
				});
				return nameIdList;
			}
			
			return null;
	
	}


	@Override
	public List<String> getShows(Integer theatreSelected) {
		// TODO Auto-generated method stub
		EntityManager em=entityFactory.createEntityManager();
		Theatre theatre = em.find(Theatre.class, theatreSelected);
		if(theatre != null) {
			List<Show> showsList = theatre.getShowsList();
			List<String> timings = new ArrayList<String>();
			showsList.forEach(show -> {
				timings.add(show.getShowId()+" : "+show.getShow_timings()+" seats available : "+show.getAvailableSeats());
			});
			return timings;
		}

		return null;
	}

	@Override
	public BigInteger getUserId(String userName) {
		
		EntityManager em=entityFactory.createEntityManager();
		Query query = em.createQuery("From Customer where customerName = :first");
		query.setParameter("first", userName);
		List<Customer> customer=query.getResultList();
		return customer.get(0).getCustomerId();		

	}

	@Override
	@Transactional
	public Boolean addBooking(Booking booking) {
		EntityManager em=entityFactory.createEntityManager();
		EntityTransaction tran=em.getTransaction();
		tran.begin();
		
		em.persist(booking);
		tran.commit();
		return true;
	}

	@Override
	public List<String> viewBookings(BigInteger userID) {
		EntityManager em=entityFactory.createEntityManager();
		Customer customer = em.find(Customer.class, userID);
		if(customer != null) {
			List<Booking> bookingsList = customer.getBookings();
			List<String> bookingIds = new ArrayList<String>();
			bookingsList.forEach(booking -> {
				bookingIds.add(booking.getBookingId() + " " + booking.getShow().getShow_timings());
			});
			return bookingIds;
		}
		
		return null;
	
	}

	@Override
	@Transactional
	public Boolean cancelBooking(BigInteger bookingid) {
		
		EntityManager em=entityFactory.createEntityManager();
		
		  Booking booking = em.find(Booking.class,bookingid);
		  em.getTransaction().begin();
		  booking.setFlag(1);
		  em.getTransaction().commit();
		
		return true;
	}

	@Override
	public BigInteger getBookingId(BigInteger userId) {
		EntityManager em=entityFactory.createEntityManager();
		Customer customer = em.find(Customer.class, userId);
		if(customer != null) {
			List<Booking> bookingsList = customer.getBookings();
			List<String> bookingIds = new ArrayList<String>();
			bookingsList.forEach(booking -> {
				bookingIds.add(booking.getBookingId() + " " + booking.getShow());
			});
			return bookingsList.get(bookingsList.size()-1).getBookingId();
		}
		return null;
	}

}
