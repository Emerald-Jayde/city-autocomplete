package org.airgraft.cityautocomplete.service;

import org.airgraft.cityautocomplete.model.City;
import org.airgraft.cityautocomplete.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityAutocompleteRepository;

    @Autowired
    public CityService(CityRepository cityAutocompleteRepository) {
        this.cityAutocompleteRepository = cityAutocompleteRepository;
    }

    public List<City> search(String name) {
        return cityAutocompleteRepository.findByNameContainingIgnoreCase(name);
    }
}
