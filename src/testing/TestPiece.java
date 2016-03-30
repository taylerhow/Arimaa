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
import piece.Dog;
import piece.Elephant;
import piece.Horse;
import piece.Owner;
import piece.Piece;
import piece.Rabbit;

public class TestPiece {

	@Test
	public void testThatPieceInitializes() {
		AbstractPiece p = new Camel(Owner.Player1);
		assertNotNull(p);
	}

	@Test
	public void testThatTypeCanBeGotten() {
		AbstractPiece p = new Camel(Owner.Player1);
		assertTrue(p instanceof Camel);
	}

	@Test
	public void testThatImageCanBeGotten() {
		Image img = new ImageIcon("resources/White camel.png").getImage();
		AbstractPiece p = new Camel(Owner.Player1);
		assertEquals(img, p.getImage());
	}
	
	@Test
	public void testThatImageCanBeSet() {
		Image img = new BufferedImage(1, 1, 1);
		AbstractPiece p = new Camel(Owner.Player1);
		p.setImage(img);
		assertEquals(img, p.getImage());
	}

	@Test
	public void testThatOwnerCanBeGotten() {
		AbstractPiece p = new Camel(Owner.Player1);
		assertEquals(Owner.Player1, p.getOwner());
	}

	@Test
	public void testGetRank() {
		AbstractPiece p = new Elephant(Owner.Player1);
		assertEquals(5, p.getRank());
	}

	@Test
	public void testComparatorChecksOwners() {
		assertNotEquals(new Rabbit(Owner.Player1), new Rabbit(Owner.Player2));
		assertEquals(new Rabbit(Owner.Player2), new Rabbit(Owner.Player2));
	}

	@Test
	public void testIsElephantStrongerThanCamel() {
		AbstractPiece p1 = new Elephant(Owner.Player1);
		AbstractPiece p2 = new Camel(Owner.Player2);
		assertTrue(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsElephantStrongerThanElephant() {
		AbstractPiece p1 = new Elephant(Owner.Player1);
		AbstractPiece p2 = new Elephant(Owner.Player2);
		assertFalse(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsCamelStrongerThanCamel() {
		AbstractPiece p1 = new Camel(Owner.Player1);
		AbstractPiece p2 = new Camel(Owner.Player2);
		assertFalse(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsCamelStrongerThanHorse() {
		AbstractPiece p1 = new Camel(Owner.Player1);
		AbstractPiece p2 = new Horse(Owner.Player2);
		assertTrue(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsHorseStrongerThanDog() {
		AbstractPiece p1 = new Horse(Owner.Player1);
		AbstractPiece p2 = new Dog(Owner.Player2);
		assertTrue(p1.isStrongerThan(p2));
	}

	@Test
	public void testIsDogStrongerThanDog() {
		AbstractPiece p1 = new Dog(Owner.Player1);
		AbstractPiece p2 = new Dog(Owner.Player2);
		assertFalse(p1.isStrongerThan(p2));
	}

	@Test
	public void testEqualsReturnsFalseForOtherObject() {
		AbstractPiece p = new Elephant(Owner.Player1);
		ArrayList<Integer> notAPiece = new ArrayList<Integer>();
		assertFalse(p.equals(notAPiece));
	}
}
