package com.openclassrooms.course.springboot.activitych3.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Objects;

@Slf4j
@Service
public class RateConversionService {
	
    /**
     *  This calls an external API on the internet from <a href="https://currencylayer.com">...</a>
     *  It's free, and you can get your own access_key from the website
     *  to see and example of the API output go to:
     *  <a href="http://apilayer.net/api/live?access_key=e8f742c7609c8d94f4d40f7d1fd104d9&currencies=AUD&format=1">...</a>
     *
     * @param currency that we want to see the exchange rate of
     * @return the exchange rate USD -> currency
     */
    public Double getConversionRateToUsd(String currency) {

        String apiUrl = "http://apilayer.net/api/live?access_key=e8f742c7609c8d94f4d40f7d1fd104d9&currencies=";
        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + currency.toUpperCase(), ObjectNode.class);

            JsonNode rateNode = Objects.requireNonNull(response.getBody()).path("quotes").path("USD" + currency.toUpperCase());
            if (rateNode.isMissingNode()) {
                return null;
            }
            return rateNode.asDouble();
        } catch (Exception e) {
            log.error("ERROR! Exception happened while trying to get currency!" + e.getMessage());
            return null;
        }
    }


}
