package ua.bieliaiev.souvenier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SouvenirServiceTest {
	private SouvenirService service;
	private Souvenir basicSouvenir;


	@BeforeEach
	void setUp() {
		service = new SouvenirService();
		basicSouvenir =
				new Souvenir("Cup", new Manufacturer("Oracle", "US"),
						LocalDate.of(2020, Month.JANUARY, 1), 29.99);
	}

	@Test
	void testAddSouvenir() {
		service.addSouvenir(basicSouvenir);

		assertThat(service.getSouvenirs())
				.containsExactly(basicSouvenir);
	}

}