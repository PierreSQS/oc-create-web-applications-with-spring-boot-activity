package com.openclassrooms.course.springboot.activitych3.configuration;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CsvBuilder.class})
public class CsvBuilderTest {
	
	@Autowired
	CsvToBeanBuilder<Rent> csvToBeanBuilder;

	@Test
	public void testCsvFileIsParsed() {
		List<Rent> rents = csvToBeanBuilder.build().parse();
		assertThat(rents.size()).isEqualTo(453);
	}

}
