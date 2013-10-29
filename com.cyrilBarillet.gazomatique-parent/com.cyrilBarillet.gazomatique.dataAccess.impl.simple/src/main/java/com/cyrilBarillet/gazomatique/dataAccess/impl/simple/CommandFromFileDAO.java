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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

/**
 * @author cyrilbarillet
 *
 */
public class CommandFromFileDAO extends GenericLineByLineCommandDAO {

	final Logger logger = LoggerFactory.getLogger(CommandFromDataDAO.class);
	
	private BufferedReader reader = null;
	
	private Path fileToRead = null;
	
	@Override
	public List<CommandEntity> findCommandByLawnMowerIndex(LawnInformationVO information, int index) {
		if (information instanceof TextFileLawnInformationVO) {
			TextFileLawnInformationVO textFileInformation = (TextFileLawnInformationVO) information;

			// Reading from file
			setFileToRead(Paths.get(textFileInformation.getFilePath()));
			if (Files.exists(getFileToRead())) {
				try{
					setReader(Files.newBufferedReader(
							getFileToRead(), Charset.defaultCharset()));
					return processLines(index);
				} 
				catch (IOException exception) {
					if (getLogger().isErrorEnabled()) {
						getLogger()
								.error("Error while opening file " + getFileToRead(), exception);
					}
					throw new RuntimeException("Error while opening file", exception);
				}
				finally
				{
					if(getReader() != null)
					{
						try {
							getReader().close();
						} catch (IOException e) {
							if (getLogger().isErrorEnabled()) {
								getLogger()
										.error("Error while closing file " + getFileToRead(), e);
							}
							throw new RuntimeException("Error while closing file", e);
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	protected String readLine() {
		try
		{
			return getReader().readLine();
		}
		catch(IOException exception)
		{
			if (getLogger().isErrorEnabled()) {
				getLogger()
						.error("Error while reading file " + getFileToRead(), exception);
			}
			throw new RuntimeException("Error while reading file", exception);
		}
	}
	
	protected Logger getLogger()
	{
		return logger;
	}
	
	protected BufferedReader getReader()
	{
		return reader;
	}
	
	protected void setReader(final BufferedReader reader)
	{
		this.reader = reader;
	}
	
	protected Path getFileToRead()
	{
		return fileToRead;
	}
	
	protected void setFileToRead(final Path fileToRead)
	{
		this.fileToRead = fileToRead; 
	}
}
