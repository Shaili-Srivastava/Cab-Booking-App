package com.masai.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String mobileNumber;

	private String firstName;

	private String lastName;

	private String password;

	private Integer currentPosition_X;

	private Integer currentPosition_Y;

}
