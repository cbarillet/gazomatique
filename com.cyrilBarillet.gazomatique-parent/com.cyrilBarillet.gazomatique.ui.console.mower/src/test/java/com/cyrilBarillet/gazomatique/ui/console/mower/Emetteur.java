package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Emetteur extends Thread {
	InetAddress  groupeIP;
	   int port;
	   MulticastSocket socketEmission;
	  
	   Emetteur(InetAddress groupeIP, int port) throws Exception {
	      this.groupeIP = groupeIP;
	      this.port = port;
	      socketEmission = new MulticastSocket();
	      socketEmission.setNetworkInterface(NetworkInterface.getByName("en1"));
	      socketEmission.setTimeToLive(15); // pour un site
	      start();
	  }
	    
	  public void run() {
	    BufferedReader entreeClavier;
	    
	    try {
	       entreeClavier = new BufferedReader(new InputStreamReader(System.in));
	       while(true) {
				  String texte = entreeClavier.readLine();
				  emettre(texte);
	       }
	    }
	    catch (Exception exc) {
	       System.out.println(exc);
	    }
	  } 

	  void emettre(String texte) throws Exception {
			byte[] contenuMessage;
			DatagramPacket message;
		
			ByteArrayOutputStream sortie = new ByteArrayOutputStream(); 
			System.out.println("TEST - Emission du texte : " + texte);
			(new DataOutputStream(sortie)).writeUTF(texte); 
			contenuMessage = sortie.toByteArray();
			message = new DatagramPacket(contenuMessage, contenuMessage.length, groupeIP, port);
			socketEmission.send(message);
	  }
}
