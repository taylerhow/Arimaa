package testing;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;

import game.Piece;
import game.Piece.Owner;
import game.Piece.PieceType;

import org.junit.Test;

public class TestPiece {

	@Test
	public void testThatPieceInitializes() {
		Piece p = new Piece(PieceType.Camel, null, Owner.Player1);
		assertNotNull(p);
	}
	
	@Test
	public void testThatPieceInitializesWithValues() {
		Image img = new BufferedImage(1, 1, 1);
		Piece p = new Piece(PieceType.Camel, img, Owner.Player1);
		assertNotNull(p);
		assertEquals(PieceType.Camel, p.getType());
		assertEquals(img, p.getImage());
	}
	
	@Test
	public void testThatTypeCanBeGotten() {
		Image img = new BufferedImage(1,1,1);
		Piece p = new Piece(PieceType.Camel, img, Owner.Player1);
		assertEquals(PieceType.Camel, p.getType());
	}
	
	@Test
	public void testThatImageCanBeGotten() {
		Image img = new BufferedImage(1,1,1);
		Piece p = new Piece(PieceType.Camel, img,Owner.Player1);
		assertEquals(img, p.getImage());
	}
	
	@Test
	public void testThatTypeCanBeSet(){
		Image img = new BufferedImage(1,1,1);
		Piece p = new Piece(PieceType.Camel, img, Owner.Player1);
		p.setType(PieceType.Elephant);
		assertEquals(PieceType.Elephant, p.getType());
	}
	
	@Test
	public void testThatImageCanBeSet(){
		Image img = new BufferedImage(1,1,1);
		Image img2 = new BufferedImage(50,50,2);
		Piece p = new Piece(PieceType.Camel, img, Owner.Player1);
		p.setImg(img2);
		assertEquals(img2, p.getImage());
	}
	
	@Test
	public void testThatOwnerCanBeSetAndGotten(){
		Image img = new BufferedImage(1,1,1);
		Piece p = new Piece(PieceType.Camel, img, Owner.Player1);
		p.setOwner(Owner.Player2);
		assertEquals(Owner.Player2, p.getOwner());
	}
	
	@Test
	public void testComparatorChecksOwners(){
		assertNotEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1), new Piece(PieceType.Rabbit, null, Piece.Owner.Player2));
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player2), new Piece(PieceType.Rabbit, null, Piece.Owner.Player2));
	}
	
	@Test
	public void testIsElephantStrongerThanCamel(){
		Piece p1 = new Piece(PieceType.Elephant, null, Owner.Player1);
		Piece p2 = new Piece(PieceType.Camel, null, Owner.Player2);
		assertTrue(p1.isStrongerThan(p2));
	}
	
	@Test
	public void testIsElephantStrongerThanElephant(){
		Piece p1 = new Piece(PieceType.Elephant, null, Owner.Player1);
		Piece p2 = new Piece(PieceType.Elephant, null, Owner.Player2);
		assertFalse(p1.isStrongerThan(p2));
	}
	
	@Test
	public void testIsCamelStrongerThanCamel(){
		Piece p1 = new Piece(PieceType.Camel, null, Owner.Player1);
		Piece p2 = new Piece(PieceType.Camel, null, Owner.Player2);
		assertFalse(p1.isStrongerThan(p2));
	}
	
	@Test
	public void testIsCamelStrongerThanHorse(){
		Piece p1 = new Piece(PieceType.Camel, null, Owner.Player1);
		Piece p2 = new Piece(PieceType.Horse, null, Owner.Player2);
		assertTrue(p1.isStrongerThan(p2));
	}
	
	@Test
	public void testIsHorseStrongerThanDog(){
		Piece p1 = new Piece(PieceType.Horse, null, Owner.Player1);
		Piece p2 = new Piece(PieceType.Dog, null, Owner.Player2);
		assertTrue(p1.isStrongerThan(p2));
	}
}
