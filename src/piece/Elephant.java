package piece;

import javax.swing.ImageIcon;

public class Elephant extends AbstractPiece {
	public Elephant(Owner owner, Coordinate coordinate) {
		super(null, owner, 5, coordinate);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " elephant.png").getImage());

	}
}