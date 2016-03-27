package piece;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Horse extends AbstractPiece {
	public Horse(Owner owner) {
		super(null, owner, 3);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " horse.png").getImage());

	}

	public Horse(Image image, Owner owner, int rank) {
		super(image, owner, rank);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Horse) {
			Horse e = (Horse) obj;
			return this.getOwner().equals(e.getOwner());
		}
		return false;
	}
}