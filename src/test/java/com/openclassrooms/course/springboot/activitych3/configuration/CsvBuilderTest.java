package com.openclassrooms.course.springboot.activitych3.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvBuilderTest {
	
	@Autowired
	ApplicationContext appCtx;

	@Autowired
	CsvToBeanBuilder<Rent> csvToBeanBuilder;
	
	@Autowired
	CsvBuilder csvBuilder;

	@Test
	public void testCsvBuilderToBeanIsCreated() {
		assertThat(csvToBeanBuilder).isNotNull();
	}

	@Test
	public void testCsvFileIsParsed() throws IOException {
//		List<Rent> rents = csvToBeanBuilder.build().parse();
		List<Rent> rents = csvBuilder.getCsvToBeanBuilder().build().parse();
		assertThat(rents.size()).isEqualTo(453);
	}
	
	@Test
	@Ignore("just to check Application Context, not a Test")
	public void showBeans() {
		assertThat(appCtx).isNotNull();
		Arrays.asList(appCtx.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
