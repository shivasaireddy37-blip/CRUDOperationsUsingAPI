package com.example.demo.model; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Employee")

public class Employee {
	@jakarta.persistence.Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
	@Column
	 private String firstname;
	@Column
	 private String lastname;
	@Column(unique=true,nullable=false)
	@Email(message="invalid mail formate")
	@NotBlank(message="mail cannot be blank")
	 private String email;
	@Column
	 private String phone;
	@Column
	 private String address;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(Long id, String firstname, String lastname,
			String phone, String address, @Email(message = "invalid mail formate") @NotBlank(message = "mail cannot be blank") String email ) {
		super();
		this.firstname = firstname ;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	 
	
}
