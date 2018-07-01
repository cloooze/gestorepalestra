package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.UserUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserByCodiceFiscale(String codiceFiscale) {
		return userRepository.findById(codiceFiscale);
	}
	
	@Override
	public User save(User user) {
		user.setCodiceFiscale(user.getCodiceFiscale().toUpperCase().trim());
		user.setNome(StringUtils.capitalize(user.getNome()).trim());
		user.setCognome(StringUtils.capitalize(user.getCognome()).trim());
		user.setEmail(user.getEmail().toLowerCase().trim());
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Long getFirstAvailableIdTessera() {
		Long res = userRepository.getFirstAvailableIdTessera();
		if (res == null)
			return new Long(1);
		return res + 1;
	}

	@Override
	public Optional<User> update(User user) {
		Optional<User> res = userRepository.findById(user.getCodiceFiscale());
		if (res.isPresent()) {
			UserUtils.updateUser(res.get(), user);
			return Optional.of(userRepository.save(res.get()));
		}
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<User> findByCodiceFiscale(String codiceFiscale) {
		return userRepository.findById(codiceFiscale);
	}

	@Override
	public void deleteUserByCodiceFiscale(String codiceFiscale) {
		userRepository.deleteById(codiceFiscale);
	}

}
