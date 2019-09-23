package com.cg.movies.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.movies.dto.Booking;
import com.cg.movies.dto.Customer;
import com.cg.movies.dto.Movie;
import com.cg.movies.dto.Show;
import com.cg.movies.dto.Theatre;
import com.cg.movies.exception.UserException;

public interface CustomerDao {

	public Customer addCustomer(Customer customer);
	public Boolean validateCustomer(String userName, String userPass) throws UserException;
	public List<Movie> getMovies();
	public List<String> getTheatreByMovieId(Integer movieId);
	public List<String> getShows(Integer theatreSelected);
	public BigInteger getUserId(String userName);
	public Boolean addBooking(Booking booking);
	public List<String> viewBookings(BigInteger userID);
	public Boolean cancelBooking(BigInteger bookingid);
	public BigInteger getBookingId(BigInteger userId);
}
