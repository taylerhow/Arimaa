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
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
