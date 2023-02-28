package com.masai.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.DriverException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.UserException;
import com.masai.Models.Driver;
import com.masai.Models.LoginDTO;
import com.masai.Models.User;
import com.masai.Models.UserDTO;
import com.masai.Services.LoginService;
import com.masai.Services.UserServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/masaicab/user")
public class UserController {

	@Autowired
	private LoginService lService;

	@Autowired
	private UserServices uService;

	@PostMapping("/login")
	public ResponseEntity<String> loginIntoAccountHandler(@RequestBody LoginDTO login) throws LoginException {
		String str = lService.LoginToAccount(login);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutFromAccountHandler(@RequestParam String key) throws LoginException {
		String str = lService.LogOutFromAccount(key);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerAccountHandler(@RequestBody UserDTO user) throws UserException {
		User cus = uService.registerUser(user);
		return new ResponseEntity<User>(cus, HttpStatus.CREATED);
	}

	@GetMapping("/findride")
	public ResponseEntity<List<Driver>> findRide(@RequestParam String key) throws LoginException, DriverException {
		List<Driver> drivers = uService.findRide(key);
		return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
	}

	@PutMapping("/book/{driverId}/{x}/{y}")
	public ResponseEntity<String> bookRide(@PathVariable("driverId") Integer driverId, @PathVariable("x") Integer x,
			@PathVariable("y") Integer y) throws LoginException, DriverException {
		String res = uService.bookRide(null, driverId, x, y);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

}
