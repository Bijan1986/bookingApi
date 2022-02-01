package com.assessment.booking.air.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.booking.air.model.People;
import com.assessment.booking.air.service.PeopleService;

@Controller
@RequestMapping("/")
public class CustomerManagementController {

	@Autowired
	private PeopleService peopleService;

	@GetMapping("/")
	public String showForm(Model theModel) {
		theModel.addAttribute("client", new People());
		return "index";
	}

	@GetMapping("/customer")
	public String showCustomer(@Valid @ModelAttribute("client") People client, BindingResult result, Errors errors,
			RedirectAttributes redirectAttributes, Model model) {
		if (result.hasErrors()) {
			return "index";
		} else {
			Optional<People> peopleByFullNameSearchResult = peopleService.getPeopleByFullName(client.getFullName());
			if (peopleByFullNameSearchResult.isPresent()) {
				People selectedPeople = peopleByFullNameSearchResult.get();
				model.addAttribute("selectedClient", selectedPeople);

				model.addAttribute("fullName", selectedPeople.getFullName());
				model.addAttribute("imageUrl", selectedPeople.getImage());
			} else {
				errors.rejectValue("id", "client " + client.getFullName() + " is unavailable");
				redirectAttributes.addFlashAttribute("errorMessage",
						"unable to find client with full name :" + client.getFullName());
				return "redirect:/";
			}

		}

		return "customerDetails";
	}

}
