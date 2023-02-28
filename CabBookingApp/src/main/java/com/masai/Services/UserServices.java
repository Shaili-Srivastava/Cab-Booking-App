package com.masai.Services;

import java.util.List;

import com.masai.Exceptions.DriverException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.UserException;
import com.masai.Models.Driver;
import com.masai.Models.User;
import com.masai.Models.UserDTO;

public interface UserServices {

	public User registerUser(UserDTO userDTO) throws UserException;

	public List<Driver> findRide(String sessionKey) throws LoginException, DriverException;

	public String bookRide(String sessionKey, Integer driverId, Integer X, Integer Y)
			throws LoginException, DriverException;
}
