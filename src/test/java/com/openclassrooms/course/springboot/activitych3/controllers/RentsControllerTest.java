package com.openclassrooms.course.springboot.activitych3.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.openclassrooms.course.springboot.activitych3.services.RateConversionService;
import com.openclassrooms.course.springboot.activitych3.services.RentService;


@WebMvcTest(controllers = {RentsController.class})
public class RentsControllerTest {
	
	@MockBean
	private RentService rentSrvMock;

	@MockBean
	private RateConversionService rateConvSrvMock;
	
	@Autowired
	MockMvc mockMvc;

	@BeforeAll
	public static void setUpBeforeAll() {
	}

	@BeforeEach
	public void setUp() {
	}

	@Test
	void testgetRentForCityInCurrency() throws Exception {
		// given
		Rent rent = new Rent(1, "Paris", "France", 22.5);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("city", "Paris");
		multiValueMap.add("currency", "XAF");
		given(rentSrvMock.findRentByCity(anyString())).willReturn(rent);
		given(rateConvSrvMock.getConversionRateToUsd(anyString())).willReturn(572.630651);
		
		// when, then
		mockMvc.perform(get("/rent").params(multiValueMap))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", equalTo("12884.1896475 XAF")))
			.andDo(print());
	}

}
