package com.masai.Models;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cab {

	private Integer capacity;

	@NotNull
	private Integer vehicleNumber;

	private String cabType;
}
