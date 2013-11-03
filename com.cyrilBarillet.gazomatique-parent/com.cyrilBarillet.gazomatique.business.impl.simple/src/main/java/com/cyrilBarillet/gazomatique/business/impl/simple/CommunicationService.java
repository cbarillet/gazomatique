/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommunicationService;
import com.cyrilBarillet.gazomatique.business.api.exception.CommunicationException;

/**
 * @author cyrilbarillet
 *
 */
public class CommunicationService implements ICommunicationService {

	final Logger logger = LoggerFactory.getLogger(CommunicationService.class);
	
	private InetAddress ip;
	private int port;
	private String mowerName;
	private String interfaceName;
	
	@Override
	public void setUp(InetAddress ip, int port, String mowerName, String interfaceName)
	{
		setIp(ip);
		setPort(port);
		setMowerName(mowerName);
		setInterfaceName(interfaceName);
	}
	
	@Override
	public void listen() throws CommunicationException
	{
		try {
			Thread receiverThread = new Thread(new Receiver(getIp(), getPort(), getMowerName(), getService(), getInterfaceName()));
			receiverThread.start();
			synchronized(this)
			{
				wait();
			}
		} catch (Exception e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured while listening", e);
			}
			throw new CommunicationException();
		}
	}
	
	@Override
	public void send(String data) throws CommunicationException
	{
		try {
			Thread senderThread = new Thread(new Sender(getIp(), getPort(), data, getInterfaceName()));
			senderThread.start();
		} catch (Exception e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured while sending", e);
			}
			throw new CommunicationException();
		}
	}
	
	/**
	 * 
	 * @return the logger of the class.
	 */
	protected Logger getLogger()
	{
		return logger;
	}

	/**
	 * @return the ip
	 */
	protected InetAddress getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	protected void setIp(InetAddress ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	protected int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	protected void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the mowerName
	 */
	protected String getMowerName() {
		return mowerName;
	}

	/**
	 * @param mowerName the mowerName to set
	 */
	protected void setMowerName(String mowerName) {
		this.mowerName = mowerName;
	}

	/**
	 * @return the service
	 */
	protected ICommunicationService getService() {
		return this;
	}

	/**
	 * @return the interfaceName
	 */
	protected String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	protected void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
}
