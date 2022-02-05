package com.assessment.booking.air.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assessment.booking.air.model.People;
import com.assessment.booking.air.service.PeopleService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
public class BookAirRestController {

	@Autowired
	private PeopleService peopleService;

	@PostMapping("/upload")
	public List<People> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
		List<People> peopleList = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record> allRecords = parser.parseAllRecords(inputStream);
		int counter = 1;
		for (Record item : allRecords) {
			if (item != null) {
				People people = new People();
				people.setId(Long.valueOf(counter));
				people.setFullName(item.getString("name"));
				people.setImage(item.getString("url"));
				peopleList.add(people);
				counter++;
			}

		}

		return peopleService.saveAllItems(peopleList);
	}

	@GetMapping("/getByName/{fullName}")
	public List<People> getPeopleByFullName(@PathVariable("fullName") String fullName) {
		return peopleService.getPeopleByName(fullName).get();
	}

	@GetMapping("/getAllPeople")
	public List<People> getAllPeople() {
		return peopleService.getAllPeople();
	}

	@GetMapping("/getAllPeopleByPage")
	public ResponseEntity<List<People>> getPeopleByPage(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<People> list = peopleService.getAllPeopleByPage(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<People>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}
