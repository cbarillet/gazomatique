package com.cyrilBarillet.gazomatique.dataAccess.api;

import com.cyrilBarillet.gazomatique.model.CommandEntity;

public interface ICommandDAO {

	boolean read(String resourceName);
	
	boolean hasNext();
	
	CommandEntity nextCommand();
	
}
