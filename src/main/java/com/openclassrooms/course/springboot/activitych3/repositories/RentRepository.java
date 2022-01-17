package com.openclassrooms.course.springboot.activitych3.repositories;

import java.util.List;
import java.util.Optional;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;

public interface RentRepository {
	List<Rent> getRentList();
	
	Optional<Rent> findByCity(String city);

}
