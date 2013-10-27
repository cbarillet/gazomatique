/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.api;

import java.util.List;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * @author cyrilbarillet
 *
 */
public interface ICommandDAO {

	List<CommandEntity> findCommandByLawnMowerIndex(LawnInformationVO information, int index);
	
}
