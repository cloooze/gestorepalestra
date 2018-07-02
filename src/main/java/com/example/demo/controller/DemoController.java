package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.WebConfig;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@SessionAttributes("allUsers")
public class DemoController extends AbstractController {
	
	private static final String TAB_NEW = "new";
	private static final String TAB_RIC = "ric";
	private static final String TAB_MOD = "mod";
	
	@Autowired
	UserService userService;
	
	@Autowired
	WebConfig webconfig;
	
	MessageSource messageSource;
	
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
		
		modelAndView.addObject("showTab", TAB_NEW);
		modelAndView.setViewName("gestione_utenti");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getDettaglioUtente", method = RequestMethod.GET)
	public ModelAndView getDettagliUtente(@RequestParam("codiceFiscale") String codiceFiscale) {
		ModelAndView modelAndView = new ModelAndView();
		
		Optional<User> res = userService.findByCodiceFiscale(codiceFiscale);
		
		modelAndView.addObject("dettaglioUtente", res.get());
		modelAndView.addObject("showTab", TAB_MOD);
		modelAndView.setViewName("gestione_utenti");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateUtente", method = RequestMethod.POST)
	public ModelAndView updateUtente(@ModelAttribute("dettaglioUtente") User user, SessionStatus sessionStatus) {
		ModelAndView modelAndView = new ModelAndView();
		
		userService.save(user);
		
		modelAndView.addObject("successMessage", "Modifiche salvate correttamente.");
		modelAndView.addObject("dettaglioUtente", new User());
		sessionStatus.setComplete();
		
		modelAndView.addObject("showTab", TAB_MOD);
		modelAndView.setViewName("gestione_utenti");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveUtente", method = RequestMethod.POST)
	public String saveUtente(Model model, @ModelAttribute("utente") @Valid User user, BindingResult bindingResult, SessionStatus sessionStatus) {
		
		if (!bindingResult.hasErrors()) {
			if (userService.findByCodiceFiscale(user.getCodiceFiscale()).isPresent()) {
				model.addAttribute("errorMessage", String.format("Utente già presente."));
			} else {
				User res = userService.save(user);
				sessionStatus.setComplete();
				model.addAttribute("utente", new User());
				model.addAttribute("successMessage", String.format("Utente %s %s correttamente creato.", res.getNome(), res.getCognome()));
			}
		} else {
			model.addAttribute("errorMessage", "Ci sono errori nel form, ricontrollare.");
//			model.addAttribute("errorMessage", messageSource.getMessage("user.save.error", null, null));
		}
		
		model.addAttribute("showTab", TAB_NEW);
		return "gestione_utenti";
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam(name = "codiceFiscale", required = true) String codiceFiscale, SessionStatus sessionStatus) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (!StringUtils.isEmpty(codiceFiscale)) {
			userService.deleteUserByCodiceFiscale(codiceFiscale);
			sessionStatus.setComplete();
		}
		
		modelAndView.addObject("showTab", TAB_MOD);
		modelAndView.addObject("successMessage", "Utente eliminato con successo.");
		modelAndView.setViewName("gestione_utenti");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/getFirstAvailableIdTessera", method = RequestMethod.GET)
	@ResponseBody
	public Long getFirstAvailableIdTessera() {
		return userService.getFirstAvailableIdTessera();
	}
	
	@RequestMapping(value = "/aggiornaListaUtenti", method = RequestMethod.GET)
	public ModelAndView aggiornaListaUtenti() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("showTab", TAB_RIC);
		modelAndView.addObject("successMessage", "Aggiornamento completato.");
		modelAndView.setViewName("gestione_utenti");
		
		return modelAndView;
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
		return userService.findAll();
	}
	
}
