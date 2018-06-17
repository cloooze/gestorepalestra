package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT MAX(idTessera) FROM UTENTE")
	public Long getFirstAvailableIdTessera();

}
