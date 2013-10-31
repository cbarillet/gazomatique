package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.com.cyrilBarillet.gazomatique.dataAccess.factory.DAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.FinishMowingEvent;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEventListener;
import com.cyrilBarillet.gazomatique.business.api.ICommunicationService;
import com.cyrilBarillet.gazomatique.business.api.ILawnMowerService;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;
import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TypeResourceEnum;
import com.cyrilBarillet.gazomatique.dataAccess.api.ILawnDAO;

/**
 * Implementation of the lawn manager.
 * 
 * @author cyrilbarillet
 * 
 */
public class LawnService implements ILawnService {

	/*
	 * Logger of the class.
	 */
	final Logger logger = LoggerFactory.getLogger(LawnService.class);
	
	/*
	 * Listeners to notify when a lawn mow has finished its work.
	 */
	private List<FinishMowingEventListener> listeners = new CopyOnWriteArrayList<>();

	/*
	 * Manager of lawn mower.
	 */
	private ILawnMowerService lawnMowerService;
	
	private ICommunicationService communicationService;

	/*
	 * YES if the mower must mow.
	 */
	private boolean startMow = false;
	
	/**
	 * Constructor.
	 */
	public LawnService() {
		super();
		setLawnMowerService(ServiceFactory.getInstance().getLawnMowerService());
	}

	@Override
	public LawnEntity mow(LawnInformationVO information) {
		LawnEntity lawn = load(information);
		if (lawn != null) {
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				getLawnMowerService().mow(information, mower);
				fireFinishMowingEvent(mower);
			}
		}
		return lawn;
	}

	@Override
	public LawnEntity load(LawnInformationVO information) {
		return getLawnDAO(information.getTypeResource()).loadData(information);
	}

	protected String buildName(int index) {
		return "#" + String.valueOf(index) + "#";
	}

	public void start(LawnInformationVO information, int index, String ip,
			int port, String interfaceName) {
		try {
			InetAddress groupIP = InetAddress.getByName(ip);
			String name = buildName(index);
			String nameOfNextMower = buildName(index + 1);
			this.communicationService = ServiceFactory.getInstance().getCommunicationService();
			this.communicationService.setUp(groupIP, port, name, interfaceName);
			if(index > 0)
			{
				this.communicationService.listen();
			}
			// Amazing : we can mow ;-)
			LawnEntity lawn = load(information);
			if(lawn.getLawnMowers().size() > index)
			{
				LawnMowerEntity mower = lawn.getLawnMowers().get(index);
				launchMower(information, mower);
				this.communicationService.send(nameOfNextMower + "("
						+ mower.getCurrentPosition().getCoordinates().getX() + ","
						+ mower.getCurrentPosition().getCoordinates().getY() + ","
						+ mower.getCurrentPosition().getOrientation() + ")");
			}
			else
			{
				if(getLogger().isWarnEnabled())
				{
					getLogger().warn("Nothing to do for lawn mower num " + index);
				}
			}
		} catch (Exception e) {
			getLogger().error("An error occured", e);
		}
	}

	public void launchMower(LawnInformationVO information, LawnMowerEntity mower) {
		getLawnMowerService().mow(information, mower);
		fireFinishMowingEvent(mower);
	}

	/**
	 * @return listeners to notify when a lawn mower has finished its work.
	 */
	protected List<FinishMowingEventListener> getListeners() {
		return listeners;
	}

	@Override
	public void addFinishMowingEventListener(FinishMowingEventListener listener) {
		if (listener != null) {
			getListeners().add(listener);
		}
	}

	@Override
	public void removeFinishMowingEventListener(
			FinishMowingEventListener listener) {
		getListeners().remove(listener);
	}

	/**
	 * Throw event to notify all listeners about end of mowing for the given
	 * mower.
	 * 
	 * @param mower
	 *            mower which has finished mowing.
	 */
	protected void fireFinishMowingEvent(LawnMowerEntity mower) {
		FinishMowingEvent event = new FinishMowingEvent(mower);
		for (FinishMowingEventListener listener : getListeners()) {
			listener.handleFinishMowingEvent(event);
		}
	}

	/**
	 * @return the lawnMowerService
	 */
	protected ILawnMowerService getLawnMowerService() {
		return lawnMowerService;
	}

	/**
	 * @param lawnMowerService
	 *            the lawnMowerService to set
	 */
	protected void setLawnMowerService(ILawnMowerService lawnMowerService) {
		this.lawnMowerService = lawnMowerService;
	}

	/**
	 * @return the lawnDAO
	 */
	protected ILawnDAO getLawnDAO(TypeResourceEnum typeResource) {
		return DAOFactory.getInstance().getLawnDAO(typeResource);
	}

	/**
	 * @param listeners
	 *            the listeners to set
	 */
	protected void setListeners(List<FinishMowingEventListener> listeners) {
		this.listeners = listeners;
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * @return the startMow
	 */
	protected boolean isStartMow() {
		return startMow;
	}

	/**
	 * @param startMow the startMow to set
	 */
	protected void setStartMow(boolean startMow) {
		this.startMow = startMow;
	}
}
