package com.cyrilBarillet.gazomatique.business.api;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * Lawn manager.
 * 
 * @author cyrilbarillet
 *
 */
public interface ILawnService {
	
	/**
	 * Mow the lawn defined by given information.
	 * 
	 * @param information information which define the lawn to mow.
	 * @return lawn defined by the given information.
	 */
	LawnEntity mow(LawnInformationVO information);
	
	/**
	 * Add a listener interested by the "FinishMowing" event. 
	 * 
	 * @param listener listener to notify when a lawn mower has finished.
	 */
	void addFinishMowingEventListener(FinishMowingEventListener listener);
	
	/**
	 * Remove a listener previously added from the list of object to notify.
	 * 
	 * @param listener listener to remove.
	 */
    void removeFinishMowingEventListener(FinishMowingEventListener listener);
}
