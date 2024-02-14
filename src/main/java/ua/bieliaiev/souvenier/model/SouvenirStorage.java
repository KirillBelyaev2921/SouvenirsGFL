package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.util.*;

public class SouvenirStorage implements Serializable {
	private final Set<Souvenir> souvenirs = new HashSet<>();
	private final Set<Manufacturer> manufacturers = new HashSet<>();

	public boolean addSouvenir(Souvenir souvenir) {
		return souvenirs.add(souvenir);
	}

	public boolean addManufacturer(Manufacturer manufacturer) {
		return manufacturers.add(manufacturer);
	}

	public Collection<Souvenir> getSouvenirs() {
		return souvenirs;
	}

	public Collection<Manufacturer> getManufacturers() {
		return manufacturers;
	}
}
