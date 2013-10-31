/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommandService;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.OrientationEnum;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;

/**
 * 
 * @see com.cyrilBarillet.gazomatique.business.api.ILawnMowerService
 * @author cyrilbarillet
 * 
 */
public class LawnMowerService implements ILawnMowerService {

	/*
	 * Logger of the class.
	 */
	final Logger logger = LoggerFactory.getLogger(LawnMowerService.class);

	private ICommandService commandService;

	/**
	 * Constructor.
	 */
	public LawnMowerService() {
		setCommandService(ServiceFactory.getInstance().getCommandService());
	}
	
	@Override
	public void mow(LawnInformationVO information, LawnMowerEntity lawnMower) {
		int newXCoordinate, newYCoordinate;
		// We load the commands for the current mower
		List<CommandEntity> commands = getCommandService().loadForLawnMower(
				information, lawnMower);
		// We execute each commands
		if (commands != null) {
			for (CommandEntity commandEntity : commands) {
				switch (commandEntity.getInstruction()) {
				case LEFT:
					switch (lawnMower.getCurrentPosition().getOrientation()) {
					case EAST:
						lawnMower.changeOrientation(OrientationEnum.NORTH);
						break;
					case NORTH:
						lawnMower.changeOrientation(OrientationEnum.WEST);
						break;
					case SOUTH:
						lawnMower.changeOrientation(OrientationEnum.EAST);
						break;
					case WEST:
						lawnMower.changeOrientation(OrientationEnum.SOUTH);
						break;
					}
					break;
				case RIGHT:
					switch (lawnMower.getCurrentPosition().getOrientation()) {
					case EAST:
						lawnMower.changeOrientation(OrientationEnum.SOUTH);
						break;
					case NORTH:
						lawnMower.changeOrientation(OrientationEnum.EAST);
						break;
					case SOUTH:
						lawnMower.changeOrientation(OrientationEnum.WEST);
						break;
					case WEST:
						lawnMower.changeOrientation(OrientationEnum.NORTH);
						break;
					}
					break;
				case MOVE:
					switch (lawnMower.getCurrentPosition().getOrientation()) {
					case WEST:
						newXCoordinate = lawnMower.getCurrentPosition()
								.getCoordinates().getX() - 1;
						// We check if we go off
						if (newXCoordinate >= 0) {
							lawnMower.changeXCoordinate(newXCoordinate);
						} else {
							if (getLogger().isDebugEnabled()) {
								getLogger()
										.debug("Lawn mower can't move to east : new x coordinate = {}",
												newXCoordinate);
							}
						}
						break;
					case NORTH:
						newYCoordinate = lawnMower.getCurrentPosition()
								.getCoordinates().getY() + 1;
						// We check if we go off
						if (newYCoordinate <= lawnMower.getLawn()
								.getTopRightCorner().getY()) {
							lawnMower.changeYCoordinate(newYCoordinate);
						} else {
							if (getLogger().isDebugEnabled()) {
								getLogger()
										.debug("Lawn mower can't move to north : new y coordinate = {}",
												newYCoordinate);
							}
						}
						break;
					case SOUTH:
						newYCoordinate = lawnMower.getCurrentPosition()
								.getCoordinates().getY() - 1;
						// We check if we go off
						if (newYCoordinate >= 0) {
							lawnMower.changeYCoordinate(newYCoordinate);
						} else {
							if (getLogger().isDebugEnabled()) {
								getLogger()
										.debug("Lawn mower can't move to south : new y coordinate = {}",
												newYCoordinate);
							}
						}
						break;
					case EAST:
						newXCoordinate = lawnMower.getCurrentPosition()
								.getCoordinates().getX() + 1;
						// We check if we go off
						if (newXCoordinate <= lawnMower.getLawn()
								.getTopRightCorner().getX()) {
							lawnMower.changeXCoordinate(newXCoordinate);
						} else {
							if (getLogger().isDebugEnabled()) {
								getLogger()
										.debug("Lawn mower can't move to west : new x coordinate = {}",
												newXCoordinate);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @return logger of the class
	 */
	private Logger getLogger() {
		return logger;
	}

	/**
	 * 
	 * @return command service
	 */
	protected ICommandService getCommandService() {
		return commandService;
	}

	/**
	 * Set up command service
	 * @param commandService instance to set up.
	 */
	protected void setCommandService(ICommandService commandService) {
		this.commandService = commandService;
	}
}
