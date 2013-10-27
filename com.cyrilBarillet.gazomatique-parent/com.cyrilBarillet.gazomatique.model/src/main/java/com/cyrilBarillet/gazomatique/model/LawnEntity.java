/**
 * 
 */
package com.cyrilBarillet.gazomatique.model;

import java.util.LinkedList;
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
	
	/**
	 * Build a new instance of LawnEntity.
	 */
	public LawnEntity() {
		super();
		lawnMowers = new LinkedList<>();
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
}
