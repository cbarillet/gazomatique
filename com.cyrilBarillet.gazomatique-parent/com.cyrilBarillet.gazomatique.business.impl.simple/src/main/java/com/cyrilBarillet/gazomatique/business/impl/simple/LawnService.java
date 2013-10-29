package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.com.cyrilBarillet.gazomatique.dataAccess.factory.DAOFactory;

import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TypeResourceEnum;
import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEvent;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEventListener;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;

/**
 * Implementation of the lawn manager.
 * 
 * @author cyrilbarillet
 *
 */
public class LawnService implements ILawnService {
	
	/*
	 * Listeners to notify when a lawn mow has finished its work.
	 */
	private List<FinishMowingEventListener> listeners = new CopyOnWriteArrayList<>();
	
	/*
	 * Manager of lawn mower.
	 */
	private ILawnMowerService lawnMowerService;
	
	/**
	 * Constructor.
	 */
	public LawnService()
	{
		super();
		setLawnMowerService(ServiceFactory.getInstance().getLawnMowerService());
	}
	
	@Override
	public LawnEntity mow(LawnInformationVO information) {
		LawnEntity lawn = load(information);
		if(lawn != null)
		{
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				getLawnMowerService().mow(information, mower);
				fireFinishMowingEvent(mower);
			}
		}
		return lawn;
	}
	
	/**
	 * Create new instance of LawnEntity with the data loaded thanks to the given information.
	 * 
	 * @param information information allow application to read data about lawn.
	 * @return lawn defined by the given information.
	 */
	LawnEntity load(LawnInformationVO information)
	{
		return getLawnDAO(information.getTypeResource()).loadData(information);
	}
	
	/**
	 * @return listeners to notify when a lawn mower has finished its work.
	 */
	protected List<FinishMowingEventListener> getListeners()
	{
		return listeners;
	}
	
	@Override
	public void addFinishMowingEventListener(FinishMowingEventListener listener) {
		if(listener != null)
		{
			getListeners().add(listener);
		}
	}

	@Override
	public void removeFinishMowingEventListener(
			FinishMowingEventListener listener) {
		getListeners().remove(listener);
	}

	/**
	 * Throw event to notify all listeners about end of mowing for the given mower. 
	 * 
	 * @param mower mower which has finished mowing.
	 */
	protected void fireFinishMowingEvent(LawnMowerEntity mower)
	{
		FinishMowingEvent event = new FinishMowingEvent(mower);
		for (FinishMowingEventListener listener : getListeners()) {
			listener.handleFinishMowingEvent(event);
		}
	}
	
	/**
	 * @return the lawnMowerService
	 */
	protected ILawnMowerService getLawnMowerService()
	{
		return lawnMowerService;
	}
	
	/**
	 * @param lawnMowerService the lawnMowerService to set
	 */
	protected void setLawnMowerService(ILawnMowerService lawnMowerService)
	{
		this.lawnMowerService = lawnMowerService;
	}

	/**
	 * @return the lawnDAO
	 */
	protected ILawnDAO getLawnDAO(TypeResourceEnum typeResource) {
		return DAOFactory.getInstance().getLawnDAO(typeResource);
	}

	/**
	 * @param listeners the listeners to set
	 */
	protected void setListeners(List<FinishMowingEventListener> listeners) {
		this.listeners = listeners;
	}
}
