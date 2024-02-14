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

	public void replaceSouvenir(Souvenir previous, Souvenir newSouvenir) {
		souvenirs.remove(previous);
		addSouvenir(newSouvenir);
	}

	public void replaceManufacturer(Manufacturer previous, Manufacturer newManufacturer) {
		manufacturers.remove(previous);
		addManufacturer(newManufacturer);

		var previousSouvenirs = getSouvenirsByManufacturer(previous);
		previousSouvenirs.forEach(souvenirs::remove);
		souvenirs.addAll(previousSouvenirs.stream()
				.map(s -> new Souvenir(s.name(), newManufacturer, s.releaseDate(), s.price()))
				.toList());
	}

	public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return getSouvenirs().stream()
				.filter(s -> s.manufacturer().equals(manufacturer))
				.toList();
	}
}
