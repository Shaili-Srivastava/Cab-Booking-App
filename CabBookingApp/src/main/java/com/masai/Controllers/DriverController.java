package com.masai.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Models.Driver;
import com.masai.Models.DriverDTO;
import com.masai.Services.DriverServices;

@RestController
@RequestMapping("/masaicab/driver")
public class DriverController {

	@Autowired
	private DriverServices dService;

	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriver(DriverDTO driver) {
		Driver savedDriver = dService.registerDriver(driver);
		return new ResponseEntity<Driver>(savedDriver, HttpStatus.CREATED);
	}
}
