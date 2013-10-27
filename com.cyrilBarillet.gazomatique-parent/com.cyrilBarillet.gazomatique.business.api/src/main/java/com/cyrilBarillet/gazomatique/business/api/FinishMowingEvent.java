/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.api;

import java.util.EventObject;

import com.cyrilBarillet.gazomatique.model.LawnMowerEntity;

/**
 * @author cyrilbarillet
 *
 */
public class FinishMowingEvent extends EventObject {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -3754458445364498697L;

	public FinishMowingEvent(LawnMowerEntity source) {
		super(source);
	}
}
