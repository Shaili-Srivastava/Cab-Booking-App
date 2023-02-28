package com.masai.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Models.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

}
