package org.airgraft.cityautocomplete.utils;

import org.airgraft.cityautocomplete.model.City;
import org.airgraft.cityautocomplete.model.Suggestion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.airgraft.cityautocomplete.utils.ScoreCalculator.calculateScore;

public class ObjectBuilder {
    /**
     * Builds the list of Suggestions that will be returned through the API
     *
     * @param cities    List of City objects
     * @param name      the name used in the query parameter
     * @param longitude the longitude passed as a query parameter
     * @param latitude  the latitude passed as a query parameter
     * @return the list of Suggestions, ranked by score, from biggest to smallest
     */
    public static List<Suggestion> buildSuggestions(List<City> cities, String name, String longitude, String latitude) {
        List<Suggestion> suggestions = new ArrayList<>();

        for (City city : cities) {
            suggestions.add(
                    Suggestion.builder()
                            .name(city.getName() + ", " + city.getCountry())
                            .longitude(city.getLongitude())
                            .latitude(city.getLatitude())
                            .score(calculateScore(city, name, longitude, latitude))
                            .build()
            );
        }

        suggestions.sort(Comparator.comparingDouble(Suggestion::getScore).reversed());

        return suggestions;
    }
}
