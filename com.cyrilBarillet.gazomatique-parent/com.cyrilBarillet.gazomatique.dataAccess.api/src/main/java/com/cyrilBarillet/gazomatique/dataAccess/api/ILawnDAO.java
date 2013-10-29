package com.cyrilBarillet.gazomatique.dataAccess.api;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * Manage persistence of the lawn.
 * @author cyrilbarillet
 *
 */
public interface ILawnDAO {

	/**
	 * Load data from the given source.
	 * @param information where find information.
	 * @return Lawn instance.
	 */
	LawnEntity loadData(LawnInformationVO information);
	
}
