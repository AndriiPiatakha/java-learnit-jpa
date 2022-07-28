package com.itbulls.learnit.jpa.entities.lifecycle;

import java.util.Date;

import com.itbulls.learnit.jpa.entities.Gender;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Demo {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory 
				= Persistence.createEntityManagerFactory("persistence-unit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		User user = new User("John", "Smith", "john.smith@email.com", 
				new Date(), 30, Gender.MALE);
		
		entityManager.persist(user);
		int userId = user.getId();
		
		System.out.println("User ID after persist() method: " + userId);
		
		User userFromDb = entityManager.find(User.class, userId);
		System.out.println("User extracted from DB: " + userFromDb);
		
		// Refresh the state of the instance from the database, 
		// overwriting changes made to the entity, if any. It also triggers PostLoad event. 
		entityManager.refresh(userFromDb);
		userFromDb.setEmail("j.smith@email.com");

//		entityManager.merge(userFromDb);
		
		User user2 = new User("Michael", "Johnson", "michael.johnson@email.com", 
				new Date(), 40, Gender.MALE);
		entityManager.persist(user2);
		
//		entityManager.detach(user2);
//		user2.setFirstName("Robert");
		// changes after detach() method invocation will not be propagated to the DB automatically
		
		entityManager.remove(user2);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("Entity is persisted successfully");
	}

}
