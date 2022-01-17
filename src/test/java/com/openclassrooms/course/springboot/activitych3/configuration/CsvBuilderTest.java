package com.openclassrooms.course.springboot.activitych3.configuration;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;
import org.assertj.core.util.Arrays;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CsvBuilder.class})
public class CsvBuilderTest {
	
	@Autowired
	ApplicationContext appCtx;

	@Autowired
	CsvToBeanBuilder<Rent> csvToBeanBuilder;

	@Test
	public void testCsvBuilderToBeanIsCreated() {
		assertThat(csvToBeanBuilder).isNotNull();
	}

	@Test
	public void testCsvFileIsParsed() {
		List<Rent> rents = csvToBeanBuilder.build().parse();
		assertThat(rents.size()).isEqualTo(453);
	}
	
	@Test
	@Ignore("just to check Application Context, not a Test")
	public void showBeans() {
		assertThat(appCtx).isNotNull();
		Arrays.asList(appCtx.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
