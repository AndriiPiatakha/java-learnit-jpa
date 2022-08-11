package com.itbulls.learnit.jpa.entities.mapping.onetomany;

import java.util.ArrayList;
import java.util.Arrays;

import jakarta.persistence.Persistence;

public class Demo {
	
	public static void main(String[] args) {
		try (var emf = Persistence.createEntityManagerFactory("persistence-unit");
				var em = emf.createEntityManager()) {
			
			em.getTransaction().begin();
			
			Author author = new Author();
			author.setName("Stephen King");
			
			Book book1 = new Book();
			book1.setAuthor(author);
			book1.setName("It");
			
			Book book2 = new Book();
			book2.setAuthor(author);
			book2.setName("The Shining");
			
			Book book3 = new Book();
			book3.setAuthor(author);
			book3.setName("Doctor Sleep");
			
			author.setBooks(new ArrayList<Book>(Arrays.asList(book1, book2, book3)));
			
			em.persist(author);
			
			em.getTransaction().commit();
		}
	}

}
