package com.cg.movies.ui;

import com.cg.movies.dto.*;

import com.cg.movies.service.*;
import java.math.BigInteger;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class MyApplication {

	public static void main(String[] args) throws Exception {

		TheatreService theatreService = new TheatreServiceImpl();
		AdminService adminService = new AdminServiceImpl();
		ShowService showService = new ShowServiceImpl();
		MovieService movieService = new MovieServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date todays_date = new Date();
//		System.out.println(sdf.format(todays_date));
		SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");

		Scanner scanner = new Scanner(System.in);
		int count = 6;
		while ((count--) > 0) {
			System.out.println("BELOW ARE THE PREFERRED ROLES");
			System.out.println("1. Admin");
			System.out.println("2. Registered User");
			System.out.println("3. Unregistered User");
			System.out.println("4. Exit");
			System.out.println("Enter the type of User you are: ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("1. Login");
				System.out.println("2. Quit");
				int adminChoice = scanner.nextInt();
				switch (adminChoice) {
				case 1:
					System.out.println("Enter the UserName");
					String userName = scanner.next();
					System.out.println("Enter the Password: ");
					String userPass = scanner.next();
					Admin validateAdminLogin = adminService.validateAdminLogin(userName, userPass);
					if (validateAdminLogin.equals(null)) {
						System.out.println("Not an Admin : Exited from the System");
						exit(1);
					} else {
						System.out.println("Logged In : " + validateAdminLogin.getAdminName());
						System.out.println("Enter Your choice: ");
						System.out.println("1. Add Theater");
						System.out.println("2. Add Movie To Theatre");
						System.out.println("3. Add Movie Show");
						System.out.println("4. Get Theatres");
						System.out.println("5. Get Movies");
						System.out.println("6. Quit");
						System.out.println("Enter Function Number you want to perform: ");
						int input = scanner.nextInt();
						switch (input) {
						case 1:

							System.out.println("Enter the Theatre Details as asked: ");
							scanner.nextLine();
							System.out.println("Enter the Theatre name");
							String theatreName = scanner.nextLine();
							System.out.println("Enter the Theatre City");
							String theatreCity = scanner.nextLine();
							System.out.println("Enter the City Pincode");
							Integer city_pincode;
							try {
								city_pincode = scanner.nextInt();

							} catch (Exception e) {
								throw new Exception("Please enter valid pincode.");
							}
							scanner.nextLine();
							Theatre theatre = new Theatre();
							System.out.println("");
							try {
								theatre.setCityName(theatreCity);
								theatre.setCityPincode(city_pincode);
								theatre.setTheatreName(theatreName);
								theatreService.save(theatre);
								System.out.println("Theatre Added");
							} catch (Exception exception) {
								System.out.println(exception.getMessage());
							}
							break;
						case 2:
							List<Theatre> theatreList = theatreService.findAll();
							for (Theatre theatrein : theatreList) {

								System.out.println(theatrein.getTheatreId() + " " + theatrein.getTheatreName());
							}
							Theatre theatreObj = new Theatre();
							System.out.println("In how many theatres you want to add movie?");
							int num = scanner.nextInt();
							List<Theatre> showcasedTheatres = new ArrayList<Theatre>();
							System.out.println("Enter the Theatre Id's: ");
							for (int i = 0; i < num; i++) {

								theatreObj.setTheatreId(scanner.nextInt());
								showcasedTheatres.add(theatreObj);

							}
							System.out.println("Enter the Movie Details as asked: ");
							scanner.nextLine();
							System.out.println("Enter the movie name");
							String name = scanner.nextLine();
							System.out.println("Enter the genre name");
							String genre = scanner.nextLine();
							System.out.println("Enter the director name");
							String director = scanner.nextLine();
							System.out.println("Enter the movie length ");
							Integer movieLength = scanner.nextInt();
							scanner.nextLine();
							System.out.println("Enter the movie release date"); // today or next
							Date release_date = sdf.parse(scanner.nextLine());
							if (release_date.before(todays_date)) {
								System.out.println("Not a valid Release date");
								exit(1);
							} else {
								System.out.println("Enter the movie language");
								String language = scanner.nextLine();
								Movie movie = new Movie();
								movie.setDirector(director);
								movie.setLanguage(language);
								movie.setMovieName(name);
								movie.setMovieReleaseDate(release_date);
								movie.setMovieLength(movieLength);
								movie.setGenre(genre);
								movie.setTheatre(showcasedTheatres);
								movie.setFlag(0);
								System.out.println("");
								try {
									movieService.save(movie);
									System.out.println("Movie Added");
								} catch (Exception exception) {
									System.out.println(exception.getMessage());
								}
							}

							break;
						case 3:
							Show show = new Show();
							Movie movie = new Movie();
							Theatre show_theatre = new Theatre();
							List<Movie> movies = customerService.getMovies();
							for (Movie moviein : movies) {

								System.out.println(moviein.getMovieId() + " " + moviein.getMovieName());
							}
							System.out.println("Enter the Movie Id to add Show : ");
							Integer movieId = scanner.nextInt();
							movie.setMovieId(movieId);
							System.out.println("Theatres with this movie: ");
							List<String> theatresList = customerService.getTheatreByMovieId(movieId);
							if (theatresList != null) {
								theatresList.forEach(theater -> {
									System.out.println(theater);
								});
								System.out.println("Enter the theatre Id: ");
								Integer theatreSelected = scanner.nextInt();
								show_theatre.setTheatreId(theatreSelected);
								scanner.nextLine();
								System.out.println("Enter Date :");
								Date show_date = sdf.parse(scanner.nextLine());
								if (show_date.before(todays_date)) {
									System.out.println("Enter correct date for show to be successfully added");
									exit(1);
								} else {
									System.out.println("Enter the show timings");
									Date show_timings = sdf1.parse(scanner.nextLine());
//									LocalDateTime lt = LocalDateTime.parse(show_timings);
									System.out.println("Enter number of booked seats");
									Integer booked_seats = scanner.nextInt();
									System.out.println("Enter number of available seats");
									Integer available_seats = scanner.nextInt();
									show.setAvailableSeats(available_seats);
									show.setBookedSeats(booked_seats);
									show.setShow_date(show_date);
									show.setShow_timings(show_timings);
									show.setTheatre(show_theatre);
									show.setMovie(movie);
									try {
										showService.save(show);
										System.out.println("Show Added");
									} catch (Exception exception) {
										System.out.println(exception.getMessage());
									}
								}
							} else {
								System.out.println("No Theatres with ths movie");
							}

							break;
						case 4:
							System.out.println("******************LIST OF THEATRES****************");
							List<Theatre> theatersList = theatreService.findAll();
							for (Theatre theatreLoop : theatersList) {
								System.out.println("##" + theatreLoop.getTheatreName());
							}
							System.out.println("****************************************");
							break;
						case 5:
							System.out.println("********************LIST OF MOVIES********************");
							List<Movie> moviesList = movieService.findAll();
							for (Movie movieloop : moviesList) {
								System.out.println("##" + movieloop.getMovieName());
							}
							System.out.println("**************************************************");
							break;
						case 6:
							exit(1);
							break;
						}
					}
					break;
				case 2:
					exit(1);
				}

				break;
			case 2:
				System.out.println("1. Login");
				System.out.println("2. View Movies");
				System.out.println("3. Quit");
				System.out.println("Enter Your choice: ");
				int userChoice = scanner.nextInt();
				switch (userChoice) {

				case 1:
					System.out.println("Enter the UserName");
					String userName = scanner.next();
					System.out.println("Enter the Password: ");
					String userPass = scanner.next();
					Boolean validate_customer = customerService.validateCustomer(userName, userPass);
					if (validate_customer == true) {
						System.out.println("You've been successfully logged in");
						System.out.println("1. Book Tickets");
						System.out.println("2. View Bookings");
						System.out.println("3. Cancel Bookings");
						System.out.println("Enter your choice");
						int userFunction = scanner.nextInt();
						switch (userFunction) {

						case 1:
							Booking booking = new Booking();
							System.out.println("Movies: ");
							List<Movie> movieList = customerService.getMovies();
							for (Movie movie : movieList) {
								System.out.println("" + movie.getMovieId() + " : " + movie.getMovieName());
							}
							System.out.println("Enter the Movie Id you want to book show for");
							Integer movieId = scanner.nextInt();
							System.out.println("Theatres with this movie: ");
							List<String> theatresList = customerService.getTheatreByMovieId(movieId);
							if (theatresList != null) {
								theatresList.forEach(theatre -> {
									System.out.println(theatre);
								});
							}
							System.out.println("Enter the theatre Id: ");
							Integer theatreSelected = scanner.nextInt();
							List<String> showsList = customerService.getShows(movieId, theatreSelected);
							if (showsList != null) {
								showsList.forEach(show -> {
									System.out.println(show);
								});
							}
							System.out.println("Enter the showId : ");
							Integer showSelected = scanner.nextInt();
							Show show = new Show();
							Customer customer = new Customer();
							show.setShowId(showSelected);
							System.out.println("Enter the seats you want");
							Integer seatsBooked = scanner.nextInt();
							System.out.println("Total Cost would be " + seatsBooked * 200 + " Rs.");
							Integer total_cost = seatsBooked * 200;
							String payment = "Done";
							booking.setPayment(payment);
							booking.setTotalCost(total_cost);
							booking.setSeatsBooked(seatsBooked);
							booking.setShow(show);
							booking.setFlag(0);
							BigInteger userId = customerService.getUserId(userName);
							customer.setCustomerId(userId);
							booking.setCustomer(customer);
							Boolean bookingStatus = customerService.addBooking(booking);
							if (bookingStatus == false) {
								System.out.println("Booking could not be completed");
							} else
								System.out.println("Booking successfully done: ");
							BigInteger bookingId = customerService.getBookingId(userId);
							System.out.println("Booking Id : " + bookingId);
							break;
						case 2:
							BigInteger userID = customerService.getUserId(userName);
							List<String> bookingsDone = customerService.viewBookings(userID);
							if (bookingsDone != null) {
								System.out.println(" " + bookingsDone);
							}

							break;
						case 3:
							BigInteger user_id = customerService.getUserId(userName);
							List<String> bookingList = customerService.viewBookings(user_id);
							if (bookingList != null) {
								System.out.println(" " + bookingList);
							}
							System.out.println("Enter the booking id to cancel");
							BigInteger booking_id = scanner.nextBigInteger();
							Boolean cancelBooking = customerService.cancelBooking(booking_id);
							if (cancelBooking == true) {
								System.out.println("Booking has been cancelled");
							}
							break;

						}
					} else {
						System.out.println("Entered Username and password combination does not exist");
						System.out.println("Please register or check the credentials");
						exit(1);
					}
					break;
				case 2:
					System.out.println("Movies: ");
					List<Movie> movieList = customerService.getMovies();
					for (Movie movie : movieList) {
						System.out.println("" + movie.getMovieId() + " : " + movie.getMovieName());
					}
					System.out.println("Enter the Movie Id you want to book show for");
					Integer movieId = scanner.nextInt();
					System.out.println("Theatres with this movie: ");
					List<String> theatresList = customerService.getTheatreByMovieId(movieId);
					if (theatresList != null) {
						theatresList.forEach(theatre -> {
							System.out.println(theatre);
						});
					}
					System.out.println("Enter the theatre Id: ");
					Integer theatreSelected = scanner.nextInt();
					List<String> showsList = customerService.getShows(movieId, theatreSelected);
					if (showsList != null) {
						showsList.forEach(show -> {
							System.out.println(show);
						});
					}
					break;
				case 3:
					exit(1);
					break;

				}
				break;

			case 3:
				System.out.println("1. Register");
				System.out.println("2. View Movies Shows");
				System.out.println("3. Exit");
				int viewerChoice = scanner.nextInt();
				switch (viewerChoice) {

				case 1:
					Customer customer = new Customer();
					System.out.println("Enter Username");
					String customerName = scanner.next();
					System.out.println("Enter the Password");
					String customerPass = scanner.next();
					System.out.println("Confirm Password");
					String confirmPass = scanner.next();
					if (customerPass.equals(confirmPass)) {
						System.out.println("Password Matched");
						System.out.println("Enter your Phone number");
						String contactNumber = scanner.next();
						customer.setCustomerName(customerName);
						customer.setCustomerPassword(customerPass);
						customer.setContactNumber(contactNumber);
						try {
							System.out.println(customerService.addCustomer(customer));
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("Password doesn't Match");
						exit(1);
					}

					break;
				case 2:
					System.out.println("Movies: ");
					List<Movie> movieList = customerService.getMovies();
					for (Movie movie : movieList) {
						System.out.println("" + movie.getMovieId() + " : " + movie.getMovieName());
					}
					System.out.println("Enter the Movie Id you want to book show for");
					Integer movieId = scanner.nextInt();
					System.out.println("Theatres with this movie: ");
					List<String> theatresList = customerService.getTheatreByMovieId(movieId);
					if (theatresList != null) {
						theatresList.forEach(theatre -> {
							System.out.println(theatre);
						});
					}
					System.out.println("Enter the theatre Id: ");
					Integer theatreSelected = scanner.nextInt();
					List<String> showsList = customerService.getShows(movieId, theatreSelected);
					if (showsList != null) {
						showsList.forEach(show -> {
							System.out.println(show);
						});
					}
					break;
				case 3:
					exit(1);
				}
				break;
			case 4:
				exit(1);
			}

		}

	}

	private static void exit(int i) {
		// TODO Auto-generated method stub

	}

}
