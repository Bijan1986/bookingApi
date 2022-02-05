package com.assessment.booking.air.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.assessment.booking.air.model.People;
import com.assessment.booking.air.repository.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	@Override
	public List<People> saveAllItems(List<People> allMembers) {
		return peopleRepository.saveAll(allMembers);
	}

	@Override
	public People getPeopleById(Long id) {
		return peopleRepository.getById(id);
	}

	@Override
	public List<People> getAllPeople() {
		return peopleRepository.findAll();
	}

	@Override
	public List<People> getAllPeopleByPage(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<People> pagedPeople = peopleRepository.findAll(paging);

		if (pagedPeople.hasContent()) {
			return pagedPeople.getContent();
		} else {
			return new ArrayList<People>();
		}
	}

	@Override
	public Optional<List<People>> getPeopleByName(String fullName) {
		return peopleRepository.searchByName(fullName);
	}

	@Override
	public Page<People> findPaginatedPeople(int pageIndex, int pageSize, String sortingField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortingField).ascending()
				: Sort.by(sortingField).descending();

		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
		return this.peopleRepository.findAll(pageable);
	}


}
