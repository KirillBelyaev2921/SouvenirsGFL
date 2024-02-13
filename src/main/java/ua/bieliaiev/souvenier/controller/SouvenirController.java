package ua.bieliaiev.souvenier.controller;

import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.model.SouvenirService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class SouvenirController {
	private SouvenirService service;

	public SouvenirController(SouvenirService service) {
		this.service = service;
	}

	public void addSouvenir(Souvenir souvenir) {
		service.addSouvenir(souvenir);
	}

	public void addManufacturer(Manufacturer manufacturer) {
		service.addManufacturer(manufacturer);
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

	public void addSouvenir(String name, Manufacturer manufacturer, String date, String price) {
		date = date + ".01";
		LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy'.'MM'.'dd"));
		double newPrice = Double.parseDouble(price);
		addSouvenir(new Souvenir(name, manufacturer, newDate, newPrice));
	}
}