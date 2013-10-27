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
	 * @param lawn lawn which will be mown by this mower.
	 */
	public LawnMowerEntity(PositionEntity startingPosition, LawnEntity lawn) {
		super();
		this.startingPosition = startingPosition;
		this.lawn = lawn;
	}
	
	/**
	 * Build a new instance LawnMowerEntity.
	 * 
	 * @param startingPosition starting position of the mower in the given lawn.
	 * @param lawn lawn which will be mown by this mower.
	 */
	public LawnMowerEntity(int x, int y, OrientationEnum orientation, int index, LawnEntity lawn) {
		super();
		this.startingPosition = new PositionEntity(x, y, orientation);
		this.lawn = lawn;
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
	
	public void changeOrientation(OrientationEnum orientation)
	{
		this.getCurrentPosition().setOrientation(orientation);
	}
	
	public void changeXCoordinate(int x)
	{
		this.getCurrentPosition().getCoordinates().setX(x);
	}
	
	public void changeYCoordinate(int y)
	{
		this.getCurrentPosition().getCoordinates().setX(y);
	}
}
