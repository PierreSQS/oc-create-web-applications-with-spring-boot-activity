package com.openclassrooms.course.springboot.activitych3.configuration;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CsvBuilder.class})
class CsvBuilderTest {
	
	@Autowired
	CsvToBeanBuilder<Rent> csvToBeanBuilder;

	@Test
	void testCsvFileIsParsed() {
		List<Rent> rents = csvToBeanBuilder.build().parse();
		assertThat(rents.size()).isEqualTo(453);
	}

}
