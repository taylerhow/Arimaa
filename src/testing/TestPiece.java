package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.Test;

import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
import piece.Coordinate;
import piece.Dog;
import piece.Elephant;
import piece.Horse;
import piece.Owner;
import piece.Rabbit;

public class TestPiece {
	@Test
	public void testThatPieceInitializes() {
		AbstractPiece p = new Camel(Owner.Player1, new Coordinate(0, 0));
		assertNotNull(p);
	}

	@Test
	public void testThatTypeCanBeGotten() {
		AbstractPiece p = new Camel(Owner.Player1, new Coordinate(0, 0));
		assertTrue(p instanceof Camel);
	}

	@Test
	public void testThatImageCanBeGotten() {
		Image img = new ImageIcon("resources/White camel.png").getImage();
		AbstractPiece p = new Camel(Owner.Player1, new Coordinate(0, 0));
		assertEquals(img, p.getImage());
	}
	
	@Test
	public void testThatImageCanBeSet() {
		Image img = new BufferedImage(1, 1, 1);
		AbstractPiece p = new Camel(Owner.Player1, new Coordinate(0, 0));
		p.setImage(img);
		assertEquals(img, p.getImage());
	}

	@Test
	public void testThatOwnerCanBeGotten() {
		AbstractPiece p = new Camel(Owner.Player1, new Coordinate(0, 0));
		assertEquals(Owner.Player1, p.getOwner());
	}

	@Test
	public void testGetRank() {
		AbstractPiece p = new Elephant(Owner.Player1, new Coordinate(0, 0));
		assertEquals(5, p.getRank());
	}
	
	@Test
	public void testGetCoordinate() {
		Coordinate coor = new Coordinate(0, 0);
		AbstractPiece p = new Elephant(Owner.Player1, coor);	
		assertEquals(coor, p.getCoordinate());
	}
	
	@Test
	public void canSetValidCoordinate() {
		AbstractPiece p = new Elephant(Owner.Player1, new Coordinate(0, 0));
		Coordinate newCoor = new Coordinate(1, 2);
		p.setCoordinate(newCoor);
		assertEquals(newCoor, p.getCoordinate());
	}
	
	@Test
	public void canNotSetInValidCoordinate() {
		Coordinate coor = new Coordinate(0, 0);
		AbstractPiece p = new Elephant(Owner.Player1, coor);	
		Coordinate newCoor = new Coordinate(-1, -2);
		p.setCoordinate(newCoor);
		assertEquals(coor, p.getCoordinate());
	}

	@Test
	public void testComparatorChecksOwners() {
		assertNotEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)), new Rabbit(Owner.Player2, new Coordinate(0, 0)));
		assertEquals(new Rabbit(Owner.Player2, new Coordinate(0, 0)), new Rabbit(Owner.Player2, new Coordinate(0, 0)));

		assertNotEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Rabbit(Owner.Player2, new Coordinate(0, 0)).hashCode());
		assertEquals(new Rabbit(Owner.Player2, new Coordinate(0, 0)).hashCode(), new Rabbit(Owner.Player2, new Coordinate(0, 0)).hashCode());
}

	@Test
	public void testComparatorChecksCoordinates() {
		assertNotEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)), new Rabbit(Owner.Player1, new Coordinate(1, 0)));
		assertEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)), new Rabbit(Owner.Player1, new Coordinate(0, 0)));

		assertNotEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Rabbit(Owner.Player1, new Coordinate(1, 0)).hashCode());
		assertEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode());
}
	
	@Test
	public void testComparatorChecksType() {
		assertNotEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)), new Camel(Owner.Player1, new Coordinate(0, 0)));
		assertEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)), new Rabbit(Owner.Player1, new Coordinate(0, 0)));

		assertNotEquals(new Cat(Owner.Player1, new Coordinate(0, 0)), new Camel(Owner.Player1, new Coordinate(0, 0)));
		assertEquals(new Cat(Owner.Player1, new Coordinate(0, 0)), new Cat(Owner.Player1, new Coordinate(0, 0)));

		assertNotEquals(new Dog(Owner.Player1, new Coordinate(0, 0)), new Camel(Owner.Player1, new Coordinate(0, 0)));
		assertEquals(new Dog(Owner.Player1, new Coordinate(0, 0)), new Dog(Owner.Player1, new Coordinate(0, 0)));

		assertNotEquals(new Elephant(Owner.Player1, new Coordinate(0, 0)), new Camel(Owner.Player1, new Coordinate(0, 0)));
		assertEquals(new Elephant(Owner.Player1, new Coordinate(0, 0)), new Elephant(Owner.Player1, new Coordinate(0, 0)));

		assertNotEquals(new Horse(Owner.Player1, new Coordinate(0, 0)), new Camel(Owner.Player1, new Coordinate(0, 0)));
		assertEquals(new Horse(Owner.Player1, new Coordinate(0, 0)), new Horse(Owner.Player1, new Coordinate(0, 0)));

		assertNotEquals(new Camel(Owner.Player1, new Coordinate(0, 0)), new Cat(Owner.Player1, new Coordinate(0, 0)));
		assertEquals(new Camel(Owner.Player1, new Coordinate(0, 0)), new Camel(Owner.Player1, new Coordinate(0, 0)));

		// hashcode testing
		assertNotEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode());
		assertEquals(new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Rabbit(Owner.Player1, new Coordinate(0, 0)).hashCode());

		assertNotEquals(new Cat(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode());
		assertEquals(new Cat(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Cat(Owner.Player1, new Coordinate(0, 0)).hashCode());

		assertNotEquals(new Dog(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode());
		assertEquals(new Dog(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Dog(Owner.Player1, new Coordinate(0, 0)).hashCode());

		assertNotEquals(new Elephant(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode());
		assertEquals(new Elephant(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Elephant(Owner.Player1, new Coordinate(0, 0)).hashCode());

		assertNotEquals(new Horse(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode());
		assertEquals(new Horse(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Horse(Owner.Player1, new Coordinate(0, 0)).hashCode());

		assertNotEquals(new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Cat(Owner.Player1, new Coordinate(0, 0)).hashCode());
		assertEquals(new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode(), new Camel(Owner.Player1, new Coordinate(0, 0)).hashCode());
	}



	@Test
	public void testIsElephantStrongerThanCamel() {
		AbstractPiece p1 = new Elephant(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Camel(Owner.Player2, new Coordinate(0, 0));
		assertTrue(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsElephantStrongerThanElephant() {
		AbstractPiece p1 = new Elephant(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Elephant(Owner.Player2, new Coordinate(0, 0));
		assertFalse(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsCamelStrongerThanCamel() {
		AbstractPiece p1 = new Camel(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Camel(Owner.Player2, new Coordinate(0, 0));
		assertFalse(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsCamelStrongerThanHorse() {
		AbstractPiece p1 = new Camel(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Horse(Owner.Player2, new Coordinate(0, 0));
		assertTrue(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsHorseStrongerThanDog() {
		AbstractPiece p1 = new Horse(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Dog(Owner.Player2, new Coordinate(0, 0));
		assertTrue(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsDogStrongerThanDog() {
		AbstractPiece p1 = new Dog(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Dog(Owner.Player2, new Coordinate(0, 0));
		assertFalse(p1.isStrongerThan(p2));
	}

	@Test
	public void testEqualsReturnsFalseForOtherObject() {
		AbstractPiece p = new Elephant(Owner.Player1, new Coordinate(0, 0));
		ArrayList<Integer> notAPiece = new ArrayList<Integer>();
		assertFalse(p.equals(notAPiece));
	}
	
	@Test
	public void testClone() {
		AbstractPiece p1 = new Camel(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece clone = p1.clone();
		clone.setCoordinate(new Coordinate(1, 1));
		assertEquals(p1.getImage(), clone.getImage());
		assertEquals(p1.getOwner(), clone.getOwner());
		assertEquals(p1.getRank(), clone.getRank());
		assertNotEquals(p1.getCoordinate(), clone.getCoordinate());
		assertEquals(Camel.class, clone.getClass());
	}
}
