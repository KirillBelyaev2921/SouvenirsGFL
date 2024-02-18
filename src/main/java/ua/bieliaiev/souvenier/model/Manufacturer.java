package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class for storing data of manufacturer.
 * This class is immutable to provide thread safety for data and
 * not allowing to change data outside SouvenirStorage class.
 *
 * @param name Name of the manufacturers company
 * @param country Country of the manufacturers company
 * @param email Email of the manufacturers company
 * @param phone Phone of the manufacturers company
 */
public record Manufacturer(String name, String country, String email, String phone) implements Serializable, Comparable<Manufacturer> {

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
		return name + ", " + country +
				(email.isBlank() ? "" : ", " + email) +
				(phone.isBlank() ? "" : ", " + phone);
	}

	public boolean exactSame(Manufacturer m) {
		return equals(m) && email.equals(m.email) && phone.equals(m.phone);
	}

	@Override
	public int compareTo(Manufacturer o) {
		return Comparator.comparing(Manufacturer::name)
				.thenComparing(Manufacturer::country).compare(this, o);
	}
}
