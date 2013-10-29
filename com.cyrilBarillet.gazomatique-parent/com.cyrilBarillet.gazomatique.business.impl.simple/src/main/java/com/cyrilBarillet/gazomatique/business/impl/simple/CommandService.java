package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.util.List;

import org.com.cyrilBarillet.gazomatique.dataAccess.factory.DAOFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommandService;
import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TypeResourceEnum;
import com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO;

/**
 * @see com.cyrilBarillet.gazomatique.business.api.ICommandService
 */
public class CommandService implements ICommandService {
	
	protected ICommandDAO getCommandDAO(TypeResourceEnum typeResource)
	{
		return DAOFactory.getInstance().getCommandDAO(typeResource);
	}
	
	public CommandService()
	{
		super();
	}
	
	@Override
	public List<CommandEntity> loadForLawnMower(LawnInformationVO information, LawnMowerEntity mower) {
		return getCommandDAO(information.getTypeResource()).findCommandByLawnMowerIndex(information,  mower.getIndex());
	}

}
