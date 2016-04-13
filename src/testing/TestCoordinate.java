package testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import piece.Coordinate;

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
	}
	
	@Test
	public void testNullEquality() {
		assertFalse(new Coordinate(0, 0).equals(null));
	}
	
	@Test
	public void testReflexiveEquality() {
		Coordinate c = new Coordinate(0, 0);
		assertTrue(c.equals(c));		
	}
	
	@Test
	public void testSymetricEquality() {
		Coordinate a = new Coordinate(0, 0);
		Coordinate b = new Coordinate(0, 0);
		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
		
	}


}
