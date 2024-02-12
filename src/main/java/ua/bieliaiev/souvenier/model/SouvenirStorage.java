package ua.bieliaiev.souvenier.model;

import java.util.*;

public class SouvenirStorage {
	private final Set<Souvenir> souvenirs = new HashSet<>();
	private final Set<Manufacturer> manufacturers = new HashSet<>();

	public void add(Souvenir souvenir) {
		souvenirs.add(souvenir);
	}

	public Collection<Souvenir> getSouvenirs() {
		return souvenirs;
	}

	public Collection<Manufacturer> getManufacturers() {
		return manufacturers;
	}
}
