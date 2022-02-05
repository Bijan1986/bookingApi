package com.assessment.booking.air.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.assessment.booking.air.model.People;

public interface PeopleService {

	List<People> saveAllItems(List<People> allMembers);

	People getPeopleById(Long id);

	List<People> getAllPeople();

	List<People> getAllPeopleByPage(Integer pageNo, Integer pageSize, String sortBy);

	Optional<List<People>> getPeopleByName(String fullName);

	Page<People> findPaginatedPeople(int pageIndex, int pageSize, String sortingField, String sortDirection);

}
