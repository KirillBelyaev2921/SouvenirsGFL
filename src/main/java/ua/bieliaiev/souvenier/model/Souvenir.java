package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class for storing data of manufacturer.
 * This class is immutable to provide thread safety for data and
 * not allowing to change data outside SouvenirStorage class.
 *
 * @param name Name of souvenir
 * @param manufacturer Manufacturer company of souvenir
 * @param releaseDate Release date of souvenir.
 * @param price Price of the souvenir.
 */
public record Souvenir(String name, Manufacturer manufacturer, LocalDate releaseDate,
					   double price) implements Serializable {

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Souvenir souvenir)) return false;

		if (!name().equals(souvenir.name())) return false;
		return manufacturer().equals(souvenir.manufacturer());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, manufacturer);
	}

	@Override
	public String toString() {
		return  name +
				", (" + manufacturer +
				"), " + dateString() +
				", " + price +
				'$';
	}

	public String dateString() {
		return releaseDate.format(DateTimeFormatter.ofPattern("yyyy'.'MM"));
	}

	public boolean exactSame(Souvenir s) {
		return equals(s)
				&& releaseDate.equals(s.releaseDate) && (price == s.price);
	}
}
