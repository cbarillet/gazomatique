package com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

//import com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.DataLawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

public class CommandFromDataDAO extends GenericCommandDAO {

	final Logger logger = LoggerFactory.getLogger(CommandFromDataDAO.class);

	@Override
	public List<CommandEntity> findCommandByLawnMowerIndex(
			LawnInformationVO information, int index) {
		if(information instanceof DataLawnInformationVO)
		{
			DataLawnInformationVO dataInformation = (DataLawnInformationVO) information;
			if(dataInformation.getDataInfomation() != null)
			{
				String separator = "\n";
				String[] components = dataInformation.getDataInfomation().split(separator);
				int lineNumberOfCommands = 2 * index + 1 + 1;
				if(components.length - 1 < lineNumberOfCommands)
				{
					throw new RuntimeException("Not enough of lines in data");
				}
				String commandsLine = components[lineNumberOfCommands];
				return this.createCommands(commandsLine);
			}
		}
		return null;
	}
	
	protected Logger getLogger()
	{
		return logger;
	}
}
