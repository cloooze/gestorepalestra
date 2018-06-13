package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.User;

public interface UserService {
	
	public Optional<User> getUserByCodiceFiscale(String codiceFiscale);

}
