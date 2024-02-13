package ua.bieliaiev.souvenier.model;

import java.util.Collection;
import java.util.List;

public class SouvenirService {
	private final SouvenirStorage souvenirs = new SouvenirStorage();

	public void addSouvenir(Souvenir souvenir) {
		souvenirs.addSouvenir(souvenir);
	}

	public void addManufacturer(Manufacturer manufacturer) {
		souvenirs.addManufacturer(manufacturer);
	}

	public Collection<Souvenir> getSouvenirs() {
		return souvenirs.getSouvenirs();
	}

	public Collection<Manufacturer> getManufacturers() {
		return souvenirs.getManufacturers();
	}

	public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return getSouvenirs().stream()
				.filter(s -> s.manufacturer().equals(manufacturer))
				.toList();
	}
}
