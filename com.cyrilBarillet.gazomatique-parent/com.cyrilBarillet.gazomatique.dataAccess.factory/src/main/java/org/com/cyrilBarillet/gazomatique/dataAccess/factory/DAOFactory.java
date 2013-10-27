package org.com.cyrilBarillet.gazomatique.dataAccess.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;


public class DAOFactory {

	final Logger logger = LoggerFactory.getLogger(DAOFactory.class);
	private static DAOFactory singleton = null;

	private DAOFactory() {
		super();
	}

	private Logger getLogger()
	{
		return logger;
	}
	
	public static DAOFactory getInstance() {
		if (singleton == null) {
			singleton = new DAOFactory();
		}
		return singleton;
	}
	
	public ILawnDAO getLawnDAO()
	{
		try {
			return (ILawnDAO) Class
					.forName(
							"com.cyrilBarillet.gazomatique.dataAccess.impl.simple.LawnDAO")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured during com.cyrilBarillet.gazomatique.dataAccess.impl.simple.LawnDAO instance creation", e);
			}
			throw new RuntimeException(e);
		}
	}
}
