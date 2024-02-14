package ua.bieliaiev.souvenier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class SouvenirServiceTest {
	private SouvenirStorage storage;
	private SouvenirService service;
	private Souvenir firstSouvenirFromOracle;
	private Souvenir secondSouvenirFromOracle;
	private Souvenir otherSouvenir;


	@BeforeEach
	void setUp() {
		storage = new SouvenirStorage();
		service = new SouvenirService(storage);
		firstSouvenirFromOracle =
				new Souvenir("Cup", new Manufacturer("Oracle", "US", "", ""),
						LocalDate.of(2020, Month.JANUARY, 1), 29.99);
		secondSouvenirFromOracle =
				new Souvenir("Plate", new Manufacturer("Oracle", "US", "", ""),
						LocalDate.of(2019, Month.FEBRUARY, 12), 9.99);
		otherSouvenir =
				new Souvenir("Cup", new Manufacturer("Google", "US", "", ""),
						LocalDate.of(2024, Month.FEBRUARY, 5), 24.99);
	}

	@Test
	void testAddSouvenir() {
		service.addSouvenir(firstSouvenirFromOracle);

		assertThat(service.getSouvenirs())
				.containsExactly(firstSouvenirFromOracle);
	}

	@Test
	void testSouvenirsByManufacturer() {
		Manufacturer m = new Manufacturer("Oracle", "US", "", "");

		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(otherSouvenir);

		assertThat(service.getSouvenirsByManufacturer(m))
				.contains(firstSouvenirFromOracle, secondSouvenirFromOracle);
	}

}