package com.masai.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Models.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	public User findByMobileNumber(String mobileNumber);
}
