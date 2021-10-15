package org.airgraft.cityautocomplete;

import org.airgraft.cityautocomplete.controller.CityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CityAutocompleteApplicationTests {

	@Autowired
	private CityController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
