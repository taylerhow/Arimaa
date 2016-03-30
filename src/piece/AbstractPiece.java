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

	abstract public boolean equals(Object obj);

	public boolean isStrongerThan(AbstractPiece p2) {
		return (this.getRank() > p2.getRank());
	}

}
