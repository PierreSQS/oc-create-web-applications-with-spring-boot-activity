package com.openclassrooms.course.springboot.activitych3;

import com.openclassrooms.course.springboot.activitych3.configuration.CsvBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ActivityCh3ApplicationTests {
	@Autowired
	CsvBuilder csvBuilder;

	@Test
	void contextLoads() {
		assertThat(csvBuilder).isNotNull();
	}

}
