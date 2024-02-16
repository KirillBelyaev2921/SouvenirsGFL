package ua.bieliaiev.souvenier.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.model.SouvenirService;
import ua.bieliaiev.souvenier.model.SouvenirStorage;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class SouvenirControllerTest {

	private SouvenirController controller;
	private static final Manufacturer oracle = new Manufacturer("Oracle", "US", "", "");
	private static final Manufacturer google = new Manufacturer("Google", "US", "", "");
	private Souvenir firstSouvenirFromOracle;
	private Souvenir secondSouvenirFromOracle;
	private Souvenir firstSouvenirFromGoogle;

	@BeforeEach
	void setUp() {
		SouvenirStorage storage = new SouvenirStorage();
		SouvenirService service = new SouvenirService(storage);
		controller = new SouvenirController(service);
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
	void addSouvenir() {
		String name = "Cup";
		Manufacturer m = oracle;
		String year = "2020.01";
		String price = "29.99";

		controller.addSouvenir(name, m, year, price);

		assertThat(controller.getSouvenirs())
				.contains(firstSouvenirFromOracle);
	}

	@Test
	void addManufacturer() {
		String name = "Oracle";
		String country = "US";

		controller.addManufacturer(name, country, "", "");

		assertThat(controller.getManufacturers())
				.contains(oracle);
	}

	@Test
	void editSouvenir() {
		String name = "Cup";
		Manufacturer m = oracle;
		String year = "2020.01";
		String price = "25.99";
		
		controller.addSouvenir(firstSouvenirFromOracle);
		controller.editSouvenir(firstSouvenirFromOracle, name, m, year, price);

		assertThat(controller.getSouvenirs())
				.contains(new Souvenir(firstSouvenirFromOracle.name(), oracle,
						firstSouvenirFromOracle.releaseDate(), 25.99));
	}

	@Test
	void editManufacturer() {
		String name = "Oracle";
		String country = "United States";

		controller.addManufacturer(oracle);
		controller.editManufacturer(oracle, name, country, "", "");

		assertThat(controller.getManufacturers())
				.contains(new Manufacturer("Oracle", "United States", "", ""));
	}

	@Test
	void getManufacturersWithSouvenirs() {
		controller.addSouvenir(firstSouvenirFromOracle);
		controller.addSouvenir(secondSouvenirFromOracle);
		controller.addSouvenir(firstSouvenirFromGoogle);

		assertThat(controller.getManufacturersWithSouvenirs())
				.contains("""
						Google, US
							Cup, (Google, US), 2024.02, 24.99$""")
				.contains("""
						Oracle, US
							Plate, (Oracle, US), 2019.02, 9.99$
							Cup, (Oracle, US), 2020.01, 29.99$""");
	}

	@Test
	void getManufacturersBySouvenirNameAndYear() {
		controller.addSouvenir(firstSouvenirFromOracle);
		controller.addSouvenir(secondSouvenirFromOracle);
		controller.addSouvenir(firstSouvenirFromGoogle);

		assertThat(controller.getManufacturersBySouvenirNameAndYear("Cup", "2024"))
				.contains("""
						Google, US
							Cup, (Google, US), 2024.02, 24.99$""");
	}

	@Test
	void getSouvenirsGroupingByYears() {
		controller.addSouvenir(firstSouvenirFromOracle);
		controller.addSouvenir(secondSouvenirFromOracle);
		controller.addSouvenir(firstSouvenirFromGoogle);

		assertThat(controller.getSouvenirsGroupingByYears())
				.isEqualTo("""
						2019
							Plate, (Oracle, US), 2019.02, 9.99$
						2020
							Cup, (Oracle, US), 2020.01, 29.99$
						2024
							Cup, (Google, US), 2024.02, 24.99$
							""");
	}
}