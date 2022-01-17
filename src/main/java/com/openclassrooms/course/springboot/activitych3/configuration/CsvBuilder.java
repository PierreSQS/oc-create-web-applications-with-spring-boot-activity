package com.openclassrooms.course.springboot.activitych3.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;

@Configuration
public class CsvBuilder {
	
	@Autowired
	ResourceLoader resLoader;
	
	@Bean
	CsvToBeanBuilder<Rent> getCsvToBeanBuilder() throws IOException {
		Resource res = resLoader.getResource("classpath:rents.csv");	
		
		return new CsvToBeanBuilder<Rent>(new BufferedReader(new InputStreamReader(res.getInputStream())))
				.withType(Rent.class);
	}
	
	

}
