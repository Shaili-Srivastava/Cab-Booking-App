package com.masai.ServiceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.LoginException;
import com.masai.Models.CurrentUserSession;
import com.masai.Models.LoginDTO;
import com.masai.Models.User;
import com.masai.Repositories.SessionRepo;
import com.masai.Repositories.UserRepo;
import com.masai.Services.LoginService;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepo uRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String LoginToAccount(LoginDTO loginDTO) throws LoginException {
		User existingCustomer = uRepo.findByMobileNumber(loginDTO.getMobile());
		
		if (existingCustomer == null)
			throw new LoginException("Please enter valid mobile number");
		Optional<CurrentUserSession> validCustomerSessionOpt = sRepo.findById(existingCustomer.getMobileNumber());
		if (validCustomerSessionOpt.isPresent())
			throw new LoginException("User already logged in with this number");
		if (existingCustomer.getPassword().equals(loginDTO.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setKey(key);
			currentUserSession.setMobile(existingCustomer.getMobileNumber());
			sRepo.save(currentUserSession);
			return key;
		} else
			throw new LoginException("Please enter valid password");
	}

	@Override
	public String LogOutFromAccount(String Key) throws LoginException {
		CurrentUserSession currentUserSession = sRepo.findByKey(Key);
		if (currentUserSession == null)
			throw new LoginException("User not logged in with this number");
		else {
			sRepo.delete(currentUserSession);
			return "Logged out !";
		}
	}

}
