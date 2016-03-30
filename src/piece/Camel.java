package piece;

import javax.swing.ImageIcon;

public class Camel extends AbstractPiece {
	public Camel(Owner owner) {
		super(null, owner, 4);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " camel.png").getImage());

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Camel) {
			Camel e = (Camel) obj;
			return this.getOwner().equals(e.getOwner());
		}
		return false;
	}
}