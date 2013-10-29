/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.api;

import java.util.List;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * @author cyrilbarillet
 *
 */
public interface ICommandService {

	/**
	 * Load commands for a given mower.
	 * 
	 * @param information information about where to find commands.
	 * @param mower mower for which you want the commands
	 * @return List of commands
	 */
	List<CommandEntity> loadForLawnMower(LawnInformationVO information, LawnMowerEntity mower);
	
}
