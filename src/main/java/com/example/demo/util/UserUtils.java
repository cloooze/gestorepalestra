package com.example.demo.util;

import com.example.demo.model.User;

public class UserUtils {
	
	public static User updateUser(User old, User newu) {
		old.setCitta(newu.getCitta());
		old.setCognome(newu.getCognome());
		old.setDataNascita(newu.getDataNascita());
		old.setEmail(newu.getEmail());
		old.setIdTessera(newu.getIdTessera());
		old.setIndirizzo(newu.getIndirizzo());
		old.setNome(newu.getNome());
		old.setNumeroTelefono(newu.getNumeroTelefono());
		
		return old;
	}

}
