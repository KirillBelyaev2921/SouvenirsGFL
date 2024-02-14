package ua.bieliaiev.souvenier.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerTest {

	@Test
	void testWhenExactSame() {
		Manufacturer m1 = new Manufacturer("Oracle", "US", "", "");
		Manufacturer m2 = new Manufacturer("Oracle", "US", "", "");

		assertTrue(m1.exactSame(m2));
	}
	@Test
	void testWhenNotEquals() {
		Manufacturer m1 = new Manufacturer("Oracle", "US", "", "");
		Manufacturer m2 = new Manufacturer("Google", "US", "", "");

		assertFalse(m1.exactSame(m2));
	}
	@Test
	void testWhenNotExactSame() {
		Manufacturer m1 = new Manufacturer("Oracle", "US", "", "");
		Manufacturer m2 = new Manufacturer("Oracle", "US", "", "123");

		assertFalse(m1.exactSame(m2));
	}
}