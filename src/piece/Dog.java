package piece;

import javax.swing.ImageIcon;

public class Dog extends AbstractPiece {
	public Dog(Owner owner, Coordinate coordinate) {
		super(null, owner, 2, coordinate);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " dog.png").getImage());

	}
}