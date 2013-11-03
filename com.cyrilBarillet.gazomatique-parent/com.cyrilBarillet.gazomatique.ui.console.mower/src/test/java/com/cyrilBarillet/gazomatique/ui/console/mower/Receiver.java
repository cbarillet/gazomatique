package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Receive multicast message according to the given groupIP and port.
 * 
 * @author cyrilbarillet
 *
 */
public class Receiver extends Thread {
	
	final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	InetAddress groupIP;
	int port;
	MulticastSocket socketReception;

	/**
	 * Build new instance.
	 * 
	 * @param groupeIP multicast group to listen
	 * @param port port to listen
	 * @throws Exception Error while listening
	 */
	Receiver(InetAddress groupeIP, int port) throws Exception {
		this.groupIP = groupeIP;
		this.port = port;
		socketReception = new MulticastSocket(port);
		socketReception.setNetworkInterface(NetworkInterface.getByName("en1"));
		socketReception.joinGroup(groupeIP);
		start();
	}

	public void run() {
		DatagramPacket message;
		byte[] bytesMessage;
		String text;

		while (true) {
			bytesMessage = new byte[1024];
			message = new DatagramPacket(bytesMessage, bytesMessage.length);
			try {
				getSocketReception().receive(message);
				text = (new DataInputStream(new ByteArrayInputStream(
						bytesMessage))).readUTF();
				if(getLogger().isInfoEnabled())
				{
					getLogger().info("Multicast client tester - reception of message : " + text);
				}
			} catch (Exception exc) {
				if(getLogger().isErrorEnabled())
				{
					getLogger().error("An error occured while receiving data", exc);
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
	 * @return the port
	 */
	protected int getPort() {
		return port;
	}

	/**
	 * @return the socketReception
	 */
	protected MulticastSocket getSocketReception() {
		return socketReception;
	}
	
	private Logger getLogger()
	{
		return logger;
	}
}
