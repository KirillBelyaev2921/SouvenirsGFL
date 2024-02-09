package ua.bieliaiev.souvenier.model;

import java.io.Serializable;

public class Manufacturer implements Serializable {
	private String name;
	private String country;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Manufacturer that = (Manufacturer) o;

		if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
		return getCountry() != null ? getCountry().equals(that.getCountry()) : that.getCountry() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
		return result;
	}
}
