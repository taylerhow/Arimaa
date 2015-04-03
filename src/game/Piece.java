package game;
import java.awt.Image;

public class Piece {
	private PieceType type;
	private Image img;
	
	public enum PieceType{
		Elephant, Camel, Horse, Dog, Cat, Rabbit
	}
	
	public Piece(PieceType t, Image i){
		this.type = t;
		this.img = i;
	}

	public PieceType getType() {
		return null;
	}

	public void setType(PieceType type) {
		
	}

	public Image getImg() {
		return null;
	}

	public void setImg(Image img) {
		
	}
}
