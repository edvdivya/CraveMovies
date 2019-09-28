package com.cg.movies.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
	EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("Movies");

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("From Customer where customerName = :first or contactNumber = :second");
		query.setParameter("first", customer.getCustomerName());
		query.setParameter("second", customer.getContactNumber());
		List<Customer> customerList = query.getResultList();
		if (customerList.isEmpty()) {
			// save customer
			EntityTransaction tran = em.getTransaction();
			tran.begin();
			em.persist(customer);
			tran.commit();

			return customer;
		}
		System.out.println("Phone number or the username is already Registered");
		System.out.println("Couldn't Register");
		return null;
	}

	@Override
	public Boolean validateCustomer(String userName, String userPass) throws UserException {
		// TODO Auto-generated method stub
		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("From Customer where customerName = :first and customerPassword = :second");
		query.setParameter("first", userName);
		query.setParameter("second", userPass);
		List<Customer> customerList = query.getResultList();
		if (customerList.isEmpty()) {
			return false;
		}
		return true;

	}

	@Override
	public List<Movie> getMovies() {
		Date today = new Date();

		// TODO Auto-generated method stub
		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("From Movie where showStatus = :first");
		query.setParameter("first",0);
		List<Movie> movieList = query.getResultList();
		return movieList;

	}

	@Override
	public List<String> getTheatreByMovieId(Integer movieId) {
		// TODO Auto-generated method stub
		EntityManager em = entityFactory.createEntityManager();
		Movie movie = em.find(Movie.class, movieId);
		if (movie != null) {
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
	public List<String> getShows(Integer movieId, Integer theatreId) {
		// TODO Auto-generated method stub
		EntityManager em = entityFactory.createEntityManager();
		Theatre theatre = em.find(Theatre.class, theatreId);
		if (theatre != null) {
			List<Show> showsList = theatre.getShowsList();

			List<String> timings = new ArrayList<String>();
			showsList.forEach(show -> {
				if (show.getMovie().getMovieId() == movieId) {
					timings.add(show.getShowId() + " : " + sdf.format(show.getShow_date()) + " : "
							+ sdf1.format(show.getShow_timings()) + " seats available : " + show.getAvailableSeats());
				}

			});
			return timings;
		}

		return null;
	}

	@Override
	public BigInteger getUserId(String userName) {

		EntityManager em = entityFactory.createEntityManager();
		Query query = em.createQuery("From Customer where customerName = :first");
		query.setParameter("first", userName);
		List<Customer> customer = query.getResultList();
		return customer.get(0).getCustomerId();

	}

	@Override
	@Transactional
	public Boolean addBooking(Booking booking) throws Exception {
		EntityManager em = entityFactory.createEntityManager();
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		em.persist(booking);
		tran.commit();
		return true;
	}

	@Override
	public List<String> viewBookings(BigInteger userID) {
		EntityManager em = entityFactory.createEntityManager();
		Customer customer = em.find(Customer.class, userID);
		if (customer != null) {
			List<Booking> bookingsList = customer.getBookings();
			List<String> bookingIds = new ArrayList<String>();
			bookingsList.forEach(booking -> {
				bookingIds.add(booking.getBookingId() + " : "
						+ sdf1.format(booking.getShow().getShow_timings()) + " : "
								+ booking.getShow().getTheatre().getTheatreName() + " : "
								+ booking.getShow().getMovie().getMovieName());
			});
			return bookingIds;
		}

		return null;

	}

	@Override
	@Transactional
	public Boolean cancelBooking(BigInteger bookingid) {

		EntityManager em = entityFactory.createEntityManager();

		Booking booking = em.find(Booking.class, bookingid);
		em.getTransaction().begin();
		booking.setFlag(1);
		em.getTransaction().commit();

		return true;
	}

	@Override
	public BigInteger getBookingId(BigInteger userId) {
		EntityManager em = entityFactory.createEntityManager();
		Customer customer = em.find(Customer.class, userId);
		if (customer != null) {
			List<Booking> bookingsList = customer.getBookings();
			List<String> bookingIds = new ArrayList<String>();
			bookingsList.forEach(booking -> {
				bookingIds.add(booking.getBookingId() + " " + booking.getShow());
			});
			return bookingsList.get(bookingsList.size() - 1).getBookingId();
		}
		return null;
	}

	@Override
	public Date getReleaseDate(Integer movieID) {
		// TODO Auto-generated method stub
		EntityManager em = entityFactory.createEntityManager();
		Movie movie = em.find(Movie.class, movieID);
		if (movie == null) {
			System.out.println("Movie not found!!");
			return null;
		} else {
			return movie.getMovieReleaseDate();
		}
	}

	@Override
	public Integer getAvailableSeats(Integer showSelected) {
		// TODO Auto-generated method stub
		EntityManager em = entityFactory.createEntityManager();
		Show show = em.find(Show.class, showSelected);
		if (show == null) {
			System.out.println("Show not found!!");
			return null;
		} else {
			return show.getAvailableSeats();
		}

	}

	@Override
	@Transactional
	public Boolean updateSeats(Integer showSelected, Integer availableSeats, Integer bookedSeats) {
		// TODO Auto-generated method stub

		EntityManager em = entityFactory.createEntityManager();
		Show show = em.find(Show.class, showSelected);
		em.getTransaction().begin();
		show.setAvailableSeats(availableSeats - bookedSeats);
//		  show.setBookedSeats(bookedSeats);
		em.getTransaction().commit();

		return true;
	}

}
