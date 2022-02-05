package com.assessment.booking.air.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.booking.air.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

	@Query("select p from PEOPLE p where p.fullName like :name")
	Optional<List<People>> searchByName(@Param("name")String fullName);

}
