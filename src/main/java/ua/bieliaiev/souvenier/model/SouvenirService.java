package ua.bieliaiev.souvenier.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SouvenirService {
	private List<Souvenir> souvenirs = new ArrayList<>();

	public void addSouvenir(Souvenir souvenir) {
		souvenirs.add(souvenir);
	}

	public List<Souvenir> getSouvenirs() {
		return Collections.unmodifiableList(souvenirs);
	}
}
