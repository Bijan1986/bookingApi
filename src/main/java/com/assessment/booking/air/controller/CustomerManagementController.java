package com.assessment.booking.air.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assessment.booking.air.model.People;
import com.assessment.booking.air.service.PeopleService;

@Controller
@RequestMapping("/")
public class CustomerManagementController {

	@Autowired
	private PeopleService peopleService;

	@GetMapping("/")
	public String showForm(Model model) {
		model.addAttribute("client", new People());
		return findPaginatedPeople(1, "fullName", "asc", model);
	}

	@GetMapping("/customerSearch")
	public String showNewEmployeeForm(@ModelAttribute("client") People client, RedirectAttributes redirectAttributes,
			Model model) {
		Optional<List<People>> clients = peopleService.getPeopleByName(client.getFullName());
		if (clients.isPresent()) {
			model.addAttribute("clients", clients.get());
			return "clientSearch";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/page/{pageNo}")
	public String findPaginatedPeople(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 20;

		Page<People> page = peopleService.findPaginatedPeople(pageNo, pageSize, sortField, sortDir);
		List<People> listEmployees = page.getContent();
		if(model.getAttribute("client") == null) {
			model.addAttribute("client", new People());
		}
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
