package com.cyrilBarillet.gazomatique.common.model;

/**
 * Lawn mower use to mow grass.
 * 
 * @author cyrilbarillet
 *
 */
public class LawnMowerEntity {

	/*
	 * Starting position of mower in the lawn.
	 */
	private PositionEntity startingPosition;
	
	/*
	 * Current position of mower in the lawn.
	 */
	private PositionEntity currentPosition;
	
	/*
	 * Order number of the lawn mower in lawn.
	 */
	private int index;
	
	/**
	 * Build a new instance LawnMowerEntity.
	 * 
	 * @param startingPosition starting position of the mower in the given lawn.
	 * @param index index of mower in the lawn
	 * @param lawn lawn which will be mown by this mower.
	 */
	public LawnMowerEntity(PositionEntity startingPosition, int index, LawnEntity lawn) {
		this(startingPosition.getCoordinates().getX(), 
				startingPosition.getCoordinates().getY(), startingPosition.getOrientation(), index, lawn);
	}
	
	/**
	 * Build a new instance LawnMowerEntity.
	 * 
	 * @param x x coordinate of starting position of the mower in the given lawn.
	 * @param y y coordinate of starting position of the mower in the given lawn.
	 * @param index index of mower in the lawn
	 * @param lawn lawn which will be mown by this mower.
	 */
	public LawnMowerEntity(int x, int y, OrientationEnum orientation, int index, LawnEntity lawn) {
		super();
		this.startingPosition = new PositionEntity(x, y, orientation);
		this.lawn = lawn;
		// At the beginning, current position is equal to starting position.
		this.currentPosition = new PositionEntity(startingPosition.getCoordinates().getX(), 
						startingPosition.getCoordinates().getY(), startingPosition.getOrientation());
	}
	
	/*
	 * Lawn in which is the mower.
	 */
	private LawnEntity lawn;

	/**
	 * @return the startingPosition
	 */
	public PositionEntity getStartingPosition() {
		return startingPosition;
	}

	/**
	 * @return the currentPosition
	 */
	public PositionEntity getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @return the lawn
	 */
	public LawnEntity getLawn() {
		return lawn;
	}
	
	/**
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * Change the orientation of the mower.
	 * 
	 * @param orientation new orientation of the mower.
	 */
	public void changeOrientation(OrientationEnum orientation)
	{
		this.getCurrentPosition().setOrientation(orientation);
	}
	
	/**
	 * Move the mower to the new coordinate.
	 * 
	 * @param x new x coordinate.
	 */
	public void changeXCoordinate(int x)
	{
		this.getCurrentPosition().getCoordinates().setX(x);
	}
	
	/**
	 * Move the mower to the new coordinate.
	 * 
	 * @param y new y coordinate.
	 */
	public void changeYCoordinate(int y)
	{
		this.getCurrentPosition().getCoordinates().setX(y);
	}
}
