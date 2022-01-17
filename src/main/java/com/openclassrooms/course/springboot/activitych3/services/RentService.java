package com.openclassrooms.course.springboot.activitych3.services;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.openclassrooms.course.springboot.activitych3.repositories.RentRepository;

@Slf4j
@Service
public class RentService {
	
	private final RentRepository rentRepo;

	public RentService(RentRepository rentRepo) {
		super();
		this.rentRepo = rentRepo;
	}
	
	public Rent findRentByCity(String city) {
		return rentRepo.findByCity(city).orElse(null);
	}
	
	public List<Rent> getRentList() {
		return rentRepo.getRentList();
	}

}
