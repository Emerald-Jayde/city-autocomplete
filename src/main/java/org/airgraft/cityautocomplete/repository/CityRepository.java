package org.airgraft.cityautocomplete.repository;

import org.airgraft.cityautocomplete.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    List<City> findByNameContainingIgnoreCase(String name);
}
