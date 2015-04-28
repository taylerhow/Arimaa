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
			this.owner=Owner.Player1;
			break;
		case 'C':
			this.type=PieceType.Camel;
			this.owner=Owner.Player1;
			break;
		case 'H':
			this.type=PieceType.Horse;
			this.owner=Owner.Player1;
			break;
		case 'D':
			this.type=PieceType.Dog;
			this.owner=Owner.Player1;
			break;
		case 'K':
			this.type=PieceType.Cat;
			this.owner=Owner.Player1;
			break;
		case 'R':
			this.type=PieceType.Rabbit;
			this.owner=Owner.Player1;
			break;
		case 'e':
			this.type=PieceType.Elephant;
			this.owner=Owner.Player2;
			break;
		case 'c':
			this.type=PieceType.Camel;
			this.owner=Owner.Player2;
			break;
		case 'h':
			this.type=PieceType.Horse;
			this.owner=Owner.Player2;
			break;
		case 'd':
			this.type=PieceType.Dog;
			this.owner=Owner.Player2;
			break;
		case 'k':
			this.type=PieceType.Cat;
			this.owner=Owner.Player2;
			break;
		case 'r':
			this.type=PieceType.Rabbit;
			this.owner=Owner.Player2;
			break;
		default:
		}
	}
	public Piece(PieceType t, Image i, Owner o){
		this.type = t;
		this.image = i;
		this.owner = o;
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
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public boolean equals(Object p2){
		if((p2 instanceof Piece)){
			if(((Piece) p2).getType()==this.getType() && (((Piece) p2).getOwner()==this.getOwner())){
				return true;
			}
		}
		return false;
		
	}
	
	public boolean isStrongerThan(Piece p2){
		if(p2.getType()==PieceType.Elephant) return false;
		if(this.getType()==PieceType.Elephant) return true;
		if(p2.getType()==PieceType.Horse || p2.getType()==PieceType.Dog) return true;
		return false;
	}
}
