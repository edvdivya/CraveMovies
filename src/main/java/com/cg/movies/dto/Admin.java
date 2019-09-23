package com.cg.movies.dto;
import javax.persistence.*;
@Entity
@Table(name = "admin")
public class Admin {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer adminId;
	@Column(name = "username")
	private String adminName;
	@Column(name = "password")
	private String adminPassword;
	@Column(name = "phone_number")
	private String contactNumber;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer adminId, String adminName, String adminPassword, String contactNumber) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.contactNumber = contactNumber;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", contactNumber=" + contactNumber + "]";
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
