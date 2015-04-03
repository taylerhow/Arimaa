package testing;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Piece;
import game.Piece.PieceType;

import org.junit.Test;

public class TestPiece {

	@Test
	public void testThatPieceInitializes() {
		Piece p = new Piece(PieceType.Camel, null);
		assertNotNull(p);
	}
	
//	@Test
//	public void testThatPieceInitializesWithValues() {
//		Image img = null;
//		try {
//			//Don't have piece images yet...using board as placeholder
//			img = ImageIO.read(new File("resources/board.jpg"));
//		} catch (IOException e) { }
//		Piece p = new Piece(PieceType.Camel, img);
//		assertNotNull(p);
//		assertEquals(PieceType.Camel, p.getType());
//		assertEquals(img, p.getImg());
//	}
	
	@Test
	public void testThatTypeCanBeGotten() {
		Image img = null;
		try {
			img = ImageIO.read(new File("resources/White rabbit.jpg"));
		} catch (IOException e) { }
		Piece p = new Piece(PieceType.Camel, img);
		assertEquals(p.getType(), PieceType.Camel);
	}
	
	@Test
	public void testThatImageCanBeGotten() {
		Image img = null;
		try {
			img = ImageIO.read(new File("resources/White rabbit.jpg"));
		} catch (IOException e) { }
		Piece p = new Piece(PieceType.Camel, img);
		assertEquals(p.getImg(), img);
	}
}
