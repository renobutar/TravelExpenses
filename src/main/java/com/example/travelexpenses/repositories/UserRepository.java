package com.example.travelexpenses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelexpenses.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
