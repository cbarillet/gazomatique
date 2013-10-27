package com.cyrilBarillet.gazomatique.business.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommandService;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;
import com.cyrilBarillet.gazomatique.common.tool.GenericFactory;

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

	public ILawnMowerService getLawnMowerService() {
		return getInstanceOfType("com.cyrilBarillet.gazomatique.business.impl.simple.LawnMowerService");
	}

	public ILawnService getLawnService() {
		return getInstanceOfType("com.cyrilBarillet.gazomatique.business.impl.simple.LawnService");
	}
	
	public ICommandService getCommandService()
	{
		return getInstanceOfType("com.cyrilBarillet.gazomatique.business.impl.simple.CommandService");
	}
}
