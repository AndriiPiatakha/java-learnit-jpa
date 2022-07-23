package com.itbulls.learnit.jpa.entities;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
//@Table(name = "corporate_user")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
//	@SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=50)
	
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_generator")
//	@TableGenerator(name="user_generator", table="id_generator", schema="onlineshop")
	private int id;
	
	// COMPOSITE PRIMARY KEY EXAMPLE
//	@EmbeddedId
//	private UserCompositePk userCompositePk;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true)
	private String email;
	
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
    private Date birthDate;
	
	@Transient
	private Integer age;
	
	@Enumerated(EnumType.STRING)
    private Gender gender;

	
	public User() {
	}
	
	public User(String firstName, String lastName, String email, 
			Date birthDate, Integer age, Gender gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.age = age;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
