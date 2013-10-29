/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import java.util.List;

import org.slf4j.Logger;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * @author cyrilbarillet
 * 
 */
public abstract class GenericLineByLineCommandDAO extends GenericCommandDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cyrilBarillet.gazomatique.dataAccess.impl.simple.GenericCommandDAO
	 * #getLogger()
	 */
	@Override
	protected Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cyrilBarillet.gazomatique.dataAccess.impl.simple.GenericCommandDAO
	 * #findCommandByLawnMowerIndex
	 * (com.cyrilBarillet.gazomatique.common.model.valueObject
	 * .LawnInformationVO, int)
	 */
	@Override
	public abstract List<CommandEntity> findCommandByLawnMowerIndex(
			LawnInformationVO information, int index);

	protected abstract String readLine();

	protected List<CommandEntity> processLines(int index) {
		String lineFromFile = "";
		int lineIndex = 1;
		int lawnMowerIndex = -1;
		while ((lineFromFile = readLine()) != null) {
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
					if (lawnMowerIndex == index) {
						return createCommands(lineFromFile);
					}
				}
			}
			lineIndex++;
		}
		return null;
	}
}
