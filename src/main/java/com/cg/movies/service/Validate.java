package com.cg.movies.service;
import com.cg.movies.dao.*;
import com.cg.movies.dto.Booking;
import com.cg.movies.dto.Customer;
import com.cg.movies.dto.Movie;
import com.cg.movies.dto.Show;
import com.cg.movies.dto.Theatre;
import com.cg.movies.exception.*;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Validate {
	
	    private static TheatreDao dao = new TheatreDaoImpl();

	    static boolean validate_theatre(Theatre theatre) throws UserException {
	        String str;
	        str=""+theatre.getCityPincode();
	        if(!str.matches("\\d+")){
	            throw new UserException("Exception Occured: City pincode contains only digits");
	        }
	        
	        if(!theatre.getCityName().matches("[a-zA-Z]+")){
	            throw new UserException("Exception Occured: City name can not have special charecters");
	        }
	        return true;
	    }
	    static boolean validate_movie(Movie movie) throws UserException {
	        String str;  
	        if(!movie.getDirector().matches("[a-zA-Z]") && !movie.getGenre().matches("[a-zA-Z]") && !movie.getLanguage().matches("[a-zA-Z]")){
	            throw new UserException("Exception Occured: Only Characters allowed in genre, director name and language");
	        }
	        str=""+movie.getMovieLength();
	        if(!str.matches("\\d+")){
	            throw new UserException("Exception Occured: Only digits allowed in length");
	        }
	        return true;
	    }
	    static boolean validate_Ids(Integer id) throws UserException {
	        String str=""+id;
	        if(!str.matches("\\d+")){
	            throw new UserException("city pincode contains only digits");
	        }
	        return true;
	    }
	    static boolean validate_name(String str) throws UserException {
	        if(!str.matches("[a-zA-Z]+")){
	            throw new UserException("name can not contain special charecters");
	        }
	        return true;
	    }
		public static boolean validate_show(Show show) {
			System.out.println("Show date");
			//validation
			return true;
		}
		public static boolean validate_customer(Customer customer) throws UserException {
			// TODO Auto-generated method stub
			if(customer.getCustomerPassword().length()<8) {
				throw new UserException("Password should be minimum 8 characters");
			}
			if(!customer.getContactNumber().matches("\\d+")) {
				throw new UserException("Contact number should be in digits");
			}
			if(!(customer.getContactNumber().length()==10)) {
				throw new UserException("Contact number should be 10 digits");
			}
			return true;
		}
		public static boolean validate_booking(Booking booking) {
			if(booking.getSeatsBooked()>3) {
				return false;
			}
			return true;
		}
		public void validateDate(LocalDate date) throws DateException {
			if(!date.isAfter(LocalDate.now())){
				throw new DateException("Date should be after today's date");
			}
		}
	}



