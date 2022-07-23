package com.itbulls.learnit.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserCompositePk implements Serializable {
    private String name;
    private long id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
