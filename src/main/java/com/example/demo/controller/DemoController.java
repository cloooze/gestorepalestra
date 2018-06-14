package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@SessionAttributes("allUsers")
public class DemoController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/home",  method = RequestMethod.GET)
	public ModelAndView get() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("codiceFiscale") String codiceFiscale) {
		Optional<User> user = userService.getUserByCodiceFiscale(codiceFiscale);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (user.isPresent()) {
			modelAndView.addObject("user", user.get());
		}
		
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/gestioneUtenti", method = RequestMethod.GET)
	public ModelAndView gestioneUtenti(@ModelAttribute("utente") User utente) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("showTab", "new");
		modelAndView.addObject("showNuovoUtenteTab", "yes");
		modelAndView.setViewName("gestione_utenti");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getDettaglioUtente", method = RequestMethod.GET)
	public ModelAndView getDettagliUtente(@RequestParam("codiceFiscale") String codiceFiscale) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("showTab", "mod");
		modelAndView.addObject("dettaglioUtente", getUserMock());
		modelAndView.setViewName("gestione_utenti");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateUtente", method = RequestMethod.POST)
	public ModelAndView getDettagliUtente(@ModelAttribute("dettaglioUtente") User user) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("showTab", "mod");
		modelAndView.addObject("successMessage", "Modifiche salvata correttamente.");
		modelAndView.setViewName("gestione_utenti");
		
		return modelAndView;
	}
	
	
	
//	@RequestMapping(value = "/getUserMock", method = RequestMethod.GET)
	public User getUserMock() {
		User user = new User();
		user.setCitta("Roma");
		user.setEmail("cloooze@gmail.com");
		user.setCodiceFiscale("BRGNDR82S08G274S");
		user.setCognome("Braghese");
		user.setNome("Andrea");
		user.setDataNascita(DateUtils.create("1982", "11", "08").getTime());
		user.setNumeroTelefono("3351051146");
		user.setIndirizzo("Vicolo del duomo 5");
		user.setIdIscrizione("1");
		
		return user;
	}
	
	@ModelAttribute("utente")
	public User getModelUtente() {
		return new User();
	}
	
	@ModelAttribute("dettaglioUtente")
	public User getModelDettaglioUtente() {
		return new User();
	}
	
	@ModelAttribute("allUsers")
	public List<User> getAllUsers() {
		List<User> res = new ArrayList<User>();
		for (int i = 0; i < 500; i++) {
			res.add(getUserMock());
		}
		return res;
	}
	
	
	
	

}
