package ua.bieliaiev.souvenier.model;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SouvenirService {
	private final SouvenirStorage souvenirs;
	private final SouvenirFileHandler fileHandler;

	public SouvenirService(SouvenirFileHandler fileHandler) throws IOException, ClassNotFoundException {
		this.fileHandler = fileHandler;
		souvenirs = fileHandler.readSouvenirs();
	}

	public SouvenirService(SouvenirStorage souvenirs) {
		this.souvenirs = souvenirs;
		this.fileHandler = null;
	}

	public boolean addSouvenir(Souvenir souvenir) {
		return souvenirs.addSouvenir(souvenir);
	}

	public boolean addManufacturer(Manufacturer manufacturer) {
		return souvenirs.addManufacturer(manufacturer);
	}

	public void editSouvenir(Souvenir previous, Souvenir newSouvenir) {
		souvenirs.replaceSouvenir(previous, newSouvenir);
	}
	public void editManufacturer(Manufacturer previous, Manufacturer newManufacturer) {
		souvenirs.replaceManufacturer(previous, newManufacturer);
	}
	public boolean removeSouvenir(Souvenir souvenir) {
		return souvenirs.removeSouvenir(souvenir);
	}
	public boolean removeManufacturer(Manufacturer manufacturer) {
		return souvenirs.removeManufacturer(manufacturer);
	}

	public Collection<Souvenir> getSouvenirs() {
		return souvenirs.getSouvenirs();
	}

	public Collection<Manufacturer> getManufacturers() {
		return souvenirs.getManufacturers();
	}

	public List<Souvenir> getSouvenirsByManufacturer(Manufacturer manufacturer) {
		return souvenirs.getSouvenirsByManufacturer(manufacturer);
	}

	public List<Manufacturer> getManufacturersByAnyLowerPriceSouvenir(double price) {
		return getSouvenirs().stream()
				.filter(s -> s.price() <= price)
				.map(Souvenir::manufacturer)
				.distinct()
				.toList();
	}
	public List<Manufacturer> getManufacturersByAllLowerPriceSouvenir(double price) {
		return getManufacturers().stream()
				.filter(m -> getSouvenirsByManufacturer(m).stream().allMatch(s -> s.price() <= price))
				.toList();
	}

	public void saveData() {
		try {
			fileHandler.saveSouvenirs(souvenirs);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Souvenir> getSouvenirsByNameAndManufacturerAndYear(String name, int year) {
		return getSouvenirs().stream().filter(s ->
				s.name().equals(name) && s.releaseDate().getYear() == year).toList();
	}
}
