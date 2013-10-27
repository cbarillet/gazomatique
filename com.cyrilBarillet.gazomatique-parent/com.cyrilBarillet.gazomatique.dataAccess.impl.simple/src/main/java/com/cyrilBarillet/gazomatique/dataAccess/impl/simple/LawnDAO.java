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

import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;
import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.OrientationEnum;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

/**
 * @author cyrilbarillet
 * 
 */
public class LawnDAO implements ILawnDAO {

	static final String SEPARATOR = " ";

	/*
	 * Logger of this class.
	 */
	final Logger logger = LoggerFactory.getLogger(LawnDAO.class);

	private Logger getLogger() {
		return logger;
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
				try (BufferedReader reader = Files.newBufferedReader(
						fileToRead, Charset.defaultCharset())) {
					String lineFromFile = "";
					int lineIndex = 1;
					LawnEntity lawn = null;
					while ((lineFromFile = reader.readLine()) != null) {
						if (getLogger().isDebugEnabled()) {
							getLogger().debug("Read line : " + lineFromFile);
						}
						switch (lineIndex) {
						case 1:
							lawn = createLawn(lineFromFile);
							break;
						default:
							switch (lineIndex % 2) {
							case 0: // Position of mower
								lawn.addLownMower(createLawnMower(lineFromFile,
										lawn));
								break;
							// case 1: Instruction for the mower. We process
							// this data later.
							}
						}
						lineIndex++;
					}
					return lawn;
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

	protected LawnMowerEntity createLawnMower(String line, LawnEntity lawn) {
		if (line != null) {
			String[] positions = line.split(SEPARATOR);
			if (positions.length == 3) {
				int x = Integer.parseInt(positions[0]);
				int y = Integer.parseInt(positions[1]);
				return new LawnMowerEntity(x, y,
						OrientationEnum.getInstruction(positions[2]), lawn);
			} else {
				if (getLogger().isErrorEnabled()) {
					getLogger().error(
							"Lawn mower line doesn't contain two coordinates and one orientation : "
									+ line);
				}
			}
		}
		return null;
	}

	protected LawnEntity createLawn(String line) {
		if (line != null) {
			String[] coordinates = line.split(SEPARATOR);
			if (coordinates.length == 2) {
				int x = Integer.parseInt(coordinates[0]);
				int y = Integer.parseInt(coordinates[1]);
				return new LawnEntity(x, y);
			} else {
				if (getLogger().isErrorEnabled()) {
					getLogger().error(
							"First line doesn't contain two coordinates : "
									+ line);
				}
			}
		}
		return null;
	}
}
