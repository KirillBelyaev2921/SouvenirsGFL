package ua.bieliaiev.souvenier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class SouvenirStorageTest {
	private SouvenirStorage storage = new SouvenirStorage();
	private static final Manufacturer oracle = new Manufacturer("Oracle", "US", "", "");
	private static final Manufacturer google = new Manufacturer("Google", "US", "", "");
	private Souvenir firstSouvenirFromOracle;
	private Souvenir secondSouvenirFromOracle;
	private Souvenir firstSouvenirFromGoogle;

	@BeforeEach
	void setUp() {
		storage = new SouvenirStorage();
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
		storage.addSouvenir(firstSouvenirFromOracle);

		assertThat(storage.getSouvenirs())
				.containsExactly(firstSouvenirFromOracle);
	}

	@Test
	void testAddManufacturer() {
		storage.addManufacturer(oracle);

		assertThat(storage.getManufacturers())
				.containsExactly(oracle);
	}

	@Test
	void testReplaceSouvenir() {
		storage.addSouvenir(firstSouvenirFromOracle);
		storage.replaceSouvenir(firstSouvenirFromOracle, secondSouvenirFromOracle);

		assertThat(storage.getSouvenirs())
				.containsExactly(secondSouvenirFromOracle);
	}

	@Test
	void testReplaceManufacturer() {
		Manufacturer newOracle = new Manufacturer("Oracle", "United States", "", "012345567");
		storage.addManufacturer(oracle);
		storage.addSouvenir(firstSouvenirFromOracle);

		storage.replaceManufacturer(oracle, newOracle);

		assertThat(storage.getManufacturers())
				.containsExactly(newOracle);
		assertThat(storage.getSouvenirs())
				.containsExactly(new Souvenir(firstSouvenirFromOracle.name(), newOracle,
						firstSouvenirFromOracle.releaseDate(), firstSouvenirFromOracle.price()));
	}

	@Test
	void testRemoveSouvenir() {
		storage.addSouvenir(firstSouvenirFromOracle);
		storage.addSouvenir(secondSouvenirFromOracle);
		storage.addSouvenir(firstSouvenirFromGoogle);
		storage.removeSouvenir(firstSouvenirFromOracle);

		assertThat(storage.getSouvenirs())
				.contains(secondSouvenirFromOracle, firstSouvenirFromGoogle);
	}

	@Test
	void testRemoveManufacturerWith2Souvenirs() {
		storage.addSouvenir(firstSouvenirFromOracle);
		storage.addSouvenir(secondSouvenirFromOracle);
		storage.addSouvenir(firstSouvenirFromGoogle);
		storage.removeManufacturer(oracle);

		assertThat(storage.getSouvenirs())
				.contains(firstSouvenirFromGoogle);
	}
	@Test
	void testRemoveManufacturerWith1Souvenir() {
		storage.addSouvenir(firstSouvenirFromOracle);
		storage.addSouvenir(secondSouvenirFromOracle);
		storage.addSouvenir(firstSouvenirFromGoogle);
		storage.removeManufacturer(google);

		assertThat(storage.getSouvenirs())
				.contains(firstSouvenirFromOracle, secondSouvenirFromOracle);
	}

	@Test
	void testGetSouvenirsByManufacturerWith2Souvenirs() {
		storage.addSouvenir(firstSouvenirFromOracle);
		storage.addSouvenir(secondSouvenirFromOracle);
		storage.addSouvenir(firstSouvenirFromGoogle);

		assertThat(storage.getSouvenirsByManufacturer(oracle))
				.contains(firstSouvenirFromOracle, secondSouvenirFromOracle);
	}
	@Test
	void testGetSouvenirsByManufacturerWith1Souvenir() {
		storage.addSouvenir(firstSouvenirFromOracle);
		storage.addSouvenir(secondSouvenirFromOracle);
		storage.addSouvenir(firstSouvenirFromGoogle);

		assertThat(storage.getSouvenirsByManufacturer(google))
				.contains(firstSouvenirFromGoogle);
	}
}