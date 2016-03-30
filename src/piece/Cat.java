package piece;

import javax.swing.ImageIcon;

public class Cat extends AbstractPiece {
	public Cat(Owner owner) {
		super(null, owner, 1);
		String color = owner.equals(Owner.Player1) ? "White" : "Black";
		this.setImage(new ImageIcon("resources/" + color + " cat.png").getImage());

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cat) {
			Cat e = (Cat) obj;
			return this.getOwner().equals(e.getOwner());
		}
		return false;
	}
}