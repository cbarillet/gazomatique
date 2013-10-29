package com.cyrilBarillet.gazomatique.business.api;

import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * Manage lawn mower operation.
 * 
 * @author cyrilbarillet
 *
 */
public interface ILawnMowerService {
	
	/**
	 * Mow the lawn.
	 * 
	 * @param information where to find commands
	 * @param lawnMower lawn mower you want to use
	 */
	void mow(LawnInformationVO information, LawnMowerEntity lawnMower);
}
