/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.api;

import java.util.List;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * Manage the persistence of command.
 * @author cyrilbarillet
 *
 */
public interface ICommandDAO {

	/**
	 * Find the commands of the mower at the given index.
	 * @param information where to search
	 * @param index index of the mower for which you want the commands
	 * @return List of the commands.
	 */
	List<CommandEntity> findCommandByLawnMowerIndex(LawnInformationVO information, int index);
	
}
