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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

/**
 * @author cyrilbarillet
 * 
 */
public class LawnFromFileDAO extends GenericLawnDAO {

	static final String SEPARATOR = " ";

	/*
	 * Logger of this class.
	 */
	final Logger logger = LoggerFactory.getLogger(LawnFromFileDAO.class);

	private BufferedReader reader;

	@Override
	protected String readLine() {
		try {
			return getReader().readLine();
		} catch (IOException e) {
			if (getLogger().isErrorEnabled()) {
				getLogger().error("An error occured during read of line", e);
			}
			throw new RuntimeException("An error occured during read of line",
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO#loadData(com.
	 * cyrilBarillet.gazomatique.model.valueObject.LawnInformationVO)
	 */
	@Override
	public LawnEntity loadData(LawnInformationVO information) {
		if (information instanceof TextFileLawnInformationVO) {
			TextFileLawnInformationVO textFileInformation = (TextFileLawnInformationVO) information;

			// Reading from file
			Path fileToRead = Paths.get(textFileInformation.getFilePath());
			if (Files.exists(fileToRead)) {
				try {
					setReader(Files.newBufferedReader(fileToRead,
							Charset.defaultCharset()));
					return processLines();
				} catch (IOException exception) {
					if (getLogger().isErrorEnabled()) {
						getLogger().error(
								"Error while opening file " + fileToRead,
								exception);
					}
					throw new RuntimeException(
							"An error occured during opening of line",
							exception);
				} finally {
					if (getReader() != null) {
						try {
							getReader().close();
						} catch (IOException e) {
							if (getLogger().isErrorEnabled()) {
								getLogger().error(
										"Error while closing file "
												+ fileToRead, e);
							}
							throw new RuntimeException(
									"An error occured during close of line", e);
						}
					}
				}
			}
		}
		return null;
	}

	protected BufferedReader getReader() {
		return reader;
	}

	protected void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
