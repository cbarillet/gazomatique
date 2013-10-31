package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Sender extends Thread {
	InetAddress groupeIP;
	int port;
	MulticastSocket socketEmission;
	String data;

	Sender(InetAddress groupeIP, int port, String data) throws Exception {
		this.groupeIP = groupeIP;
		this.port = port;
		this.data = data;
		socketEmission = new MulticastSocket();
		socketEmission.setNetworkInterface(NetworkInterface.getByName("en1"));
		socketEmission.setTimeToLive(15); // pour un site
		start();
	}

	public void run() {

		try {
			byte[] messageInBytes;
			DatagramPacket message;

			ByteArrayOutputStream sortie = new ByteArrayOutputStream();
			System.out.println("Send " + this.data);
			(new DataOutputStream(sortie)).writeUTF(this.data);
			messageInBytes = sortie.toByteArray();
			message = new DatagramPacket(messageInBytes, messageInBytes.length,
					groupeIP, port);
			socketEmission.send(message);
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
}
