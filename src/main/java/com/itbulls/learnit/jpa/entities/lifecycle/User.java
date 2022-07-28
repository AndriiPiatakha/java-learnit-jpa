package com.itbulls.learnit.jpa.entities.lifecycle;


import java.util.Date;

import com.itbulls.learnit.jpa.entities.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity(name = "corporate_user")
@Table(name = "corporate_user")
@EntityListeners(LifecycleListener.class)
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

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
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.age = age;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birthDate=" + birthDate + ", age=" + age + ", gender=" + gender + "]";
	}
	
	// ============ Lifecycle events handling 
	

	@PrePersist
	public void handleBeforeUserIsCreated() {
		System.out.println("*** FROM USER ENTITY *** Before user creation. Event handling of user creation with ID: " + this.id);
	}
	    
	@PostPersist
	public void handleAfterUserIsCreated() {
		System.out.println("*** FROM USER ENTITY *** User is created. Event handling of user creation with ID: " + this.id);
	}
	    
	@PreRemove
	public void handleBeforeUserIsRemoved() {
		System.out.println("*** FROM USER ENTITY *** Before user removal. Event handling of user removal with ID: " + this.id);
	}
	    
	@PostRemove
	public void handleUserIsRemoved() {
		System.out.println("*** FROM USER ENTITY *** User is removed. Event handling of user removal with ID: " + this.id);
	}

	@PreUpdate
	public void handleBeforeUserIsUpdated() {
		System.out.println("*** FROM USER ENTITY *** Before user update. Event handling of user update with ID: " + this.id);
	}

	@PostUpdate
	public void handleUserIsLoaded() {
		System.out.println("*** FROM USER ENTITY *** User is updated. Event handling of user update with ID: " + this.id);
	}

	@PostLoad
	public void handleUserLoaded() {
		System.out.println("*** FROM USER ENTITY *** User is loaded. Event handling of user loading with ID: " + this.id);
	}
	
}
