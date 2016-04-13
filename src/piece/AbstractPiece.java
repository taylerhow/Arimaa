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

	abstract public boolean equals(Object obj);

	public boolean isStrongerThan(AbstractPiece p2) {
		return (this.getRank() > p2.getRank());
	}

}
