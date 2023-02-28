package com.masai.Services;



import com.masai.Exceptions.LoginException;
import com.masai.Models.LoginDTO;

public interface LoginService {

	public String LoginToAccount(LoginDTO loginDTO) throws LoginException;

	public String LogOutFromAccount(String Key)throws LoginException;

}
