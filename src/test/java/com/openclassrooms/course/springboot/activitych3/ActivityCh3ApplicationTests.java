package com.openclassrooms.course.springboot.activitych3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.course.springboot.activitych3.configuration.CsvBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityCh3ApplicationTests {
	@Autowired
	CsvBuilder csvBuilder;

	@Test
	public void contextLoads() {
		assertThat(csvBuilder).isNotNull();
	}

}
