package com.itbulls.learnit.jpa.entities.mapping.onetoone;

import jakarta.persistence.Persistence;

public class Demo {
	
	public static void main(String[] args) {
		try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
				var em = emf.createEntityManager()) {
			
			em.getTransaction().begin();
			
			User u = new User();
			u.setEmail("user1@email.com");
			u.setName("John");
			u.setPassword("password");
			
			UserProfile up = new UserProfile();
			up.setAge(30);
			up.setFavoriteColor("green");
			up.setGender("male");
			up.setUser(u);
			
			u.setProfile(up);
			
			em.persist(u);
//			em.persist(up); // in case there is CascadeType.PERSIST in User Entity, we don't need to persist UserProfile manually.
			
			em.getTransaction().commit();
			
		}
	}

}
