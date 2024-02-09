package ua.bieliaiev.souvenier.model;

import java.util.List;

public class SouvenirService {
	private final SouvenirStorage souvenirs = new SouvenirStorage();

	public void addSouvenir(Souvenir souvenir) {
		souvenirs.add(souvenir);
	}

	public List<Souvenir> getSouvenirs() {
		return souvenirs.getSouvenirs();
	}

	public List<Manufacturer> getManufacturers() {
		return souvenirs.getManufacturers();
	}

	public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return getSouvenirs().stream()
				.filter(s -> s.getManufacturer().equals(manufacturer))
				.toList();
	}
}
