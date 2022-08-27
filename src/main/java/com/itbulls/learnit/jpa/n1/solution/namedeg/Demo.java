package com.itbulls.learnit.jpa.n1.solution.namedeg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Demo {
	
	public static void main(String[] args) {
		try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
				var em1 = emf.createEntityManager();
				var em2 = emf.createEntityManager()) {
			
			createCategoriesWithProducts(em1);
			
			em2.getTransaction().begin();
			
			EntityGraph entityGraph = em2.getEntityGraph("category-products-graph");
			// In case we don't want to use annotations, we can create an entity graph programmatically like this.
//			EntityGraph<Category> entityGraph = em2.createEntityGraph(Category.class);
//			entityGraph.addAttributeNodes("products");
			
			TypedQuery<Category> jpaQuery = em2.createQuery("SELECT C FROM category_ng C WHERE C.isAvailable = :isAvailable", Category.class);
			
			// This request will not fetch products, and will send separate request to fetch products after category is loaded
			jpaQuery.setParameter("isAvailable", true);
			
			// This request will fetch products with the JOIN statement, taking into account they are specified in Graph
			jpaQuery.setHint("javax.persistence.fetchgraph", entityGraph);
			
			List<Category> categories = jpaQuery.getResultList();
			
			System.out.println("===== Category is loaded =====");
			System.out.println(categories);
			em2.getTransaction().commit();
		}
	}

	private static void createCategoriesWithProducts(EntityManager em) {
		em.getTransaction().begin();
		
		Category category1 = new Category();
		category1.setCategoryName("laptop");
		category1.setAvailable(true);
		category1.setProducts(Arrays.asList(new Product("Asus Zenbook", category1),
											new Product("HP Pavilion", category1)));
		
		Category category2 = new Category();
		category2.setCategoryName("TV");
		category2.setAvailable(true);
		category2.setProducts(Arrays.asList(new Product("Samsung SUPER TV", category2),
											new Product("LG Super TV", category2)));
		
		Category category3 = new Category();
		category3.setCategoryName("Fridge");
		category3.setAvailable(false);
		category3.setProducts(Arrays.asList(new Product("Samsung Fridge", category3),
											new Product("LG Fridge", category3)));
		
		em.persist(category1);
		em.persist(category2);
		em.persist(category3);
		
		em.getTransaction().commit();
	}

}
