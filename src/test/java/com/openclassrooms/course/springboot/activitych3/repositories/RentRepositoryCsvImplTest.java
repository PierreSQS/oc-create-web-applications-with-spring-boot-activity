package com.openclassrooms.course.springboot.activitych3.repositories;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RentRepositoryCsvImplTest {
	
	@Autowired
	RentRepository rentRepoCsv;

	@BeforeAll
	public static void setUpBeforeClass() {
	}

	@BeforeEach
	public void setUp() {
	}

	@Test
	void testParsedRentList() {
		List<Rent> rentList = rentRepoCsv.getRentList();
		assertThat(rentList.size()).isEqualTo(453);
	}

}
