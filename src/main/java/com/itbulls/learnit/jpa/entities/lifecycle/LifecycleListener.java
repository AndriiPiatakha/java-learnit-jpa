package com.itbulls.learnit.jpa.entities.lifecycle;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class LifecycleListener {
	@PrePersist
	public void handleBeforeUserIsCreated(User user) {
		System.out.println("*** FROM LifecycleListener *** Before user creation. Event handling of user creation with ID: " + user.getId());
	}
	    
	@PostPersist
	public void handleAfterUserIsCreated(User user) {
		System.out.println("*** FROM LifecycleListener *** User is created. Event handling of user creation with ID: " + user.getId());
	}
	    
	@PreRemove
	public void handleBeforeUserIsRemoved(User user) {
		System.out.println("*** FROM LifecycleListener *** Before user removal. Event handling of user removal with ID: " + user.getId());
	}
	    
	@PostRemove
	public void handleUserIsRemoved(User user) {
		System.out.println("*** FROM LifecycleListener *** User is removed. Event handling of user removal with ID: " + user.getId());
	}

	@PreUpdate
	public void handleBeforeUserIsUpdated(User user) {
		System.out.println("*** FROM LifecycleListener *** Before user update. Event handling of user update with ID: " + user.getId());
	}

	@PostUpdate
	public void handleUserIsLoaded(User user) {
		System.out.println("*** FROM LifecycleListener *** User is updated. Event handling of user update with ID: " + user.getId());
	}
	
	@PostLoad
	public void handleUserLoaded(User user) {
		System.out.println("*** FROM LifecycleListener *** User is loaded. Event handling of user loading with ID: " + user.getId());
	}
	
}
