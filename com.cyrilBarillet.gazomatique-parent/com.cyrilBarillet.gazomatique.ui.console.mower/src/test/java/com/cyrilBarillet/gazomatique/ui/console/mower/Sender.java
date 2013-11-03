package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Send keyboard data to multicast socket.
 * 
 * @author cyrilbarillet
 *
 */
public class Sender extends Thread {
	final Logger logger = LoggerFactory.getLogger(Sender.class);
	
	private InetAddress groupIP;
	private int port;
	private MulticastSocket socketEmission;

	Sender(InetAddress groupeIP, int port) throws Exception {
		this.groupIP = groupeIP;
		this.port = port;
		socketEmission = new MulticastSocket();
		socketEmission.setNetworkInterface(NetworkInterface.getByName("en1"));
		socketEmission.setTimeToLive(15); // pour un site
		start();
	}

	public void run() {
		BufferedReader keyboardData;

		try {
			keyboardData = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String text = keyboardData.readLine();
				send(text);
			}
		} catch (Exception exc) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("En error occured while sending data", exc);
			}
		}
	}

	void send(String text) throws Exception {
		byte[] bytesMessage;
		DatagramPacket message;

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if(getLogger().isInfoEnabled())
		{
			getLogger().info("Multicast sender tester - send message : " + text);
		}
		(new DataOutputStream(out)).writeUTF(text);
		bytesMessage = out.toByteArray();
		message = new DatagramPacket(bytesMessage, bytesMessage.length,
				getGroupIP(), getPort());
		getSocketEmission().send(message);
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
	 * @return the socketEmission
	 */
	protected MulticastSocket getSocketEmission() {
		return socketEmission;
	}
	
	private Logger getLogger()
	{
		return logger;
	}
}
