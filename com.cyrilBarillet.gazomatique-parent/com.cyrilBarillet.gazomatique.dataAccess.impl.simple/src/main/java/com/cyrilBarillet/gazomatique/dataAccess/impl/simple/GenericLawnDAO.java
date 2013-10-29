/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;
import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.OrientationEnum;

/**
 * @author cyrilbarillet
 * 
 */
public abstract class GenericLawnDAO implements ILawnDAO {

	/*
	 * Separator use in file to delimit field
	 */
	static final String SEPARATOR = " ";

	/*
	 * Logger of this class.
	 */
	final Logger logger = LoggerFactory.getLogger(GenericLawnDAO.class);

	/**
	 * @return logger of the current class
	 */
	protected abstract Logger getLogger();

	/**
	 * @return new read line
	 */
	protected abstract String readLine();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO#loadData(com.
	 * cyrilBarillet.gazomatique.model.valueObject.LawnInformationVO)
	 */
	public LawnEntity processLines() {
		// Reading from file
		String lineFromFile = "";
		int lineIndex = 1;
		LawnEntity lawn = null;
		int indexOfMower = 0;
		while ((lineFromFile = readLine()) != null) {
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
							indexOfMower, lawn));
					indexOfMower++;
					break;
				// case 1: Instruction for the mower. We process
				// this data later.
				}
			}
			lineIndex++;
		}
		return lawn;
	}

	/**
	 * Create lawn mower instance with the given data.
	 * 
	 * @param line	data to use to build mower
	 * @param index	Number of the mower in the lawn
	 * @param lawn	lawn which the mower is going to mow
	 * @return new lawn mower
	 */
	protected LawnMowerEntity createLawnMower(String line, int index,
			LawnEntity lawn) {
		if (line != null) {
			String[] positions = line.split(SEPARATOR);
			if (positions.length == 3) {
				int x = Integer.parseInt(positions[0]);
				int y = Integer.parseInt(positions[1]);
				return new LawnMowerEntity(x, y,
						OrientationEnum.getInstruction(positions[2]), index,
						lawn);
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

	/**
	 * Create lawn with the given data
	 * 
	 * @param line data to use to build lawn
	 * @return new lawn
	 */
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
