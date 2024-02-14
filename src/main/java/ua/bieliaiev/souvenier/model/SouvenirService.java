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

	public boolean addSouvenir(Souvenir souvenir) {
		return souvenirs.addSouvenir(souvenir);
	}

	public boolean addManufacturer(Manufacturer manufacturer) {
		return souvenirs.addManufacturer(manufacturer);
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

	public void saveData() {
		try {
			fileHandler.saveSouvenirs(souvenirs);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
