package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sender extends Thread {
	
	final Logger logger = LoggerFactory.getLogger(Sender.class);
	
	InetAddress groupeIP;
	int port;
	MulticastSocket socketEmission;
	String data;

	Sender(InetAddress groupeIP, int port, String data, String interfaceName) throws Exception {
		this.groupeIP = groupeIP;
		this.port = port;
		this.data = data;
		socketEmission = new MulticastSocket();
		if(interfaceName != null)
		{
			socketEmission.setNetworkInterface(NetworkInterface.getByName(interfaceName));
		}
		socketEmission.setTimeToLive(15); // pour un site
		start();
	}

	public void run() {

		try {
			byte[] messageInBytes;
			DatagramPacket message;

			ByteArrayOutputStream sortie = new ByteArrayOutputStream();
			if(getLogger().isInfoEnabled())
			{
				getLogger().info("Send " + getData());
			}
			(new DataOutputStream(sortie)).writeUTF(getData());
			messageInBytes = sortie.toByteArray();
			message = new DatagramPacket(messageInBytes, messageInBytes.length,
					getGroupeIP(), getPort());
			socketEmission.send(message);
		} catch (Exception exc) {
			getLogger().error("An error occured while sending message", exc);
		}
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * @return the groupeIP
	 */
	protected InetAddress getGroupeIP() {
		return groupeIP;
	}

	/**
	 * @param groupeIP the groupeIP to set
	 */
	protected void setGroupeIP(InetAddress groupeIP) {
		this.groupeIP = groupeIP;
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
	 * @return the socketEmission
	 */
	protected MulticastSocket getSocketEmission() {
		return socketEmission;
	}

	/**
	 * @param socketEmission the socketEmission to set
	 */
	protected void setSocketEmission(MulticastSocket socketEmission) {
		this.socketEmission = socketEmission;
	}

	/**
	 * @return the data
	 */
	protected String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	protected void setData(String data) {
		this.data = data;
	}
}
