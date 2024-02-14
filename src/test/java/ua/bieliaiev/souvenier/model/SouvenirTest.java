package ua.bieliaiev.souvenier.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SouvenirTest {

	@Test
	void testWhenExactSame() {
		Manufacturer m = new Manufacturer("Oracle", "US", "", "");
		Souvenir s1 = new Souvenir("cup", m, LocalDate.of(2001, 1, 1), 12);
		Souvenir s2 = new Souvenir("cup", m, LocalDate.of(2001, 1, 1), 12);

		assertTrue(s1.exactSame(s2));
	}
	@Test
	void testWhenNotEquals() {
		Manufacturer m = new Manufacturer("Oracle", "US", "", "");
		Souvenir s1 = new Souvenir("cup", m, LocalDate.of(2001, 1, 1), 12);
		Souvenir s2 = new Souvenir("mouse", m, LocalDate.of(2001, 1, 1), 12);

		assertFalse(s1.exactSame(s2));
	}
	@Test
	void testWhenNotExactSame() {
		Manufacturer m = new Manufacturer("Oracle", "US", "", "");
		Souvenir s1 = new Souvenir("cup", m, LocalDate.of(2001, 1, 1), 12);
		Souvenir s2 = new Souvenir("cup", m, LocalDate.of(2001, 1, 1), 116);

		assertFalse(s1.exactSame(s2));
	}
}