package ua.bieliaiev.souvenier.model;

import java.time.LocalDate;

public class Souvenir {
	private String name;
	private Manufacturer manufacturer;
	private LocalDate releaseDate;
	private double price;

	public Souvenir(String name, Manufacturer manufacturer, LocalDate releaseDate, double price) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.releaseDate = releaseDate;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
