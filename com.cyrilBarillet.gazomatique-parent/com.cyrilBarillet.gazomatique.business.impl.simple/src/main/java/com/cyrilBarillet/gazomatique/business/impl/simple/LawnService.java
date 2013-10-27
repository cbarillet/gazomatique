package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.model.LawnEntity;
import com.cyrilBarillet.gazomatique.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.model.valueObject.LawnInformationVO;

import com.cyrilBarillet.gazomatique.business.api.FinishMowingEvent;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEventListener;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;

public class LawnService implements ILawnService {

	private final List<FinishMowingEventListener> listeners = new CopyOnWriteArrayList<>();
	
	private ILawnMowerService lawnMowerService = null;
	
	@Override
	public void Mow(LawnInformationVO information) {
		// TODO créer la pelouse
		LawnEntity lawn = load(information);
		// TODO pour chaque tondeuse lancer la tonte
		for (LawnMowerEntity mower : lawn.getLawnMowers()) {
			getLawnMowerService().mow(mower);
			fireFinishMowingEvent(mower);
		}
	}

	LawnEntity load(LawnInformationVO information)
	{
		return null;
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
		if(lawnMowerService == null)
		{
			lawnMowerService = ServiceFactory.getInstance().getLawnMowerService();
		}
		return lawnMowerService;
	}
}
