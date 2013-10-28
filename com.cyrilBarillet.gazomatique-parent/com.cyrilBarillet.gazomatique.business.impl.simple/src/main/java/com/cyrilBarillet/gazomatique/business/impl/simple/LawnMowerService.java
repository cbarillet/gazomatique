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
 * @author cyrilbarillet
 *
 */
public class LawnMowerService implements ILawnMowerService {

	final Logger logger = LoggerFactory.getLogger(LawnMowerService.class);
	
	private ICommandService commandService;
	
	private Logger getLogger()
	{
		return logger;
	}
	
	protected ICommandService getCommandService()
	{
		return commandService;
	}
	
	protected void setCommandService(ICommandService commandService)
	{
		this.commandService = commandService;
	}
	
	public LawnMowerService()
	{
		setCommandService(ServiceFactory.getInstance().getCommandService());
	}
	
	@Override
	public void mow(LawnInformationVO information, LawnMowerEntity lawnMower) {
		List<CommandEntity> commands = getCommandService().loadForLawnMower(information, lawnMower);
		int newXCoordinate, newYCoordinate;
		for (CommandEntity commandEntity : commands) {
			switch(commandEntity.getInstruction())
			{
			case LEFT:
				switch(lawnMower.getCurrentPosition().getOrientation())
				{
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
			case RIGHT:
				switch(lawnMower.getCurrentPosition().getOrientation())
				{
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
			case MOVE:
				switch(lawnMower.getCurrentPosition().getOrientation())
				{
				case EAST:
					newXCoordinate = lawnMower.getCurrentPosition().getCoordinates().x - 1;
					if(newXCoordinate >= 0)
					{
						lawnMower.changeYCoordinate(newXCoordinate);
					}
					else
					{
						if(getLogger().isDebugEnabled())
						{
							getLogger().debug("Lawn mower can't move to east : new x coordinate = {}", newXCoordinate);
						}
					}
					break;
				case NORTH:
					newYCoordinate = lawnMower.getCurrentPosition().getCoordinates().y + 1;
					if(newYCoordinate <= lawnMower.getLawn().getTopRightCorner().y)
					{
						lawnMower.changeYCoordinate(newYCoordinate);
					}
					else
					{
						if(getLogger().isDebugEnabled())
						{
							getLogger().debug("Lawn mower can't move to north : new y coordinate = {}", newYCoordinate);
						}
					}
					break;
				case SOUTH:
					newYCoordinate = lawnMower.getCurrentPosition().getCoordinates().y - 1;
					if(newYCoordinate >= 0)
					{
						lawnMower.changeYCoordinate(newYCoordinate);
					}
					else
					{
						if(getLogger().isDebugEnabled())
						{
							getLogger().debug("Lawn mower can't move to south : new y coordinate = {}", newYCoordinate);
						}
					}
					break;
				case WEST:
					newXCoordinate = lawnMower.getCurrentPosition().getCoordinates().x + 1;
					if(newXCoordinate <= lawnMower.getLawn().getTopRightCorner().x)
					{
						lawnMower.changeYCoordinate(newXCoordinate);
					}
					else
					{
						if(getLogger().isDebugEnabled())
						{
							getLogger().debug("Lawn mower can't move to west : new x coordinate = {}", newXCoordinate);
						}
					}
				}
			}
		}
	}
}
