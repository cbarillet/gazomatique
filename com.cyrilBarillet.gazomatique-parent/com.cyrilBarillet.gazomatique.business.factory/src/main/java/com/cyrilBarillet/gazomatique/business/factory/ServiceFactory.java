package com.cyrilBarillet.gazomatique.business.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommandService;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;
import com.cyrilBarillet.gazomatique.common.tool.GenericFactory;

/**
 * Factory which manages the build of service
 * 
 * @author cyrilbarillet
 *
 */
public class ServiceFactory extends GenericFactory {
	final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);
	private static ServiceFactory singleton = null;

	private ServiceFactory() {
		super();
	}

	@Override
	protected Logger getLogger()
	{
		return logger;
	}
	
	public static ServiceFactory getInstance() {
		if (singleton == null) {
			singleton = new ServiceFactory();
		}
		return singleton;
	}

	private ILawnMowerService lawnMowerService;
	public ILawnMowerService getLawnMowerService() {
		if(lawnMowerService == null)
		{
			if(getLogger().isDebugEnabled())
			{
				getLogger().debug("Asking for new instance of ILawnMowerService");
			}
			lawnMowerService = getInstanceOfType("com.cyrilBarillet.gazomatique.business.impl.simple.LawnMowerService"); 
		}
		return lawnMowerService;
	}

	private ILawnService lawnService;
	public ILawnService getLawnService() {
		if(lawnService == null)
		{
			if(getLogger().isDebugEnabled())
			{
				getLogger().debug("Asking for new instance of ILawnService");
			}
			lawnService = getInstanceOfType("com.cyrilBarillet.gazomatique.business.impl.simple.LawnService"); 
		}
		return lawnService;
	}
	
	private ICommandService commandService;
	public ICommandService getCommandService()
	{
		if(commandService == null)
		{
			if(getLogger().isDebugEnabled())
			{
				getLogger().debug("Asking for new instance of ICommandService");
			}
			commandService = getInstanceOfType("com.cyrilBarillet.gazomatique.business.impl.simple.CommandService"); 
		}
		return commandService;
	}
}
