package org.com.cyrilBarillet.gazomatique.dataAccess.api;

import org.com.cyrilBarillet.gazomatique.model.CommandEntity;

public interface ICommandDAO {

	boolean read(String resourceName);
	
	boolean hasNext();
	
	CommandEntity nextCommand();
	
}
