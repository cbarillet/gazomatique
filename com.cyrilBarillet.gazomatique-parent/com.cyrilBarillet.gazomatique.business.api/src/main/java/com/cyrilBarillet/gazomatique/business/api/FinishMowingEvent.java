/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.api;

import java.util.EventObject;

import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;

/**
 * Event sent when a mower finish its instructions.
 *
 * @author cyrilbarillet
 *
 */
public class FinishMowingEvent extends EventObject {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -3754458445364498697L;

	/**
	 * Constructor of the vent.
	 * @param source element which throws this event.
	 */
	public FinishMowingEvent(final LawnMowerEntity source) {
		super(source);
	}
}
