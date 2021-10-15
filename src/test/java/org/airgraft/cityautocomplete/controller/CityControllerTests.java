package org.airgraft.cityautocomplete.controller;

import com.google.gson.Gson;
import org.airgraft.cityautocomplete.model.City;
import org.airgraft.cityautocomplete.model.Suggestion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
public class CityControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityController cityController;

    static City city = new City();
    static String name = "nator";
    static String longitude = "-79.4163";
    static String latitude = "43.70011";

    @BeforeAll
    public static void setup() {
        city.setCountry("BD");
        city.setLatitude("24.41112");
        city.setLongitude("88.98673");
        city.setName("Natore");
    }

    @Test
    public void getSuggestions() throws Exception {
        Suggestion suggestion = Suggestion.builder()
                .name(city.getName() + ", " + city.getCountry())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .score(0.822)
                .build();

        String suggestionsJSON = new Gson().toJson(Collections.singletonList(suggestion));

        given(cityController.getSuggestions(name, null, null)).willReturn(suggestionsJSON);

        mvc.perform(get("/suggestions")
                        .param("q", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().string(suggestionsJSON));
    }

    @Test
    public void getSuggestionsWithLongitude() throws Exception {
        Suggestion suggestion = Suggestion.builder()
                .name(city.getName() + ", " + city.getCountry())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .score(0.411)
                .build();

        String suggestionsJSON = new Gson().toJson(Collections.singletonList(suggestion));

        given(cityController.getSuggestions(name, longitude, null)).willReturn(suggestionsJSON);

        mvc.perform(get("/suggestions")
                        .param("q", name)
                        .param("longitude", longitude))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().string(suggestionsJSON));
    }

    @Test
    public void getSuggestionsWithLatitude() throws Exception {
        Suggestion suggestion = Suggestion.builder()
                .name(city.getName() + ", " + city.getCountry())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .score(0.628)
                .build();

        String suggestionsJSON = new Gson().toJson(Collections.singletonList(suggestion));

        given(cityController.getSuggestions(name, null, latitude)).willReturn(suggestionsJSON);

        mvc.perform(get("/suggestions")
                        .param("q", name)
                        .param("latitude", latitude))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().string(suggestionsJSON));
    }

    @Test
    public void getSuggestionsWithLongitudeAndLatitude() throws Exception {
        Suggestion suggestion = Suggestion.builder()
                .name(city.getName() + ", " + city.getCountry())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .score(0.419)
                .build();

        String suggestionsJSON = new Gson().toJson(Collections.singletonList(suggestion));

        given(cityController.getSuggestions(name, longitude, latitude)).willReturn(suggestionsJSON);

        mvc.perform(get("/suggestions")
                        .param("q", name)
                        .param("longitude", longitude)
                        .param("latitude", latitude))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().string(suggestionsJSON));
    }

    @Test
    public void getSuggestionsWithNoResult() throws Exception {
        String search = "blablabla";
        String suggestionsJSON = new Gson().toJson(List.of());

        given(cityController.getSuggestions(search, null, null)).willReturn(suggestionsJSON);

        mvc.perform(get("/suggestions")
                        .param("q", search))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(content().string(suggestionsJSON));
    }

    @Test
    public void getSuggestionsWithoutQueryParams() throws Exception {
        mvc.perform(get("/suggestions"))
                .andExpect(status().isBadRequest());
    }
}
