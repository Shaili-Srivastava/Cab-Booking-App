package com.masai.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.DriverException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.UserException;
import com.masai.Models.CurrentUserSession;
import com.masai.Models.Driver;
import com.masai.Models.User;
import com.masai.Models.UserDTO;
import com.masai.Repositories.DriverRepo;
import com.masai.Repositories.SessionRepo;
import com.masai.Repositories.UserRepo;
import com.masai.Services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepo uRepo;

	@Autowired
	private DriverRepo dRepo;

	@Autowired
	private SessionRepo sRepo;

	@Override
	public User registerUser(UserDTO userDTO) throws UserException {

		Optional<User> existingUser = uRepo.findById(userDTO.getMobileNumber());

		if (existingUser.isPresent())
			throw new UserException("User already present with this mobile Number");

		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPassword(userDTO.getPassword());
		user.getCurrentPosition()[0] = userDTO.getCurrentPosition_X();
		user.getCurrentPosition()[1] = userDTO.getCurrentPosition_Y();

		User savedUser = uRepo.save(user);

		return savedUser;
	}

	@Override
	public List<Driver> findRide(String sessionKey) throws LoginException, DriverException {

		CurrentUserSession session = sRepo.findByKey(sessionKey);

		if (session == null)
			throw new LoginException("Invalid session key");

		User user = uRepo.findByMobileNumber(session.getMobile());

		List<Driver> allDrivers = dRepo.findAll();

		List<Driver> filtered = new ArrayList<>();
		for(Driver d : allDrivers) {
			int dx = d.getCurrentPosition()[0];
			int dy = d.getCurrentPosition()[1];
			int ux = user.getCurrentPosition()[0];
			int uy = user.getCurrentPosition()[1];

			int x_diff = Math.abs(dx-ux);
			int y_diff = Math.abs(dy - uy);


			int distance = (int) Math.sqrt(((x_diff) * (x_diff)) + (y_diff) * (y_diff));

			if (distance <= 5) {
				filtered.add(d);
			}

		}

		if (filtered.size() == 0)
			throw new DriverException("No Rides Found");

		return filtered;
	}

	@Override
	public String bookRide(String sessionKey, Integer driverId, Integer X, Integer Y)
			throws LoginException, DriverException {

		CurrentUserSession session = sRepo.findByKey(sessionKey);

		if (session == null)
			throw new LoginException("Invalid session key");

		User user = uRepo.findByMobileNumber(session.getMobile());

		Optional<Driver> driver = dRepo.findById(driverId);

		if (!driver.isPresent())
			throw new DriverException("Invalid Driver Id");

		int dx = driver.get().getCurrentPosition()[0];
		int dy = driver.get().getCurrentPosition()[1];
		int ux = user.getCurrentPosition()[0];
		int uy = user.getCurrentPosition()[1];

		int x_diff = Math.abs(dx - ux);
		int y_diff = Math.abs(dy - uy);

		int distance = (int) Math.sqrt(((x_diff) * (x_diff)) + (y_diff) * (y_diff));

		if(distance>5) throw new DriverException("Sorry but the driver is 5 kms away from you.");

		driver.get().getCurrentPosition()[0] = X;
		driver.get().getCurrentPosition()[1] = Y;

		user.getCurrentPosition()[0]=X;
		user.getCurrentPosition()[1]=Y;

		return "Hope you enjoyed the ride :)";
	}

}
