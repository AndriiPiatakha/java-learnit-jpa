package com.itbulls.learnit.jpa.entities.mapping.manytomany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Persistence;

public class Demo {
	
	public static void main(String[] args) {
		try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
				var em = emf.createEntityManager()) {
			
			em.getTransaction().begin();
			
			Author author1 = new Author();
			author1.setName("Stephen King");
			
			Author author2 = new Author();
			author2.setName("John Doe");
			
			Book book1 = new Book();
			book1.setAuthors(new HashSet<>(Arrays.asList(author1, author2)));
			book1.setName("It");
			
			Book book2 = new Book();
			book2.setAuthors(new HashSet<>(Arrays.asList(author1, author2)));
			book2.setName("The Shining");
			
			Book book3 = new Book();
			book3.setAuthors(new HashSet<>(Arrays.asList(author1)));
			book3.setName("Doctor Sleep");
			
			author1.setBooks(new HashSet<Book>(Arrays.asList(book1, book2, book3)));
			author2.setBooks(new HashSet<Book>(Arrays.asList(book1, book2)));
			
			em.persist(author1);
			em.persist(author2);
			
			em.getTransaction().commit();
			
		}
	}

}
