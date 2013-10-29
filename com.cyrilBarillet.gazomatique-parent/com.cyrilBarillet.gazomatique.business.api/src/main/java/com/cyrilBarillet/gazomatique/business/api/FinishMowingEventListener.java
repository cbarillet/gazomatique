package com.cyrilBarillet.gazomatique.business.api;

/**
 * Listener of finish mowing event.
 * 
 * @author cyrilbarillet
 *
 */
public interface FinishMowingEventListener {
	
	/**
	 * Handle the finish mowing event.
	 * 
	 * @param event event to process.
	 */
	void handleFinishMowingEvent(FinishMowingEvent event);
}
