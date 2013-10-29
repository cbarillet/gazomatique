/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.InstructionEnum;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO;

/**
 * @author cyrilbarillet
 *
 */
public abstract class GenericCommandDAO implements ICommandDAO {

	protected abstract Logger getLogger();
	
	/* (non-Javadoc)
	 * @see com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO#findCommandByLawnMowerIndex(com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO, int)
	 */
	@Override
	public abstract List<CommandEntity> findCommandByLawnMowerIndex(LawnInformationVO information, int index);
	
	protected List<CommandEntity> createCommands(String lineOfCommands)
	{
		LinkedList<CommandEntity> commandEntities = new LinkedList<>();
		if(lineOfCommands != null)
		{
			for (char command : lineOfCommands.toCharArray()) {
				commandEntities.add(new CommandEntity(InstructionEnum.getInstruction(String.valueOf(command))));
			}
		}
		return commandEntities;
	}
}
