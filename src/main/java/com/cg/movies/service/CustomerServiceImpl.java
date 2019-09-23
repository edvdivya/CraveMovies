package com.cg.movies.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.movies.dao.*;
import com.cg.movies.dto.Booking;
import com.cg.movies.dto.Customer;
import com.cg.movies.dto.Movie;
import com.cg.movies.dto.Show;
import com.cg.movies.dto.Theatre;
import com.cg.movies.exception.UserException;

public class CustomerServiceImpl implements CustomerService {
		
	CustomerDao dao=new CustomerDaoImpl();
	@Override
	public Customer addCustomer(Customer customer) throws UserException {
		// TODO Auto-generated method stub
		if(Validate.validate_customer(customer)) {
			return dao.addCustomer(customer);
		}
		return null;
	}

	@Override
	public Boolean validateCustomer(String userName, String userPass) throws UserException {
		// TODO Auto-generated method stub
		return dao.validateCustomer(userName,userPass);
	}

	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return dao.getMovies();
	}

	@Override
	public List<String> getTheatreByMovieId(Integer movieId) {
		// TODO Auto-generated method stub
		return dao.getTheatreByMovieId(movieId);
	}

	@Override
	public List<String> getShows(Integer movieId, Integer theatreSelected) {
		// TODO Auto-generated method stub
		return dao.getShows(theatreSelected);
	}

	@Override
	public BigInteger getUserId(String userName) {
		// TODO Auto-generated method stub
		return dao.getUserId(userName);
	}

	@Override
	public Boolean addBooking(Booking booking) {
		// TODO Auto-generated method stub
		if(Validate.validate_booking(booking)) {
			return dao.addBooking(booking);
		}
		return false;
	}

	@Override
	public List<String> viewBookings(BigInteger userID) {
		// TODO Auto-generated method stub
		return dao.viewBookings(userID);
	}

	@Override
	public Boolean cancelBooking(BigInteger bookingid) {
		// TODO Auto-generated method stub
		return dao.cancelBooking(bookingid);
	}

	@Override
	public BigInteger getBookingId(BigInteger userId) {
		// TODO Auto-generated method stub
		return dao.getBookingId(userId);
	}

}
