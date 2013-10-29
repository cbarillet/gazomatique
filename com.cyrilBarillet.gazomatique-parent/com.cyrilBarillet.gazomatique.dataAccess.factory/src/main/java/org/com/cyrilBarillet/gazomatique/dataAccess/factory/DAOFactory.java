package org.com.cyrilBarillet.gazomatique.dataAccess.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.common.model.valueObject.TypeResourceEnum;
import com.cyrilBarillet.gazomatique.common.tool.GenericFactory;
import com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO;
import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;


public class DAOFactory extends GenericFactory {

	final Logger logger = LoggerFactory.getLogger(DAOFactory.class);
	private static DAOFactory singleton = null;

	private DAOFactory() {
		super();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	public static DAOFactory getInstance() {
		if (singleton == null) {
			singleton = new DAOFactory();
		}
		return singleton;
	}
	
	private ILawnDAO lawnFromDataDAO;
	
	private ILawnDAO lawnFromFileDAO;
	
	public ILawnDAO getLawnDAO(TypeResourceEnum typeResource)
	{
		switch(typeResource)
		{
		case DATA:
			if(lawnFromDataDAO == null)
			{
				if(getLogger().isDebugEnabled())
				{
					getLogger().debug("Asking for new instance of ILawnDAO (LawnFromDataDAO)");
				}
				lawnFromDataDAO = this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.LawnFromDataDAO"); 
			}
			return lawnFromDataDAO;
		case TEXT_FILE:
			if(lawnFromFileDAO == null)
			{
				if(getLogger().isDebugEnabled())
				{
					getLogger().debug("Asking for new instance of ILawnDAO (LawnFromFileDAO)");
				}
				lawnFromFileDAO = this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.LawnFromFileDAO"); 
			}
			return lawnFromFileDAO;
			default:
				throw new RuntimeException("Unexpected type : " + typeResource);
		}
	}
	
	private ICommandDAO commandFromDataDAO;
	
	private ICommandDAO commandFromFileDAO;
	
	public ICommandDAO getCommandDAO(TypeResourceEnum typeResource)
	{
		switch(typeResource)
		{
		case DATA:
			if(commandFromDataDAO == null)
			{
				if(getLogger().isDebugEnabled())
				{
					getLogger().debug("Asking for new instance of ICommandDAO (CommandFromDataDAO)");
				}
				commandFromDataDAO = this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.CommandFromDataDAO"); 
			}
			return commandFromDataDAO;
		case TEXT_FILE:
			if(commandFromFileDAO == null)
			{
				if(getLogger().isDebugEnabled())
				{
					getLogger().debug("Asking for new instance of ICommandDAO (CommandFromFileDAO)");
				}
				commandFromFileDAO = this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.CommandFromFileDAO"); 
			}
			return commandFromFileDAO;
			default:
				throw new RuntimeException("Unexpected type : " + typeResource);
		}
	}
}
