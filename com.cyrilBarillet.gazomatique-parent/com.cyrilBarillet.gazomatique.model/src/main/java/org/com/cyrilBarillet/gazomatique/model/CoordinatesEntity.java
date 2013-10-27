package org.com.cyrilBarillet.gazomatique.model;

/**
 * Coordinates.
 * 
 * @author cyrilbarillet
 *
 */
public class CoordinatesEntity {
	
	/*
	 * X coordinate of location.
	 */
	public int x;
	
	/*
	 * Y coordinate of location.
	 */
	public int y;

	/**
	 * Build a new instance of CoordinatesEntity.
	 * 
	 * @param x x coordinate of the location.
	 * @param y y coordinate of the location.
	 */
	public CoordinatesEntity(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param x the x to set
	 */
	protected void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	protected void setY(int y) {
		this.y = y;
	}
	
}
