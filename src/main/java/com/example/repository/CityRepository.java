package com.example.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.City;

public interface CityRepository extends JpaRepository<City, BigDecimal>{
	
	
}