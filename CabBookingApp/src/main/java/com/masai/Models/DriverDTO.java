package com.masai.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

	private String name;

	private Integer currentPosition_X;

	private Integer currentPosition_Y;

	private Cab cab;
}
