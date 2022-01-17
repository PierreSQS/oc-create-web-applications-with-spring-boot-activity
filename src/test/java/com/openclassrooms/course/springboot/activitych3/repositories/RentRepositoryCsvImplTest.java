package com.openclassrooms.course.springboot.activitych3.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentRepositoryCsvImplTest {
	
	@Autowired
	private RentRepositoryCsvImpl rentRepoCsv;

	@BeforeClass
	public static void setUpBeforeClass() {
	}

	@Before
	public void setUp() {
	}

	@Test
	public void testParsedRentList() {
		List<Rent> rentList = rentRepoCsv.getRentList();
		assertThat(rentList.size()).isEqualTo(453);
	}

}
