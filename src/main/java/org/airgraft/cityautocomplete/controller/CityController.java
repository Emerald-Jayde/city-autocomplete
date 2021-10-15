package org.airgraft.cityautocomplete.controller;

import com.google.gson.Gson;
import org.airgraft.cityautocomplete.model.City;
import org.airgraft.cityautocomplete.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.airgraft.cityautocomplete.utils.ObjectBuilder.buildSuggestions;

@RestController
public class CityController {

    private final CityService cityAutocompleteService;

    @Autowired
    public CityController(CityService cityAutocompleteService) {
        this.cityAutocompleteService = cityAutocompleteService;
    }

    /**
     * GET /suggestions
     * Searches DB for partial or total matches to search parameter.
     * Returns suggestions, ranked in descending order.
     *
     * @param name      the partial or complete name of a city
     * @param longitude longitude of the city to search
     * @param latitude  latitude of the city to search
     * @return JSON response is a list of Suggestion objects
     */
    @GetMapping("/suggestions")
    public String getSuggestions(@RequestParam(name = "q") String name,
                      @RequestParam(value = "longitude", required = false) String longitude,
                      @RequestParam(value = "latitude", required = false) String latitude) {
        List<City> cities = cityAutocompleteService.search(name);

        return new Gson().toJson(
            buildSuggestions(cities, name, longitude, latitude)
        );
    }
}
