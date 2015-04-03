package game;
import java.awt.Image;

public class Piece {
	private PieceType type;
	private Image image;
	private Owner owner;
	
	public enum Owner{
		Player1, Player2
	}
	
	public enum PieceType{
		Elephant, Camel, Horse, Dog, Cat, Rabbit
	}
	public Piece(char c){
		switch(c){
		case 'E':
			this.type=PieceType.Elephant;
			break;
		case 'C':
			this.type=PieceType.Camel;
			break;
		case 'H':
			this.type=PieceType.Horse;
			break;
		case 'D':
			this.type=PieceType.Dog;
			break;
		case 'K':
			this.type=PieceType.Cat;
			break;
		case 'R':
			this.type=PieceType.Rabbit;
			break;
		default:
		}
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
		this.image = img;
	}

	public Owner getOwner() {
		return null;
	}

	public void setOwner(Owner owner) {
	}
	
	public boolean equals(Object p2){
		if((p2 instanceof Piece)){
			if(((Piece) p2).getType()==this.getType()){
				return true;
			}
		}
		return false;
		
	}
}
