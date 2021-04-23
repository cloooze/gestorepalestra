package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.Richiamo;
import com.example.demo.dto.SoggettoDto;

@Controller
@RequestMapping("/soggetti")
public class SoggettiController extends AbstractController {
	
	@RequestMapping(value = "/inserimentoTestata", method = RequestMethod.GET)
	public ModelAndView get() {
		ModelAndView modelAndView = new ModelAndView();
		
		SoggettoDto soggetto = new SoggettoDto();
//		Richiamo richiamo = new Richiamo();
//		richiamo.setProtocollo(123);
//		soggetto.setRichiami(Arrays.asList(richiamo));
		
		modelAndView.addObject("soggetto", soggetto);
		modelAndView.setViewName("soggetti/inserimento_testata");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/salvaSoggetto", method = RequestMethod.POST)
	public String salvaSoggetto(Model model, @ModelAttribute("soggetto") @Valid SoggettoDto soggetto, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			model.addAttribute("successMessage", "Soggetto correttamente creato.");
			model.addAttribute("soggetto", new SoggettoDto());
		} else {
			model.addAttribute("errorMessage", "Ci sono errori nel form, ricontrollare.");
//			bindingResult.rejectValue("nome", "nome", "blabla"); Aggiunge manualmente un errore al bindingResult
		}
		
		return "soggetti/inserimento_testata";
	}
	
	@RequestMapping(value="/salvaSoggetto", params={"aggiungiRichiamo"}, method = RequestMethod.POST)
	public ModelAndView aggiungiRichiamo(final SoggettoDto soggetto) {
		ModelAndView modelAndView = new ModelAndView();
		
		soggetto.getRichiami().add(0, new Richiamo());
		modelAndView.addObject("soggetto", soggetto);
		
		modelAndView.setViewName("soggetti/inserimento_testata");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/salvaSoggetto", params={"rimuoviRichiamo"}, method = RequestMethod.POST)
	public ModelAndView rimuoviRichiamo(final SoggettoDto soggetto, HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView();
		
		final Integer richiamoId = Integer.valueOf(req.getParameter("rimuoviRichiamo"));
		soggetto.getRichiami().remove(richiamoId.intValue());
		
		modelAndView.addObject("soggetto", soggetto);
		
		modelAndView.setViewName("soggetti/inserimento_testata");
		
		return modelAndView;
	}

	
	@RequestMapping(value = "/gestione", method = RequestMethod.GET)
	public ModelAndView gestione() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("soggetto", new SoggettoDto());
		modelAndView.setViewName("soggetti/gestione");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/ricerca", method = RequestMethod.POST)
	public ModelAndView ricerca(@ModelAttribute("soggetto") @Valid SoggettoDto soggetto, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (!bindingResult.hasErrors()) {
			modelAndView.addObject("soggetto", soggetto);
			modelAndView.addObject("soggettiTrovati", getSoggettiTest());
		} else {
//			modelAndView.addObject("errorMessage", "Ci sono errori nel form, ricontrollare.");
		}
		
		modelAndView.setViewName("soggetti/gestione");
		
		return modelAndView;
	}
	
	public List<SoggettoDto> getSoggettiTest() {
		SoggettoDto s1 = new SoggettoDto();
		SoggettoDto s2 = new SoggettoDto();
		
		s1.setId(11);
		s2.setId(22);
		s1.setNome("Andrea");
		s2.setNome("Luca");
		
		s1.setGiornoNascita(8);
		s1.setMeseNascita(11);
		s1.setAnnoNascita(1982);
		
		s2.setGiornoNascita(1);
		s2.setMeseNascita(12);
		s2.setAnnoNascita(1980);
		
		return Arrays.asList(s1, s2);
	}
	
	
	
	
	
	
}
