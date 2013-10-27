/**
 * 
 */
package com.cyrilBarillet.gazomatique.common.tool;

import org.slf4j.Logger;

/**
 * @author cyrilbarillet
 *
 */
public abstract class GenericFactory {

	protected abstract Logger getLogger();
	
	protected <T extends Object> T getInstanceOfType(String className)
	{
		try {
			@SuppressWarnings("unchecked")
			T instance = (T) Class
					.forName(
							className)
					.newInstance();
			return instance;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured during " + className + " instance creation", e);
			}
			throw new RuntimeException(e);
		}
	}
	
}
