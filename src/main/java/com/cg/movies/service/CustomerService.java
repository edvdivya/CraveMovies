package com.cg.movies.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.movies.dto.Booking;
import com.cg.movies.dto.Customer;
import com.cg.movies.dto.Movie;
import com.cg.movies.dto.Show;
import com.cg.movies.dto.Theatre;
import com.cg.movies.exception.UserException;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws UserException;
	public Boolean validateCustomer(String userName, String userPass) throws UserException;
	public List<Movie> getMovies();
	public List<String> getTheatreByMovieId(Integer movieId);
	public List<String> getShows(Integer movieId, Integer theatreSelected);
	public BigInteger getUserId(String userName);
	public Boolean addBooking(Booking booking);
	public List<String> viewBookings(BigInteger userID);
	public Boolean cancelBooking(BigInteger booking_id);
	public BigInteger getBookingId(BigInteger userId);

}
