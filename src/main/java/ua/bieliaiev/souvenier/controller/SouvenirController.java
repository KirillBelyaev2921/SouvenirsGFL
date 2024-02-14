package ua.bieliaiev.souvenier.controller;

import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.model.SouvenirService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

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
		if (manufacturer == null) return false;
		if (name.isBlank()) return false;
		if (date.isBlank()) return false;
		if (price.isBlank()) return false;
		try {
			date = date + ".01";
			LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy'.'MM'.'dd"));
			double newPrice = Double.parseDouble(price);
			return addSouvenir(new Souvenir(name, manufacturer, newDate, newPrice));
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean addManufacturer(String name, String country, String email, String phone) {
		if (name.isBlank()) return false;
		if (country.isBlank()) return false;
		return addManufacturer(new Manufacturer(name, country, email, phone));
	}

	public void saveData() {
		service.saveData();
	}
}
