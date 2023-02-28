package com.masai.Repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.masai.Models.CurrentUserSession;

@Repository
public interface SessionRepo extends JpaRepositoryImplementation<CurrentUserSession, String> {

	public CurrentUserSession findByKey(String Key);

}
