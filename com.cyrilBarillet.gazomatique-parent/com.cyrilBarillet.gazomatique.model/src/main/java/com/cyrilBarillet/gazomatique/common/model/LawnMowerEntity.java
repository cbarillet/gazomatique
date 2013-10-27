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
	
	
}
