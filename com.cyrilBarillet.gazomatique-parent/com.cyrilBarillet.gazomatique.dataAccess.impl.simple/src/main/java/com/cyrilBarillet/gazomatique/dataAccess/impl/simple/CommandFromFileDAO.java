/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.InstructionEnum;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;
import com.cyrilBarillet.gazomatique.dataAccess.api.ICommandDAO;

/**
 * @author cyrilbarillet
 *
 */
public class CommandFromFileDAO implements ICommandDAO {

	final Logger logger = LoggerFactory.getLogger(CommandDAO.class);
	
	protected Logger getLogger()
	{
		return logger;
	}
	
	@Override
	public List<CommandEntity> findCommandByLawnMowerIndex(LawnInformationVO information, int index) {
		if (information instanceof TextFileLawnInformationVO) {
			TextFileLawnInformationVO textFileInformation = (TextFileLawnInformationVO) information;

			// Reading from file
			Path fileToRead = Paths.get(textFileInformation.getFilePath());
			if (Files.exists(fileToRead)) {
				try (BufferedReader reader = Files.newBufferedReader(
						fileToRead, Charset.defaultCharset())) {
					String lineFromFile = "";
					int lineIndex = 1;
					int lawnMowerIndex = -1;
					while ((lineFromFile = reader.readLine()) != null) {
						if (getLogger().isDebugEnabled()) {
							getLogger().debug("Read line : " + lineFromFile);
						}
						switch (lineIndex) {
						case 1:
							// nothing to do
							break;
						default:
							switch (lineIndex % 2) {
							case 0: // Position of mower
								lawnMowerIndex++;
								break;
							case 1:
								if(lawnMowerIndex == index)
								{
									return createCommands(lineFromFile);
								}
							}
						}
						lineIndex++;
					}
				} catch (IOException exception) {
					if (getLogger().isErrorEnabled()) {
						getLogger()
								.error("Error while reading file " + fileToRead, exception);
					}
				}
			}
		}
		return null;
	}
	
	List<CommandEntity> createCommands(String lineOfCommands)
	{
		LinkedList<CommandEntity> commandEntities = new LinkedList<>();
		if(lineOfCommands != null)
		{
			for (char command : lineOfCommands.toCharArray()) {
				commandEntities.add(new CommandEntity(InstructionEnum.getInstruction(String.valueOf(command))));
			}
		}
		return commandEntities;
	}

}
