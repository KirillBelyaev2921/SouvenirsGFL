package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.util.Objects;

public record Manufacturer(String name, String country, String email, String phone) implements Serializable {

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Manufacturer that)) return false;

		if (!name().equals(that.name())) return false;
		return country().equals(that.country());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, country);
	}

	@Override
	public String toString() {
		return name + ", " + country;
	}

	public boolean exactSame(Manufacturer m) {
		return equals(m) && email.equals(m.email) && phone.equals(m.phone);
	}
}
