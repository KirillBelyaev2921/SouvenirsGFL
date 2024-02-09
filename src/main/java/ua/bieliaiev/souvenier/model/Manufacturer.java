package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Manufacturer implements Serializable {
	private String name;
	private String country;
	private final Map<String, String> requisites = new HashMap<>();

	public Manufacturer(String name, String country) {
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<String, String> getRequisites() {
		return requisites;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Manufacturer that)) return false;

		if (!getName().equals(that.getName())) return false;
		return getCountry().equals(that.getCountry());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, country);
	}
}
