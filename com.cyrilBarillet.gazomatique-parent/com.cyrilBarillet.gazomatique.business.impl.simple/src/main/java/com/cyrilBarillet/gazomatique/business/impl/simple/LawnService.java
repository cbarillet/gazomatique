package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.com.cyrilBarillet.gazomatique.dataAccess.factory.DAOFactory;

import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEvent;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEventListener;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;

public class LawnService implements ILawnService {

	private ILawnDAO lawnDAO = null;
	
	private List<FinishMowingEventListener> listeners = new CopyOnWriteArrayList<>();
	
	private ILawnMowerService lawnMowerService = null;
	
	public LawnService()
	{
		super();
		setLawnMowerService(lawnMowerService = ServiceFactory.getInstance().getLawnMowerService());
		setLawnDAO(DAOFactory.getInstance().getLawnDAO());
	}
	
	@Override
	public void Mow(LawnInformationVO information) {
		LawnEntity lawn = load(information);
		for (LawnMowerEntity mower : lawn.getLawnMowers()) {
			getLawnMowerService().mow(mower);
			fireFinishMowingEvent(mower);
		}
	}

	LawnEntity load(LawnInformationVO information)
	{
		return getLawnDAO().loadData(information);
	}
	
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

	protected void fireFinishMowingEvent(LawnMowerEntity mower)
	{
		FinishMowingEvent event = new FinishMowingEvent(mower);
		for (FinishMowingEventListener listener : getListeners()) {
			listener.handleFinishMowingEvent(event);
		}
	}
	
	protected ILawnMowerService getLawnMowerService()
	{
		return lawnMowerService;
	}
	
	protected void setLawnMowerService(ILawnMowerService lawnMowerService)
	{
		this.lawnMowerService = lawnMowerService;
	}

	@Override
	public boolean loadData(String resourceName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LawnEntity nextCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the lawnDAO
	 */
	protected ILawnDAO getLawnDAO() {
		return lawnDAO;
	}

	/**
	 * @param lawnDAO the lawnDAO to set
	 */
	protected void setLawnDAO(ILawnDAO lawnDAO) {
		this.lawnDAO = lawnDAO;
	}

	/**
	 * @param listeners the listeners to set
	 */
	protected void setListeners(List<FinishMowingEventListener> listeners) {
		this.listeners = listeners;
	}
}
