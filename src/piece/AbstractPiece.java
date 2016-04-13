package piece;

import java.awt.Image;

public abstract class AbstractPiece {
	private Image image;
	private Owner owner;
	private int rank;
	private Coordinate coordinate;

	public AbstractPiece(Image image, Owner owner, int rank, Coordinate coordinate) {
		this.image = image;
		this.owner = owner;
		this.rank = rank;
		this.coordinate = coordinate;
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

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(Coordinate newCoordinate) {
		if (newCoordinate.isValid()) {
			this.coordinate = newCoordinate;
		}
	}

	public boolean equals(Object obj) {
		// this allows for subclassing of individual pieces
		// can i treat the given object as this type of object != this.class == obj.class
		if (obj.getClass().isAssignableFrom(this.getClass())) {
			AbstractPiece other = (AbstractPiece) obj;
			return this.getOwner().equals(other.getOwner()) && this.getCoordinate().equals(other.getCoordinate());
		}
		return false;
	}

	public int hashCode() {
		return this.getClass().hashCode() + this.image.hashCode() + this.owner.hashCode() + Integer.hashCode(this.rank) + this.coordinate.hashCode();
	}

	public boolean isStrongerThan(AbstractPiece p2) {
		return (this.getRank() > p2.getRank());
	}

}
