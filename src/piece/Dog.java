package piece;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Dog extends AbstractPiece {
	public Dog(Owner owner) {
		super(null, owner, 2);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " dog.png").getImage());

	}

	public Dog(Image image, Owner owner, int rank) {
		super(image, owner, rank);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dog) {
			Dog e = (Dog) obj;
			return this.getOwner().equals(e.getOwner());
		}
		return false;
	}
}