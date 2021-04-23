package com.example.demo.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import waffle.servlet.WindowsPrincipal;
import waffle.windows.auth.WindowsAccount;

@RestController
@RequestMapping("/waffle")
public class WaffleTestController extends AbstractController {
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String get(HttpServletRequest req) {
		Principal pri = req.getUserPrincipal();
		return req.getRemoteUser();
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public String get(Authentication auth) {
		WindowsPrincipal userPrincipal = (WindowsPrincipal)auth.getPrincipal();
		

		for(Map.Entry<String, WindowsAccount> entry: userPrincipal.getGroups().entrySet()){
		    System.out.println(entry.getValue().getName()); //The is the group that the user memberOf
		}
		
		return "name:" + userPrincipal.getName() + " fqn: " + userPrincipal.getIdentity().getFqn();
	}
	
	
}
