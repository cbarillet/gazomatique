package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Recepteur extends Thread {
	InetAddress groupeIP;
	int port;
	MulticastSocket socketReception;

	Recepteur(InetAddress groupeIP, int port) throws Exception {
		this.groupeIP = groupeIP;
		this.port = port;
		socketReception = new MulticastSocket(port);
		socketReception.setNetworkInterface(NetworkInterface.getByName("en1"));
		socketReception.joinGroup(groupeIP);
		start();
	}

	public void run() {
		DatagramPacket message;
		byte[] contenuMessage;
		String texte;

		while (true) {
			contenuMessage = new byte[1024];
			message = new DatagramPacket(contenuMessage, contenuMessage.length);
			try {
				socketReception.receive(message);
				texte = (new DataInputStream(new ByteArrayInputStream(
						contenuMessage))).readUTF();
				System.out.println("TEST - Réception du texte : " + texte);
			} catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}
}
