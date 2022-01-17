package com.openclassrooms.course.springboot.activitych3.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.course.springboot.activitych3.entities.Rent;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This controller provides one HTTP end-point that returns the cost of renting a one bedroom apartment
 * in the city of major cities in the world in any desired currency.
 *
 * The rental cost in those cities in USD are stored in rents.csv in CSV format.
 * The exchange rate however is retrieved dynamically on the fly from an external API
 *
 * Start the application and go to http://localhost:8080/rent?city=sydney&currency=AUD on your browser
 * to see how it works
 */


//@RestController
public class RentsControllerOld {

    private List<Rent> rents;

    public RentsControllerOld() throws IOException {

        initializeCitiesList();
    }

    /**
     * e.g. http://localhost:8080/rent?city=sydney&currency=AUD
     *
     * @param city the city that the apartment is located in
     * @param currency the currency we want to see the rental cost in
     * @return the calculated rental cost of a 1 BR apartment
     */
    @GetMapping("/rent")
    public Map<String, Object> getRentForCityInCurrency(@RequestParam("city") String city, @RequestParam("currency") String currency) {


        return Collections.singletonMap("result", getCityRent(city, currency));
    }

    private String getCityRent(@RequestParam("city") String city, @RequestParam("currency") String currency) {

        String result;
        Rent rent = findByCity(city);
        if (rent == null) {
            System.out.println("Error: No rental data found for the city "+city);
            result = "No rental data found for the city: "+city;
        } else {
            Double conversionRateToUsd = getConversionRateToUsd(currency);
            if (conversionRateToUsd == null) {
                System.out.println("Error: No exchange rate found for the currency "+currency );
                result = "No exchange rate found for the currency "+currency;
            } else {
                double rentInCurrency = rent.getRentPrice() * conversionRateToUsd;
                result = rentInCurrency + " " + currency;
            }
        }
        return result;
    }

    /**
     * Finds a Rent object in the list of rents by city
     *
     * @param city name of the city
     * @return a Rent object
     */
    private Rent findByCity(String city) {
        return rents.stream().filter(r -> r.getCity().equalsIgnoreCase(city)).findFirst().orElse(null);
    }


    /**
     * Initializes the "rents" list by reading the values in rents.csv file
     * located in the /resources folder using Opencsv library
     *
     * @throws FileNotFoundException
     */
    private void initializeCitiesList() throws IOException {
        InputStream resource = new ClassPathResource("rents.csv").getInputStream();
        rents = new CsvToBeanBuilder<Rent>(new BufferedReader(
                new InputStreamReader(resource))).withType(Rent.class).build().parse();
    }


    /**
     *  This calls an external API on the internet from https://currencylayer.com
     *  It's free and you can get your own access_key from the website
     *  to see and example of the API output go to:
     *  http://apilayer.net/api/live?access_key=e8f742c7609c8d94f4d40f7d1fd104d9&currencies=AUD&format=1
     *
     * @param currency that we want to see the exchange rate of
     * @return the exchange rate USD -> currency
     */
    private Double getConversionRateToUsd(String currency) {

        String apiUrl = "http://apilayer.net/api/live?access_key=e8f742c7609c8d94f4d40f7d1fd104d9&currencies=";
        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + currency.toUpperCase(), ObjectNode.class);

            JsonNode rateNode = response.getBody().path("quotes").path("USD" + currency.toUpperCase());
            if (rateNode.isMissingNode()) {
                return null;
            }
            return rateNode.asDouble();
        } catch (Exception e) {
            System.out.println("ERROR! Exception happened while trying to get currency!" + e.getMessage());
            return null;
        }
    }

}
