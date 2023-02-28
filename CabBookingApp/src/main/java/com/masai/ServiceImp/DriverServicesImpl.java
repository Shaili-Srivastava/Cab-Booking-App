package com.masai.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Models.Driver;
import com.masai.Models.DriverDTO;
import com.masai.Repositories.DriverRepo;
import com.masai.Services.DriverServices;

@Service
public class DriverServicesImpl implements DriverServices {

	@Autowired
	private DriverRepo dRepo;

	@Override
	public Driver registerDriver(DriverDTO driverDTO) {

		Driver newDriver = new Driver();

		newDriver.setName(driverDTO.getName());
		newDriver.getCurrentPosition()[0] = driverDTO.getCurrentPosition_X();
		newDriver.getCurrentPosition()[1] = driverDTO.getCurrentPosition_Y();
		newDriver.setCab(driverDTO.getCab());

		Driver savedDriver = dRepo.save(newDriver);

		return savedDriver;

	}

}
