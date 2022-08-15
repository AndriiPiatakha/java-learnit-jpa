package com.itbulls.learnit.jpa.queries.nativequeries;

import java.util.Date;

import com.itbulls.learnit.jpa.queries.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class NativeQueryDemo {
	
	
	public static void main(String[] args) throws InterruptedException {
		try (var emf = Persistence
				.createEntityManagerFactory("persistence-unit");
				var em = emf.createEntityManager()) {
			createUserInDb(emf);
			
			em.getTransaction().begin();
			User user = getUserById(1, em);
			
			System.out.println(user);
			em.getTransaction().commit();
		}
	}
	
	public static User getUserById(Integer id, EntityManager em) {
		Query nativeQuery
	      		= em.createNativeQuery("SELECT * FROM user_qd WHERE id=:id", User.class);
	    nativeQuery.setParameter("id", id);
	    return (User) nativeQuery.getSingleResult();
	}
	
	private static void createUserInDb(EntityManagerFactory emf) {
		try (var em =  emf.createEntityManager()) {
			em.getTransaction().begin();
			User user = new User("John", "Smith", "john.smith@email.com", new Date());
			em.persist(user);
			em.getTransaction().commit();
		}
		
	}
	

}
