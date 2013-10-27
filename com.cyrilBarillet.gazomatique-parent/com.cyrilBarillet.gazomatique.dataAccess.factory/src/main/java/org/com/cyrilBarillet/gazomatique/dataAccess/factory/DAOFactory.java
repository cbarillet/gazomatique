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
	
	public ILawnDAO getLawnDAO()
	{
		return this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.LawnDAO");
	}
	
	public ICommandDAO getCommandDAO(TypeResourceEnum typeResource)
	{
		switch(typeResource)
		{
		case DATA:
			return this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.CommandDAO");
		case TEXT_FILE:
			return this.getInstanceOfType("com.cyrilBarillet.gazomatique.dataAccess.impl.simple.CommandFromFileDAO");
			default:
				throw new RuntimeException("Unexpected type : " + typeResource);
		}
	}
}
