package ua.bieliaiev.souvenier.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Class for encapsulating all souvenirs data and provides CRUD methods.
 * All methods are synchronized so that multiples threads will not get or add
 * inconsistent data.
 */
public class SouvenirStorage implements Serializable {
	@Serial
	private static final long serialVersionUID = 4281506286552116631L;

	private final Set<Souvenir> souvenirs = new HashSet<>();
	private final Set<Manufacturer> manufacturers = new HashSet<>();

	public synchronized boolean addSouvenir(Souvenir souvenir) {
		addManufacturer(souvenir.manufacturer()); // add manufacturer if it is not in database.
		return souvenirs.add(souvenir);
	}

	public synchronized boolean addManufacturer(Manufacturer manufacturer) {
		return manufacturers.add(manufacturer);
	}

	public synchronized Collection<Souvenir> getSouvenirs() {
		return souvenirs;
	}

	public synchronized Collection<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public synchronized void replaceSouvenir(Souvenir previous, Souvenir newSouvenir) {
		removeSouvenir(previous);
		addSouvenir(newSouvenir);
	}

	public synchronized void replaceManufacturer(Manufacturer previous, Manufacturer newManufacturer) {
		manufacturers.remove(previous);
		addManufacturer(newManufacturer);

		/* Change all souvenirs with replaced manufacturers. This is made to prevent changes
		* in mutating data outside of object of this class, with will make thread safety of class and data. */
		var previousSouvenirs = getSouvenirsByManufacturer(previous);
		previousSouvenirs.forEach(souvenirs::remove);
		souvenirs.addAll(previousSouvenirs.stream()
				.map(s -> new Souvenir(s.name(), newManufacturer, s.releaseDate(), s.price()))
				.toList());
	}

	public synchronized boolean removeSouvenir(Souvenir souvenir) {
		return souvenirs.remove(souvenir);
	}

	public synchronized boolean removeManufacturer(Manufacturer manufacturer) {
		boolean result = manufacturers.remove(manufacturer);

		/* Remove all souvenirs with removed manufacturers. This is made to prevent changes
		 * in mutating data outside of object of this class, with will make thread safety of class and data. */
		var previousSouvenirs = getSouvenirsByManufacturer(manufacturer);
		previousSouvenirs.forEach(souvenirs::remove);
		return result;
	}

	public synchronized List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return getSouvenirs().stream()
				.filter(s -> s.manufacturer().equals(manufacturer))
				.toList();
	}
}
