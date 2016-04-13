package piece;

import javax.swing.ImageIcon;

public class Cat extends AbstractPiece {
	public Cat(Owner owner, Coordinate coordinate) {
		super(null, owner, 1, coordinate);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " cat.png").getImage());

	}
}