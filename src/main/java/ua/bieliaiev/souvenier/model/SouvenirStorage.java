package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.util.*;

public class SouvenirStorage implements Serializable {
	private final Set<Souvenir> souvenirs = new HashSet<>();
	private final Set<Manufacturer> manufacturers = new HashSet<>();

	public void addSouvenir(Souvenir souvenir) {
		souvenirs.add(souvenir);
	}

	public void addManufacturer(Manufacturer manufacturer) {
		manufacturers.add(manufacturer);
	}

	public Collection<Souvenir> getSouvenirs() {
		return souvenirs;
	}

	public Collection<Manufacturer> getManufacturers() {
		return manufacturers;
	}
}
