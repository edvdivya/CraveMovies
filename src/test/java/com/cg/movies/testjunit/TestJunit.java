package com.cg.movies.testjunit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigInteger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.movies.dto.Admin;
import com.cg.movies.dto.Booking;
import com.cg.movies.dto.Customer;
import com.cg.movies.dto.Movie;
import com.cg.movies.dto.Show;
import com.cg.movies.dto.Theatre;
import com.cg.movies.service.AdminService;
import com.cg.movies.service.AdminServiceImpl;
import com.cg.movies.service.CustomerService;
import com.cg.movies.service.CustomerServiceImpl;
import com.cg.movies.service.MovieService;
import com.cg.movies.service.MovieServiceImpl;
import com.cg.movies.service.ShowService;
import com.cg.movies.service.ShowServiceImpl;
import com.cg.movies.service.TheatreService;
import com.cg.movies.service.TheatreServiceImpl;

public class TestJunit {

	static AdminService adminService;
	static TheatreService theatreService;
	static MovieService movieService;
	static ShowService 	showService;
	static CustomerService customerService;	
	
	
	Admin admin;
	Theatre theatre;
	Movie movie;
	Customer customer;
	Show show;
	
	
	@BeforeAll
	public static void beforeTest() {
		adminService=new AdminServiceImpl();
		theatreService=new TheatreServiceImpl();
		movieService=new MovieServiceImpl();
		customerService=new CustomerServiceImpl();
		showService=new ShowServiceImpl();
		
	}
	
	@AfterAll
	public static void afterTest() {
		adminService=null;
		theatreService=null;
		movieService=null;
		showService=null;
		customerService=null;
	}
	
	@BeforeEach
	public void beforeEachTest() {
		admin=new Admin();
		admin.setAdminId(1);
		admin.setAdminName("Umang");
		admin.setAdminPassword("12345678");
		admin.setContactNumber("9760123123");
		theatre=new Theatre();
		theatre.setCityName("Mumbai");
		theatre.setCityPincode(400059);
		theatre.setTheatreId(10);
		theatre.setTheatreName("ABC");
		theatre.setMovieList(null);
		theatre.setShowsList(null);
		movie = new Movie();
		movie.setMovieReleaseDate(null);
		movie.setDirector("Christopher Nolan");
		movie.setGenre("Sci-fic Fantasy");
		movie.setLanguage("English");
		movie.setMovieId(11);
		movie.setMovieLength(120);
		movie.setMovieName("Interstellar");
		movie.setShowsList(null);
		movie.setTheatre(null);
		customer=new Customer();
		customer.setBookings(null);
		customer.setContactNumber("1231231231");
		customer.setCustomerId(BigInteger.valueOf(99999));
		customer.setCustomerName("Umang");
		customer.setCustomerPassword("12312312");
		show = new Show();
		show.setAvailableSeats(100);
		show.setBlockedSeats(5);
		show.setBookings(null);
		show.setMovie(movie);
		show.setShow_date(null);
		show.setShow_timings(null);
		show.setShowId(1);
		show.setTheatre(theatre);
		
		
		
	}
	
	@Test
	public void testSaveTheatre() {
		assertEquals(theatre , theatreService.save(theatre));
	}
	
	@Test
	public void testfindTheatre() {
		assertEquals(theatre, theatreService.find(10));
	}

	@Test
	public void testAdminSave() {
		assertEquals(admin, adminService.save(admin));
	}
	
	@Test
	public void testAdminFind() {
		assertEquals(admin,adminService.find(1));
	}
	@Test
	public void testRemoveAdmin() {
		assertEquals(admin,adminService.remove(1));
	}
}