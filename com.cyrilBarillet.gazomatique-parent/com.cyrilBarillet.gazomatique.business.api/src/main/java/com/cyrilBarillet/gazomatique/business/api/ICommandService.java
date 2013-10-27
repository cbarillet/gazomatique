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

	List<CommandEntity> loadForLawnMower(LawnInformationVO information, LawnMowerEntity mower);
	
}
