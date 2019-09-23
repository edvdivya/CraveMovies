package com.cg.movies.dto;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.math.BigInteger;
import java.util.List;
@Entity
@Table(name = "customer")
public class Customer {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private BigInteger customerId;
	@Column(name="username")
	private String customerName;
	@Column(name="password")
	private String customerPassword;
	@Column(name="phone_number")
	private String contactNumber;
	@OneToMany(mappedBy = "customer")
	private List<Booking> bookings;
	
	
	public Customer() {
		
		// TODO Auto-generated constructor stub
	}
	public Customer(BigInteger customerId, String customerName, String customerPassword, String contactNumber,
			List<Booking> bookings) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.contactNumber = contactNumber;
		this.bookings = bookings;
	}
	public BigInteger getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigInteger customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", contactNumber=" + contactNumber + ", bookings=" + bookings + "]";
	}
	

}
