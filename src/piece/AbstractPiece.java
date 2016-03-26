package piece;

import java.awt.Image;

public abstract class AbstractPiece {
	private Image image;
	private Owner owner;
	private int rank;

	public AbstractPiece(Image image, Owner owner, int rank) {
		this.image = image;
		this.owner = owner;
		this.rank = rank;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	abstract public boolean equals(Object obj);
	// public boolean equals(Object p2) {
	// if ((p2 instanceof Piece)) {
	// if (((Piece) p2).getType() == this.getType()
	// && (((Piece) p2).getOwner() == this.getOwner())) {
	// return true;
	// }
	// }
	// return false;
	//
	// }

	public boolean isStrongerThan(Piece p2) {
		return (this.getRank() > p2.getRank());
	}

}
