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

	public int getRank() {
		return rank;
	}

	public boolean equals(Object obj) {
		// this allows for subclassing of individual pieces
		// can i treat the given object as this type of object != this.class == obj.class
		if (obj.getClass().isAssignableFrom(this.getClass())) {
			AbstractPiece other = (AbstractPiece) obj;
			return this.getOwner().equals(other.getOwner());
		}
		return false;
	}

	public int hashCode() {
		return this.getClass().hashCode() + this.image.hashCode() + this.owner.hashCode() + Integer.hashCode(this.rank);
	}

	public boolean isStrongerThan(AbstractPiece p2) {
		return (this.getRank() > p2.getRank());
	}
}
