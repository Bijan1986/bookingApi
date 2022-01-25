package com.assessment.booking.air.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Bijan Nayak 
 * People entity class to store the 
 * details of full name and
 * their images
 *
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "PEOPLE")
@Table(name = "PEOPLE")
public class People {

	@Id
	@Column(name = "PEOPLE_ID")
	private Long id;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "PEOPLE_IMAGE")
	private String image;

}
