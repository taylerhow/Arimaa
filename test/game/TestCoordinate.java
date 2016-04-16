package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCoordinate {
	@Test
	public void testCoordinateInitializes() {
		Coordinate c = new Coordinate(0, 0);
		assertNotNull(c);
	}

	@Test
	public void testValidCoordinateIsValid() {
		Coordinate c = new Coordinate(0, 0);
		assertTrue(c.isValid());

		c = new Coordinate(5, 3);
		assertTrue(c.isValid());
		
		c = new Coordinate(7, 7);
		assertTrue(c.isValid());
	}
	
	@Test
	public void testCopyConstructor() {
		Coordinate c = new Coordinate(0, 0);
		Coordinate copy = new Coordinate(c);
		assertFalse(c == copy);
		assertEquals(c, copy);
	}

	@Test
	public void testCanGetX() {
		Coordinate c = new Coordinate(1, 2);
		assertEquals(1, c.getX());
	}
	
	@Test
	public void testCanGetY() {
		Coordinate c = new Coordinate(1, 2);
		assertEquals(2, c.getY());
	}
	
	@Test
	public void testInvalidCoordinateIsInvalid() {
		Coordinate c = new Coordinate(-1, 0);
		assertFalse(c.isValid());

		c = new Coordinate(0, -1);
		assertFalse(c.isValid());

		c = new Coordinate(-1, -1);
		assertFalse(c.isValid());

		c = new Coordinate(8, 0);
		assertFalse(c.isValid());

		c = new Coordinate(0, 8);
		assertFalse(c.isValid());

		c = new Coordinate(8, 8);
		assertFalse(c.isValid());
		
		c = new Coordinate(-1, 8);
		assertFalse(c.isValid());
	}

	@Test
	public void testNullEquality() {
		assertNotEquals(new Coordinate(0, 0), null);
	}

	@Test
	public void testReflexiveEquality() {
		Coordinate c = new Coordinate(0, 0);
		assertEquals(c, c);
	}

	@Test
	public void testSymetricEquality() {
		Coordinate a = new Coordinate(0, 0);
		Coordinate b = new Coordinate(0, 0);
		assertEquals(a, b);
		assertEquals(b, a);
	}

	@Test
	public void testTransitiveEquality() {
		Coordinate a = new Coordinate(0, 0);
		Coordinate b = new Coordinate(0, 0);
		Coordinate c = new Coordinate(0, 0);
		assertEquals(a, b);
		assertEquals(b, c);
		assertEquals(a, c);
	}

	@Test
	public void testInvalidIsNeverEqual() {
		Coordinate valid = new Coordinate(0, 0);
		Coordinate invalid1 = new Coordinate(-1, -1);
		Coordinate invalid2 = new Coordinate(8, 8);
		
		assertNotEquals(valid, invalid1);
		assertNotEquals(valid, invalid2);
		assertNotEquals(invalid1, valid);
		assertNotEquals(invalid2, valid);
		
		assertNotEquals(invalid1, invalid1);
		assertNotEquals(invalid2, invalid2);
	}
	
	@Test
	public void testPositiveEquality() {
		Coordinate a = new Coordinate(0, 0);
		Coordinate b = new Coordinate(0, 0);
		assertEquals(a, b);
		assertEquals(b, a);
		
		a = new Coordinate(1, 3);
		b = new Coordinate(1, 3);
		assertEquals(a, b);
		assertEquals(b, a);
	}

}
