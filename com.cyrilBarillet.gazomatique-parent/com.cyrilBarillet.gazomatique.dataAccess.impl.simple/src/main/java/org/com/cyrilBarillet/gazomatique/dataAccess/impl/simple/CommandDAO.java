package org.com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.model.CommandEntity;

public class CommandDAO implements ICommandDAO {

	final Logger logger = LoggerFactory.getLogger(CommandDAO.class);
	
	/*
	 * Commands read by this DAO.
	 */
	protected String[] commands = {"G", "A", "G", "A", "G", "A", "G", "A", "A"};
	
	/*
	 * Index of the last read command.
	 */
	protected int index = -1;
	
	private Logger getLogger()
	{
		return logger;
	}
	
	@Override
	public CommandEntity nextCommand() 
	{
		// Check if developer has called read method.
		if(index < 0)
		{
			throw new RuntimeException("You have to call read method before call neextCommand");
		}
		// Read command
		String readCommand = commands[index];
		if(getLogger().isDebugEnabled())
		{
			getLogger().debug("Next read command {} at index {}", readCommand, index);
		}
		// Increment the current position
		index++;
		return EntityFactory.getInstance().getCommandEntity(readCommand);
	}

	@Override
	public boolean read(String resourceName) {
		index = 0;
		return true;
	}

	@Override
	public boolean hasNext() {
		return commands.length > index;
	}

}
