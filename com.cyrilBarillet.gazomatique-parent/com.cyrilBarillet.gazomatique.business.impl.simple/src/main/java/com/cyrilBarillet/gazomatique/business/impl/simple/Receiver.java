/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommunicationService;

/**
 * @author cyrilbarillet
 * 
 */
public class Receiver extends Thread {
	
	final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	private InetAddress groupIP;
	private int port;
	private MulticastSocket socketReception;
	private String mowerName;
	private ICommunicationService service;

	public Receiver(InetAddress ip, int port, String mowerName,
			ICommunicationService service, String interfaceName) throws Exception {
		this.service = service;
		this.groupIP = ip;
		this.port = port;
		this.mowerName = mowerName;
		socketReception = new MulticastSocket(port);
		if(interfaceName != null)
		{
			socketReception.setNetworkInterface(NetworkInterface.getByName(interfaceName));
		}
		socketReception.joinGroup(ip);
		start();
	}

	public void run() {
		DatagramPacket message;
		byte[] messageAsBytes;
		String text;

		boolean stop = false;
		while (!stop) {
			messageAsBytes = new byte[1024];
			message = new DatagramPacket(messageAsBytes, messageAsBytes.length);
			try {
				getSocketReception().receive(message);
				text = (new DataInputStream(new ByteArrayInputStream(
						messageAsBytes))).readUTF();
				if(getLogger().isDebugEnabled())
				{
					getLogger().debug("I received this message : " + text);
				}
				if (!text.startsWith(getMowerName()))
				{
					if(getLogger().isDebugEnabled())
					{
						getLogger().debug("I'm not interested in this message : " + text);
					}
					continue;
				}
				stop = true;
				if(getLogger().isDebugEnabled())
				{
					getLogger().debug("This message is for me : " + text);
				}
				getSocketReception().leaveGroup(getGroupIP());
				getSocketReception().close();
				getService().stopListening();
			} catch (Exception exc) {
				if(getLogger().isErrorEnabled())
				{
					getLogger().error("An error occured while processing network message", exc);
				}
				if(getSocketReception() != null)
				{
					try {
						getSocketReception().leaveGroup(getGroupIP());
					} catch (IOException e) {
						if(getLogger().isErrorEnabled())
						{
							getLogger().error("An error occured while processing network error", e);
						}
					}
					if(!getSocketReception().isClosed())
					{
						getSocketReception().close();
					}
				}
			}
		}
	}

	/**
	 * @return the groupIP
	 */
	protected InetAddress getGroupIP() {
		return groupIP;
	}

	/**
	 * @param groupIP the groupIP to set
	 */
	protected void setGroupIP(InetAddress groupIP) {
		this.groupIP = groupIP;
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
	 * @return the socketReception
	 */
	protected MulticastSocket getSocketReception() {
		return socketReception;
	}

	/**
	 * @param socketReception the socketReception to set
	 */
	protected void setSocketReception(MulticastSocket socketReception) {
		this.socketReception = socketReception;
	}

	/**
	 * @return the name
	 */
	protected String getMowerName() {
		return mowerName;
	}

	/**
	 * @param name the name to set
	 */
	protected void setMowerName(String mowerName) {
		this.mowerName = mowerName;
	}

	/**
	 * @return the service
	 */
	protected ICommunicationService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	protected void setService(ICommunicationService service) {
		this.service = service;
	}
	
	protected Logger getLogger()
	{
		return logger;
	}
}
