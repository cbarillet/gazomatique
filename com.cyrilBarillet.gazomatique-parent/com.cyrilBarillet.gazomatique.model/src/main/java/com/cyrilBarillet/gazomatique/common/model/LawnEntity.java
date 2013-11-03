/**
 * 
 */
package com.cyrilBarillet.gazomatique.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangular lawn to mow.
 * 
 * @author cyrilbarillet
 *
 */
public class LawnEntity {
	
	/*
	 * List of mowers in the lawn.
	 */
	private List<LawnMowerEntity> lawnMowers;
	
	/*
	 * Bottom left corner location is (0,0).
	 * System needs to know the location of top right corner of the lawn.
	 */
	private CoordinatesEntity topRightCorner;
	
	/**
	 * Build a new instance of LawnEntity.
	 */
	public LawnEntity(int x, int y) {
		super();
		setLawnMowers(new ArrayList<LawnMowerEntity>());
		setTopRightCorner(new CoordinatesEntity(x, y));
	}

	/**
	 * @return the lawnMowers
	 */
	public List<LawnMowerEntity> getLawnMowers() {
		return lawnMowers;
	}
	
	/**
	 * @return set the lawnMowers
	 */
	protected void setLawnMowers(final List<LawnMowerEntity> lawnMowers)
	{
		this.lawnMowers = lawnMowers;
	}
	
	/**
	 * Add the given mower in the lawn.
	 * 
	 * @param lawnMower mower to add in the lawn.
	 * @return if add action was succeed then return true else return false; 
	 */
	public boolean addLownMower(final LawnMowerEntity lawnMower)
	{
		if(lawnMower != null)
		{
			return this.getLawnMowers().add(lawnMower);
		}
		else
		{
			return false;
		}
	}

	/**
	 * @return the topRightCorner
	 */
	public CoordinatesEntity getTopRightCorner() {
		return topRightCorner;
	}

	/**
	 * @param topRightCorner the topRightCorner to set
	 */
	protected void setTopRightCorner(CoordinatesEntity topRightCorner) {
		this.topRightCorner = topRightCorner;
	}
}
