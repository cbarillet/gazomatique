package org.com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import com.cyrilBarillet.gazomatique.model.CommandEntity;
import com.cyrilBarillet.gazomatique.model.InstructionEnum;

public class EntityFactory {

	/*
	 * Unique instance of EntityFactory.
	 */
	private static EntityFactory singleton = null;
	
	/**
	 * Constructor.
	 */
	private EntityFactory()
	{
		// Nothing to do
	}
	
	/**
	 * Return an instance of EntityFactory
	 * 
	 * @return EntityFactory instance.
	 */
	public static EntityFactory getInstance()
	{
		// Simple implementation of singleton because we aren't in multithread environment.
		if(singleton == null)
		{
			singleton = new EntityFactory();
		}
		return singleton;
	}
	
	/**
	 * Return an instance of CommandEntity.
	 * 
	 * @param command Command read from the datasource.
	 * @return instance of CommandEntity.
	 */
	public CommandEntity getCommandEntity(final String command)
	{
		return new CommandEntity(InstructionEnum.getInstruction(command));
	}
}
