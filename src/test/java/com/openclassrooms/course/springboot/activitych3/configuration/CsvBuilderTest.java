package com.openclassrooms.course.springboot.activitych3.configuration;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {CsvBuilder.class})
class CsvBuilderTest {
	
	@Autowired
	CsvToBeanBuilder<Rent> csvToBeanBuilder;

	@Test
	void testCsvFileIsParsed() {
		List<Rent> rents = csvToBeanBuilder.build().parse();
		Rent rent1 = rents.get(17);
		System.out.println(rent1);
		assertThat(rents.size()).isEqualTo(453);

		assertAll("The Rent in Sydney",
				() -> assertEquals(18, rent1.getId()),
				() -> assertEquals("Sydney", rent1.getCity()),
				() -> assertEquals("Australia", rent1.getCountry()),
				() -> assertEquals(1862.37, rent1.getRentPrice())
		);
	}

}
