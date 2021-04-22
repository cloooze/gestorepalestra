package com.example.demo.controller;

import java.util.Arrays;

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
		
		SoggettoDto sogg = new SoggettoDto();
		Richiamo richiamo = new Richiamo();
		richiamo.setProtocollo(123);
		sogg.setRichiami(Arrays.asList(richiamo));
		
		modelAndView.addObject("soggetto", sogg);
		modelAndView.setViewName("soggetti/inserimento_testata");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/salvaSoggetto", method = RequestMethod.POST)
	public String salvaSoggetto(Model model, @ModelAttribute("soggetto") @Valid SoggettoDto soggetto, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("soggetti/inserimento_testata");
//		modelAndView.addObject("soggetto", soggetto);
		
		if (!bindingResult.hasErrors()) {
			model.addAttribute("successMessage", String.format("Soggetto correttamente creato."));
		} else {
			model.addAttribute("errorMessage", "Ci sono errori nel form, ricontrollare.");
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

	
	
	@RequestMapping(value = "/ricerca", method = RequestMethod.GET)
	public ModelAndView ricerca() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("soggetto", new SoggettoDto());
		modelAndView.addObject("risultatiRicerca", Boolean.FALSE);
		modelAndView.setViewName("soggetti/ricerca");
		
		return modelAndView;
	}
	
	
	
	
	
	
}
