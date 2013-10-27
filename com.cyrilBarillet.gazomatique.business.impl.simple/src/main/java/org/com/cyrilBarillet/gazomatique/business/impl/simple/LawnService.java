package org.com.cyrilBarillet.gazomatique.business.impl.simple;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.com.cyrilBarillet.gazomatique.business.api.FinishMowingEvent;
import org.com.cyrilBarillet.gazomatique.business.api.FinishMowingEventListener;
import org.com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import org.com.cyrilBarillet.gazomatique.business.api.ILawnService;
import org.com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import org.com.cyrilBarillet.gazomatique.model.LawnEntity;
import org.com.cyrilBarillet.gazomatique.model.LawnMowerEntity;
import org.com.cyrilBarillet.gazomatique.model.valueObject.LawnInformationVO;

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
