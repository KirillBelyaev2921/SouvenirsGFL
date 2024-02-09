package ua.bieliaiev.souvenier.model;

import java.util.ArrayList;
import java.util.List;

public class SouvenirStorage {
	private final List<Souvenir> souvenirs = new ArrayList<>();

	public void add(Souvenir souvenir) {
		souvenirs.add(souvenir);
	}

	public List<Souvenir> getSouvenirs() {
		return souvenirs;
	}

	public List<Manufacturer> getManufacturers() {
		return new ArrayList<>();
	}
}
