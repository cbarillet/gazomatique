package com.cyrilBarillet.gazomatique.common.model;

/**
 * PositionEntity allows to describes location and orientation.
 * 
 * @author cyrilbarillet
 *
 */
public class PositionEntity {

	/*
	 * Location of this entity.
	 */
	private CoordinatesEntity coordinates;
	
	/*
	 * Orientation of this entity.
	 */
	private OrientationEnum orientation;
	
	/**
	 * Build a new instance of PositionEntity.
	 * 
	 * @param x x coordinate of the position
	 * @param y y coordinate of the position
	 * @param orientation Orientation of the position
	 */
	public PositionEntity(int x, int y,
			OrientationEnum orientation) {
		super();
		this.coordinates = new CoordinatesEntity(x, y);
		this.orientation = orientation;
	}

	/**
	 * @return the coordinates
	 */
	public CoordinatesEntity getCoordinates() {
		return coordinates;
	}

	/**
	 * @return the orientation
	 */
	public OrientationEnum getOrientation() {
		return orientation;
	}
}
