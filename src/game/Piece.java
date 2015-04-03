package game;
import java.awt.Image;

public class Piece {
	private PieceType type;
	private Image image;
	
	public enum PieceType{
		Elephant, Camel, Horse, Dog, Cat, Rabbit
	}
	
	public Piece(PieceType t, Image i){
		this.type = t;
		this.image = i;
	}

	public PieceType getType() {
		return this.type;
	}

	public void setType(PieceType type) {
		this.type = type; 
	}

	public Image getImage() {
		return this.image;
	}

	public void setImg(Image img) {
		
	}
}
