package com.itbulls.learnit.jpa.entities;

import java.util.Date;

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
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("Entity is persisted successfully");
	}

}
