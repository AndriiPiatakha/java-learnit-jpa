package com.itbulls.learnit.jpa.queries.criteriaapi;

import java.util.Date;
import java.util.List;

import com.itbulls.learnit.jpa.queries.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CriteriaApiDemo {
	
	
	public static void main(String[] args) throws InterruptedException {
		try (var emf = Persistence
				.createEntityManagerFactory("persistence-unit");
				var em = emf.createEntityManager()) {
			createUserInDb(emf);
			
			em.getTransaction().begin();
			
			List<User> allUsers = getAllUsers(em);
			System.out.println(allUsers);
			
			User user = getUserById(1, em);
			System.out.println(user);

			updateUserEmail(1, "new.email@email.com", em);
			
			deleteUserById(1, em);
			
			em.getTransaction().commit();
		}
	}
	
	public static List<User> getAllUsers(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		Query query = em.createQuery(criteriaQuery.select(userRoot));
		return query.getResultList();
	}
	
	public static User getUserById(Integer id, EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
	    Root<User> userRoot = criteriaQuery.from(User.class);
	    
	    // Just some other examples of Criteria API
	    /*
	    
	    1) Extract users with ID greater than 1
	    criteriaQuery.select(userRoot).where(criteriaBuilder.gt(userRoot.get("id"), 1));
	    
	    2) Extract users where First Name contains "o"
	    criteriaQuery.select(userRoot).where(criteriaBuilder.like(userRoot.get("first_name"), "%o%"));
	    
	    3) Extract users with ID between 1 and 5
	    criteriaQuery.select(userRoot).where(criteriaBuilder.between(userRoot.get("id"), 1, 5));
	    
	    4) Extract users with the first name in the specified range
	    criteriaQuery.select(userRoot).where(userRoot.get("first_name").in("John", "Michael", "Peter"));
	    
	    5) Chain expressions
	    Predicate[] predicates = new Predicate[2];
		predicates[0] = criteriaBuilder.isNull(userRoot.get("last_name"));
		predicates[1] = criteriaBuilder.like(userRoot.get("first_name"), "J%");
		criteriaQuery.select(userRoot).where(predicates);
		
		6) Sorting example
		criteriaQuery.orderBy(
			  criteriaBuilder.asc(userRoot.get("first_name")), 
			  criteriaBuilder.desc(userRoot.get("last_name")));
	    
	    */
	    return em.createQuery(criteriaQuery.select(userRoot)
	    					.where(criteriaBuilder.equal(userRoot.get("id"), id)))
	    			.getSingleResult();
		
	}
	
	public static void updateUserEmail(Integer id, String newEmail, EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
		Root<User> root = criteriaUpdate.from(User.class);
		criteriaUpdate.set("email", newEmail);
		criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
		em.createQuery(criteriaUpdate).executeUpdate();
	}
	
	public static void deleteUserById(Integer id, EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
		Root<User> root = criteriaDelete.from(User.class);
		criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
		em.createQuery(criteriaDelete).executeUpdate();
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
