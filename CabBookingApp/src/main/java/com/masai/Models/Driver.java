package com.masai.Models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	
	@NotNull
	private String name;

	private Integer[] currentPosition = new Integer[2];

	@Embedded
	private Cab cab;

}
