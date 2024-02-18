package ua.bieliaiev.souvenier.util;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class NumberParser {

	public static OptionalDouble parseDouble(String doubleString) {
		double parsedDouble;
		try {
			parsedDouble = Double.parseDouble(doubleString);
		} catch (NumberFormatException e) {
			return OptionalDouble.empty();
		}
		return parsedDouble >= 0 ? OptionalDouble.of(parsedDouble) : OptionalDouble.empty();
	}

	public static OptionalInt parseInt(String intString) {
		int parsedInt;
		try {
			parsedInt = Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			return OptionalInt.empty();
		}
		return parsedInt >= 0 ? OptionalInt.of(parsedInt) : OptionalInt.empty();
	}
}
