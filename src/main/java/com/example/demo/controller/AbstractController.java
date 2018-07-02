package com.example.demo.controller;

public class AbstractController {
	
	public <T> boolean checkNull(T object) {
		if (object == null) 
			return true;
		return false;
	}

}
