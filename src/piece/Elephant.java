package piece;

import javax.swing.ImageIcon;

public class Elephant extends AbstractPiece {
	public Elephant(Owner owner) {
		super(null, owner, 5);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " elephant.png").getImage());

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Elephant) {
			Elephant e = (Elephant) obj;
			return this.getOwner().equals(e.getOwner());
		}
		return false;
	}
}