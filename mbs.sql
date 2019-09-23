create database mbsdb;

use mbsdb;

CREATE TABLE admin(
user_id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(15) NOT NULL,
password VARCHAR(15) NOT NULL,
phone_number VARCHAR(12) NOT NULL,
delete_flag INT default 0,
Check (delete_flag IN(0,1))
);




CREATE TABLE theatre(
theatre_id INT PRIMARY KEY AUTO_INCREMENT,
theatre_name VARCHAR(20) NOT NULL,
theatre_city VARCHAR(20) NOT NULL,
city_pincode INT NOT NULL, 
delete_flag INT default 0,
Check (delete_flag IN(0,1))
);



CREATE TABLE movie(
movie_id INT PRIMARY KEY AUTO_INCREMENT,
movie_name VARCHAR(30) NOT NULL,
movie_genre VARCHAR(30) NOT NULL,
movie_director VARCHAR(30) NOT NULL,
movie_length INT NOT NULL,
movie_language VARCHAR(30) NOT NULL,
movie_release_date date NOT NULL,
theatre_id INT , FOREIGN KEY(theatre_id) references theatre(theatre_id),
delete_flag INT default 0,
Check (delete_flag IN(0,1)) 
);



CREATE TABLE movie_show(
show_id INT PRIMARY KEY AUTO_INCREMENT,
show_date date NOT NULL,
show_timings datetime NOT NULL,
booked_seats INT,
available_seats INT NOT NULL,
movie_id INT, FOREIGN KEY(movie_id) REFERENCES movie(movie_id),
theatre_id INT, FOREIGN KEY(theatre_id) REFERENCES theatre(theatre_id),
delete_flag INT default 0,
Check (delete_flag IN(0,1))
);



CREATE TABLE booking(
booking_id bigint PRIMARY KEY AUTO_INCREMENT,
show_id INT, FOREIGN KEY(show_id) REFERENCES movie_show(show_id),
user_id INT, FOREIGN KEY(user_id) REFERENCES customer(user_id),
seats_booked INT NOT NULL,
total_cost double,
payment VARCHAR(10),
CHECK(payment IN(Pending,Done,Failed))
);

customer_id INT, FOREIGN KEY(customer_id) REFERENCES customer(user_id),

CREATE TABLE customer(
user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(15) NOT NULL,
password VARCHAR(15) NOT NULL,
email_address VARCHAR(50),
phone_number VARCHAR(12) NOT NULL,
delete_flag INT default 0,
Check (delete_flag IN(0,1))
);






#saveBooking
INSERT INTO booking(user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost) VALUES(?,?,?,?,?,?);

#if required use this,after booking
UPDATE booking SET delete_flag=1 WHERE booking_id=?;

#removeBooking
#DELETE FROM booking WHERE booking_id=?;

#list all bookings
SELECT * FROM booking WHERE delete_flag=0 AND booking_id=?;

#list bookings by booking id
SELECT b.booking_id,b.date_of_journey,p.passenger_name,p.passenger_age,p.passenger_gender,b.mode_of_payment,b.total_cost,b.status FROM
booking b JOIN passenger p ON b.booking_id=p.booking_id WHERE b.booking_id=?;


#savePassenger
INSERT INTO passenger 
____________________
		String sql ="INSERT INTO booking(booking_id,user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost,status,delete_flag) VALUES(?,?,?,?,?,?,?,?,?)";		
	String sql ="DELETE FROM booking WHERE booking_id=?";
String sql ="SELECT * FROM booking";
		String sql= "SELECT * FROM booking WHERE booking_id=?";
		String sql="INSERT INTO passenger (passenger_id,booking_id,passenger_name,passenger_age,passenger_gender,delete_flag) VALUES(?,?,?,?,?,?) ";
		String sql="SELECT * FROM passenger";
		String sql="SELECT * FROM passenger WHERE passenger_name=?";

		String sql="INSERT INTO bus(bus_id,bus_name,bus_type,bus_class,no_of_seats,source,destination,cost,delete_flag) VALUES(?,?,?,?,?,?,?,?,?)";
		String sql="DELETE FROM bus WHERE bus_id=?";
		String sql="SELECT * FROM bus";
		String sql="SELECT * FROM bus WHERE bus_id=?";
		String sql="SELECT * FROM transaction WHERE (transaction.bus_id= bus.bus_id";
		String sql="SELECT * FROM transaction WHERE date=?";
