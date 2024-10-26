package com.InsuranceManagement.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "insurances")
@Inheritance(strategy = InheritanceType.JOINED)
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   public UUID id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    public User user;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
