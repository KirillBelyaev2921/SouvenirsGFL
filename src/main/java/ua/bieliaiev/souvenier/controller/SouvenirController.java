package ua.bieliaiev.souvenier.controller;

import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.model.SouvenirService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

public class SouvenirController {
	private final SouvenirService service;

	public SouvenirController(SouvenirService service) {
		this.service = service;
	}

	public boolean addSouvenir(Souvenir souvenir) {
		return service.addSouvenir(souvenir);
	}

	public boolean addManufacturer(Manufacturer manufacturer) {
		return service.addManufacturer(manufacturer);
	}

	public Collection<Souvenir> getSouvenirs() {
		return service.getSouvenirs();
	}

	public Collection<Manufacturer> getManufacturers() {
		return service.getManufacturers();
	}

	public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return service.getSouvenirsByManufacturer(manufacturer);
	}

	public boolean addSouvenir(String name, Manufacturer manufacturer, String date, String price) {
		Souvenir newSouvenir = parseSouvenir(name, manufacturer, date, price);
		if (newSouvenir == null) {
			return false;
		} else {
			return addSouvenir(newSouvenir);
		}
	}

	public boolean addManufacturer(String name, String country, String email, String phone) {
		return addManufacturer(parseManufacturer(name, country, email, phone));
	}

	public void saveData() {
		service.saveData();
	}

	public boolean editSouvenir(Souvenir past, String name, Manufacturer manufacturer, String date, String price) {
		if (past == null) return false;
		Souvenir newSouvenir = parseSouvenir(
				name, manufacturer, date, price);
		if (past.exactSame(newSouvenir)) return false;

		service.editSouvenir(past, newSouvenir);
		return true;
	}

	public boolean editManufacturer(Manufacturer past, String name, String country, String email, String phone) {
		if (past == null) return false;
		Manufacturer newManufacturer = parseManufacturer(
				name, country, email, phone);
		if (past.exactSame(newManufacturer)) return false;

		service.editManufacturer(past, newManufacturer);
		return true;
	}

	public boolean removeSouvenir(Souvenir souvenir) {
		return service.removeSouvenir(souvenir);
	}
	public boolean removeManufacturer(Manufacturer manufacturer) {
		return service.removeManufacturer(manufacturer);
	}

	public List<Manufacturer> getManufacturersByAnyLowerPriceSouvenir(String price) {
		try {
			return service.getManufacturersByAnyLowerPriceSouvenir(Double.parseDouble(price));
		} catch (NumberFormatException e) {
			return List.of();
		}
	}
	public List<Manufacturer> getManufacturersByAllLowerPriceSouvenir(String price) {
		try {
			return service.getManufacturersByAllLowerPriceSouvenir(Double.parseDouble(price));
		} catch (NumberFormatException e) {
			return List.of();
		}
	}

	private Souvenir parseSouvenir(String name, Manufacturer manufacturer, String date, String price) {
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

	private Manufacturer parseManufacturer(String name, String country, String email, String phone) {
		if (name.isBlank()) return null;
		if (country.isBlank()) return null;
		return new Manufacturer(name, country, email, phone);
	}

	public String getManufacturersWithSouvenirs() {
		var map = service.getSouvenirs().stream().collect(groupingBy(Souvenir::manufacturer, TreeMap::new, toList()));
		StringBuilder result = new StringBuilder();
		map.forEach((k, v)-> {
			result.append(k).append('\n');
			v.forEach(s -> result.append('\t').append(s).append('\n'));
		});
		return result.toString();
	}

	public String getManufacturersBySouvenirNameAndYear(String name, String year) {
		int newYear;
		try {
			newYear = Integer.parseInt(year);
		} catch (NumberFormatException e) {
			return "";
		}
		var map = service.getSouvenirsByNameAndManufacturerAndYear(name, newYear)
				.stream().collect(groupingBy(Souvenir::manufacturer, TreeMap::new, toList()));
		StringBuilder result = new StringBuilder();
		map.forEach((k, v)-> {
			result.append(k).append('\n');
			v.forEach(s -> result.append('\t').append(s).append('\n'));
		});
		return result.toString();
	}


	public String getSouvenirsGroupingByYears() {
		var map = service.getSouvenirs().stream().collect(groupingBy(s -> s.releaseDate().getYear(),
				TreeMap::new, toList()));

		StringBuilder result = new StringBuilder();
		map.forEach((k, v)-> {
			result.append(k).append('\n');
			v.forEach(s -> result.append('\t').append(s).append('\n'));
		});
		return result.toString();
	}
}
