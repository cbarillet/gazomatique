package com.cyrilBarillet.gazomatique.business.api;

import java.net.InetAddress;

import com.cyrilBarillet.gazomatique.business.api.exception.CommunicationException;

/**
 * Manage the communication between mower.
 * 
 * @author cyrilbarillet
 *
 */
public interface ICommunicationService {

	/**
	 * Setting up parameters.
	 * @param ip ip of group
	 * @param port port destination
	 * @param mowerName name of the mower which listen
	 * @param interfaceName network interface to use
	 */
	void setUp(InetAddress ip, int port, String mowerName, String interfaceName);
	
	/**
	 * Listen other mower.
	 * 
	 * @throws CommunicationException Error occurred while listening
	 */
	void listen() throws CommunicationException;
	
	void stopListening();
	
	/**
	 * Send the given data to other mower.
	 * 
	 * @param data data to send.
	 * @throws CommunicationException Error occurred wile sending
	 */
	void send(String data) throws CommunicationException;
	
}
