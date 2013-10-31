/**
 * 
 */
package com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.DataLawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * @author cyrilbarillet
 * 
 */
public class LawnFromDataDAO extends GenericLawnDAO {

	/*
	 * Separator used in data representation
	 */
	static final String SEPARATOR = "\n";

	/*
	 * Logger of this class.
	 */
	final Logger logger = LoggerFactory.getLogger(LawnFromDataDAO.class);

	private String[] lines;
	
	private int index;

	@Override
	protected String readLine() {
		String line = null;
		if(index < getLines().length)
		{
			line = lines[index];
			setIndex(getIndex() + 1);
		}
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO#loadData(com.
	 * cyrilBarillet.gazomatique.model.valueObject.LawnInformationVO)
	 */
	@Override
	public LawnEntity loadData(LawnInformationVO information) {
		if (information instanceof DataLawnInformationVO) {
			DataLawnInformationVO dataInformation = (DataLawnInformationVO) information;
			if (dataInformation.getDataInfomation() != null) 
			{
					String separator = SEPARATOR;
					setIndex(0);
					String[] components = dataInformation.getDataInfomation().split(separator);
					if(components.length < 2)
					{
						separator = "\\\\n";
						components = dataInformation.getDataInfomation().split(separator);
					}
					setLines(components);
					return processLines();
			}
		}
		return null;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * @return the lines
	 */
	protected String[] getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	protected void setLines(String[] lines) {
		this.lines = lines;
	}

	/**
	 * @return the index
	 */
	protected int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	protected void setIndex(int index) {
		this.index = index;
	}
}
