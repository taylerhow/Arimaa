package piece;

import javax.swing.ImageIcon;

public class Rabbit extends AbstractPiece {
	public Rabbit(Owner owner) {
		super(null, owner, 0);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " rabbit.png").getImage());

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rabbit) {
			Rabbit e = (Rabbit) obj;
			return this.getOwner().equals(e.getOwner());
		}
		return false;
	}
}