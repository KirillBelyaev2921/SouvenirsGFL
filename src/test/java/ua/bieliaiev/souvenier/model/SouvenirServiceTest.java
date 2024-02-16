package ua.bieliaiev.souvenier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class SouvenirServiceTest {
	private SouvenirService service;
	private static final Manufacturer oracle = new Manufacturer("Oracle", "US", "", "");
	private static final Manufacturer google = new Manufacturer("Google", "US", "", "");
	private Souvenir firstSouvenirFromOracle;
	private Souvenir secondSouvenirFromOracle;
	private Souvenir firstSouvenirFromGoogle;

	@BeforeEach
	void setUp() {
		SouvenirStorage storage = new SouvenirStorage();
		service = new SouvenirService(storage);
		firstSouvenirFromOracle =
				new Souvenir("Cup", oracle,
						LocalDate.of(2020, Month.JANUARY, 1), 29.99);
		secondSouvenirFromOracle =
				new Souvenir("Plate", oracle,
						LocalDate.of(2019, Month.FEBRUARY, 12), 9.99);
		firstSouvenirFromGoogle =
				new Souvenir("Cup", google,
						LocalDate.of(2024, Month.FEBRUARY, 5), 24.99);
	}

	@Test
	void testAddSouvenir() {
		service.addSouvenir(firstSouvenirFromOracle);

		assertThat(service.getSouvenirs())
				.containsExactly(firstSouvenirFromOracle);
	}

	@Test
	void testAddManufacturer() {
		service.addManufacturer(oracle);

		assertThat(service.getManufacturers())
				.containsExactly(oracle);
	}

	@Test
	void testEditSouvenir() {
		service.addSouvenir(firstSouvenirFromOracle);
		service.editSouvenir(firstSouvenirFromOracle, secondSouvenirFromOracle);

		assertThat(service.getSouvenirs())
				.containsExactly(secondSouvenirFromOracle);
	}

	@Test
	void testEditManufacturer() {
		Manufacturer newOracle = new Manufacturer("Oracle", "United States", "", "012345567");
		service.addManufacturer(oracle);
		service.addSouvenir(firstSouvenirFromOracle);

		service.editManufacturer(oracle, newOracle);

		assertThat(service.getManufacturers())
				.containsExactly(newOracle);
		assertThat(service.getSouvenirs())
				.containsExactly(new Souvenir(firstSouvenirFromOracle.name(), newOracle,
						firstSouvenirFromOracle.releaseDate(), firstSouvenirFromOracle.price()));
	}

	@Test
	void testRemoveSouvenir() {
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);
		service.removeSouvenir(firstSouvenirFromOracle);

		assertThat(service.getSouvenirs())
				.contains(secondSouvenirFromOracle, firstSouvenirFromGoogle);
	}

	@Test
	void testRemoveManufacturerWith2Souvenirs() {
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);
		service.removeManufacturer(oracle);

		assertThat(service.getSouvenirs())
				.contains(firstSouvenirFromGoogle);
	}

	@Test
	void testRemoveManufacturerWith1Souvenir() {
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);
		service.removeManufacturer(google);

		assertThat(service.getSouvenirs())
				.contains(firstSouvenirFromOracle, secondSouvenirFromOracle);
	}

	@Test
	void testGetSouvenirsByManufacturerWith2Souvenirs() {
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);

		assertThat(service.getSouvenirsByManufacturer(oracle))
				.contains(firstSouvenirFromOracle, secondSouvenirFromOracle);
	}

	@Test
	void testGetSouvenirsByManufacturerWith1Souvenir() {
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);

		assertThat(service.getSouvenirsByManufacturer(google))
				.contains(firstSouvenirFromGoogle);
	}

	@Test
	void testGetManufacturersByAnyLowerPriceSouvenir() {
		double price = 25;
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);

		assertThat(service.getManufacturersByAnyLowerPriceSouvenir(price))
				.contains(oracle, google);
	}

	@Test
	void testGetManufacturersByAllLowerPriceSouvenir() {
		double price = 25;
		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);

		assertThat(service.getManufacturersByAllLowerPriceSouvenir(price))
				.contains(google);
	}


	@Test
	void testGetSouvenirsByNameAndManufacturerAndYear() {
		String name = "Cup";
		int year = 2024;

		service.addSouvenir(firstSouvenirFromOracle);
		service.addSouvenir(secondSouvenirFromOracle);
		service.addSouvenir(firstSouvenirFromGoogle);

		assertThat(service.getSouvenirsByNameAndManufacturerAndYear(name, google, year))
				.contains(firstSouvenirFromGoogle);
	}

}