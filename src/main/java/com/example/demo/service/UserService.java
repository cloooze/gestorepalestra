package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.User;

public interface UserService {
	
	public Optional<User> getUserByCodiceFiscale(String codiceFiscale);
	
	public User save(User user);
	
	public Optional<User> findByCodiceFiscale(String codiceFiscale);
	
	public Optional<User> update(User user);
	
	public List<User> findAll();
	
	public Long getFirstAvailableIdTessera();

}
