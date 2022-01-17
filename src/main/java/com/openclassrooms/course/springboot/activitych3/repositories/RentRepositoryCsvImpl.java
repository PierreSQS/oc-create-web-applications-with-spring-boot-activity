package com.openclassrooms.course.springboot.activitych3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;

@Repository
public class RentRepositoryCsvImpl implements RentRepository {
	
	private List<Rent> rentList;
	
	private final CsvToBeanBuilder<Rent> csvToBeanBuilder;
	
	public RentRepositoryCsvImpl(CsvToBeanBuilder<Rent> csvToBeanBuilder) {
		super();
		this.csvToBeanBuilder = csvToBeanBuilder;
		initializeRentList();
	}


	@Override
	public List<Rent> getRentList() {
		return rentList;
	}


	/**
     * Finds a Rent object in the list of rents by city
     *
     * @param city name of the city
     * @return a Rent object
     */
	@Override
	public Optional<Rent> findByCity(String city) {
		return rentList.stream().filter(rent -> rent.getCity().equalsIgnoreCase(city)).findFirst();
	}
	
	private void initializeRentList() {
		rentList = csvToBeanBuilder.build().parse();
	}
	
}
