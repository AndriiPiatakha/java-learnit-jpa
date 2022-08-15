package com.itbulls.learnit.jpa.queries.jpql;

import java.util.Date;

import com.itbulls.learnit.jpa.queries.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class JpqlQueryDemo {
	
	
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
	    Query jpqlQuery = em.createQuery("SELECT u FROM user_queries_demo u WHERE u.id=:id");
	    jpqlQuery.setParameter("id", id);
	    return (User) jpqlQuery.getSingleResult();
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
