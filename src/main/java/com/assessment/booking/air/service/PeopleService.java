package com.assessment.booking.air.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.assessment.booking.air.model.People;
import com.assessment.booking.air.repository.PeopleRepository;

@Service
public class PeopleService {
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	public List<People> saveAllItems(List<People> allMembers) {
		return peopleRepository.saveAll(allMembers);
	}
	
	public People getPeopleById(Long id) {
		return peopleRepository.getById(id);
	}
	
	public List<People> getAllPeople(){
		return peopleRepository.findAll();
	}
	
	public List<People> getAllPeopleByPage(Integer pageNo, Integer pageSize,String sortBy){
		 Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		 
	        Page<People> pagedPeople = peopleRepository.findAll(paging);
	         
	        if(pagedPeople.hasContent()) {
	            return pagedPeople.getContent();
	        } else {
	            return new ArrayList<People>();
	        }
	}
	
	public List<People> getPeopleByName(String name){
		return peopleRepository.searchByName(name);
	}

}
