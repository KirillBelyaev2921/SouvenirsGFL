package ua.bieliaiev.souvenier.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
				"), " + releaseDate.format(DateTimeFormatter.ofPattern("yyyy' of 'MMMM")) +
				", " + price +
				'$';
	}
}
