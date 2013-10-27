package com.cyrilBarillet.gazomatique.business.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;

public class ServiceFactory {
	final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);
	private static ServiceFactory singleton = null;

	private ServiceFactory() {
		super();
	}

	private Logger getLogger()
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
		try {
			return (ILawnMowerService) Class
					.forName(
							"com.cyrilBarillet.gazomatique.impl.simple.LawnMowerService")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured during com.cyrilBarillet.gazomatique.impl.simple.LawnMowerService instance creation", e);
			}
			throw new RuntimeException(e);
		}
	}

	public ILawnService getLawnService() {
		try {
			return (ILawnService) Class
					.forName(
							"com.cyrilBarillet.gazomatique.impl.simple.LawnService")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured during com.cyrilBarillet.gazomatique.impl.simple.LawnService instance creation", e);
			}
			throw new RuntimeException(e);
		}
	}
}
