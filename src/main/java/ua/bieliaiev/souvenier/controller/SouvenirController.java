package ua.bieliaiev.souvenier.controller;

import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.model.SouvenirService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static ua.bieliaiev.souvenier.util.NumberParser.parseDouble;
import static ua.bieliaiev.souvenier.util.NumberParser.parseInt;

/**
 * Controller to interact with view and model classes.
 */
public class SouvenirController {
	private final SouvenirService service;

	public SouvenirController(SouvenirService service) {
		this.service = service;
	}

	/* Adding operations */
	public boolean addSouvenir(Souvenir souvenir) {
		return service.addSouvenir(souvenir);
	}

	public boolean addManufacturer(Manufacturer manufacturer) {
		return service.addManufacturer(manufacturer);
	}

	/**
	 * Method add Souvenir by String names, date and price, and by object of Manufacturer.
	 *
	 * @return true if adding is successfully, false if otherwise
	 */
	public boolean addSouvenir(String name, Manufacturer manufacturer, String date, String price) {
		Souvenir newSouvenir = validateSouvenir(name, manufacturer, date, price);
		return newSouvenir != null && addSouvenir(newSouvenir);
	}

	/**
	 * Method add Manufacturer by String names, country, email, and phone.
	 *
	 * @return true if adding is successfully, false if otherwise
	 */
	public boolean addManufacturer(String name, String country, String email, String phone) {
		Manufacturer newManufacturer = validateManufacturer(name, country, email, phone);
		return newManufacturer != null && addManufacturer(newManufacturer);
	}

	/* Edit operations */

	public boolean editSouvenir(Souvenir past, String name, Manufacturer manufacturer, String date, String price) {
		if (past == null) return false; // check if old value was chosen.
		Souvenir newSouvenir = validateSouvenir(name, manufacturer, date, price);
		if (past.exactSame(newSouvenir)) return false; // check if no changes was made.

		service.editSouvenir(past, newSouvenir);
		return true;
	}

	public boolean editManufacturer(Manufacturer past, String name, String country, String email, String phone) {
		if (past == null) return false; // check if old value was chosen.
		Manufacturer newManufacturer = validateManufacturer(name, country, email, phone);
		if (past.exactSame(newManufacturer)) return false; // check if no changes was made.

		service.editManufacturer(past, newManufacturer);
		return true;
	}

	/* Removing operations */
	public boolean removeSouvenir(Souvenir souvenir) {
		return service.removeSouvenir(souvenir);
	}

	public boolean removeManufacturer(Manufacturer manufacturer) {
		return service.removeManufacturer(manufacturer);
	}

	/* Selecting operations */
	public Collection<Souvenir> getSouvenirs() {
		return service.getSouvenirs();
	}

	public Collection<Manufacturer> getManufacturers() {
		return service.getManufacturers();
	}

	/**
	 * @return all souvenirs with passed manufacturer
	 */
	public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return service.getSouvenirsByManufacturer(manufacturer);
	}

	/**
	 * Returns list of manufacturers, which have at least 1 souvenir with lower price
	 * that price parameter.
	 */
	public List<Manufacturer> getManufacturersByAnyLowerPriceSouvenir(String price) {
		return getListByPrice(price, service::getManufacturersByAnyLowerPriceSouvenir);
	}

	/**
	 * Returns list of manufacturers, which have all their souvenirs with lower price
	 * that price parameter. Manufacturers with no souvenirs are not included.
	 */
	public List<Manufacturer> getManufacturersByAllLowerPriceSouvenir(String price) {
		return getListByPrice(price, service::getManufacturersByAllLowerPriceSouvenir);
	}

	/**
	 * Returns list of souvenirs, which manufacturer is from country passed in parameter.
	 */
	public List<Souvenir> getSouvenirsByCountry(String country) {
		return service.getSouvenirsByCountry(country);
	}

	/**
	 * Return list of manufacturers by price and function from service.
	 * Return empty list if price String parameter is not double.
	 */
	private List<Manufacturer> getListByPrice(String price, Function<Double, List<Manufacturer>> f) {
		OptionalDouble parsedPrice = parseDouble(price);
		return parsedPrice.isPresent()
				? f.apply(parsedPrice.getAsDouble())
				: List.of();
	}

	/**
	 * Returns string with manufacturers on 1 level and their souvenirs on
	 * 2 level (with tabulation).
	 */
	public String getManufacturersWithSouvenirs() {
		var map = service.getSouvenirs().stream().collect(groupingBy(Souvenir::manufacturer, TreeMap::new, toList()));
		return getString(map);
	}

	/**
	 * Returns string with manufacturers on 1 level and their souvenirs on
	 * 2 level (with tabulation), that has name and release year equals to parameters.
	 */
	public String getManufacturersBySouvenirNameAndYear(String name, String yearString) {
		OptionalInt oYear = parseInt(yearString);
		if (oYear.isEmpty()) return "";
		var map = service.getSouvenirsByNameAndManufacturerAndYear(name, oYear.getAsInt())
				.stream().collect(groupingBy(Souvenir::manufacturer, TreeMap::new, toList()));
		return getString(map);
	}

	/**
	 * Returns string with years on 1 level and souvenirs released on this year on
	 * 2 level (with tabulation).
	 */
	public String getSouvenirsGroupingByYears() {
		var map = service.getSouvenirs().stream().collect(groupingBy(s -> s.releaseDate().getYear(),
				TreeMap::new, toList()));

		return getString(map);
	}

	public void saveData() {
		service.saveData();
	}

	/**
	 * Returns string with key on 1 level and list of objects on
	 * 2 level (with tabulation).
	 */
	private static <T> String getString(TreeMap<?, List<T>> map) {
		StringBuilder result = new StringBuilder();
		map.forEach((k, v) -> {
			result.append(k).append('\n');
			v.forEach(s -> result.append('\t').append(s).append('\n'));
		});
		return result.toString();
	}

	/**
	 * Returns Souvenir object if souvenir parameters are valid, and null otherwise.
	 */
	private Souvenir validateSouvenir(String name, Manufacturer manufacturer, String date, String price) {
		if (manufacturer == null) return null;
		if (name.isBlank()) return null;
		if (date.isBlank()) return null;
		if (price.isBlank()) return null;
		try {
			date = date + ".01";
			LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy'.'MM'.'dd"));
			double newPrice = Double.parseDouble(price);
			return new Souvenir(name, manufacturer, newDate, newPrice);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns Manufacturer object if manufacturer parameters are valid, and null otherwise.
	 */
	private Manufacturer validateManufacturer(String name, String country, String email, String phone) {
		if (name.isBlank()) return null;
		if (country.isBlank()) return null;
		return new Manufacturer(name, country, email, phone);
	}

}
