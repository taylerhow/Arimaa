package piece;

import javax.swing.ImageIcon;

public class Camel extends AbstractPiece {
	public Camel(Owner owner) {
		super(null, owner, 4);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " camel.png").getImage());

	}
}