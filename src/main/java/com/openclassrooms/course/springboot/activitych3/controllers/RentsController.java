package com.openclassrooms.course.springboot.activitych3.controllers;

import java.util.Collections;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.openclassrooms.course.springboot.activitych3.services.RateConversionService;
import com.openclassrooms.course.springboot.activitych3.services.RentService;

/**
 * This controller provides one HTTP end-point that returns the cost of renting a one-bedroom apartment
 * in the city of major cities in the world in any desired currency.
 * <p>
 * The rental cost in those cities in USD are stored in rents.csv in CSV format.
 * The exchange rate however is retrieved dynamically on the fly from an external API
 * <p>
 * Start the application and go to <a href="http://localhost:8080/rent?city=sydney&currency=AUD">...</a> on your browser
 * to see how it works
 */

@Slf4j
@RestController
public class RentsController {

    private final RentService rentSrv;

    private final RateConversionService rateConvServ;

    public RentsController(RentService rentSrv, RateConversionService rateConvServ) {
        super();
        this.rentSrv = rentSrv;
        this.rateConvServ = rateConvServ;
    }

    /**
     * e.g. <a href="http://localhost:8080/rent?city=sydney&currency=AUD">...</a>
     *
     * @param city     the city that the apartment is located in
     * @param currency the currency we want to see the rental cost in
     * @return the calculated rental cost of a 1 BR apartment
     */
    @GetMapping("/rent")
    public Map<String, String> getRentForCityInCurrency(@RequestParam String city, @RequestParam String currency) {


        return Collections.singletonMap("result", getCityRent(city, currency));
    }

    private String getCityRent(String city, String currency) {

        String result;
        Rent rent = rentSrv.findRentByCity(city);
        if (rent == null) {
            log.error("Error: No rental data found for the city "+city);
            result = "No rental data found for the city: "+city;
        } else {
            Double conversionRateToUsd = rateConvServ.getConversionRateToUsd(currency);
            if (conversionRateToUsd == null) {
                log.error("Error: No exchange rate found for the currency "+currency );
                result = "No exchange rate found for the currency "+currency;
            } else {
                double rentInCurrency = rent.getRentPrice() * conversionRateToUsd;
                result = rentInCurrency + " " + currency;
            }
        }

        return result;
    }

}
