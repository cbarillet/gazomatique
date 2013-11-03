/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ICommunicationService;
import com.cyrilBarillet.gazomatique.business.api.exception.CommunicationException;

/**
 * @author cyrilbarillet
 * 
 */
public class CommunicationMulticastChannelService extends CommunicationService {

	final Logger logger = LoggerFactory
			.getLogger(CommunicationMulticastChannelService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cyrilBarillet.gazomatique.business.api.ICommunicationService#setUp
	 * (java.net.InetAddress, int, java.lang.String, java.lang.String)
	 */
	@Override
	public void setUp(InetAddress ip, int port, String mowerName,
			String interfaceName) {
		setIp(ip);
		setPort(port);
		setMowerName(mowerName);
		setInterfaceName(interfaceName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cyrilBarillet.gazomatique.business.api.ICommunicationService#listen()
	 */
	@Override
	public void listen() throws CommunicationException {
		// join multicast group on this interface, and also use this
		// interface for outgoing multicast datagrams
		NetworkInterface ni = null;
		try {
			DatagramChannel dc;
			if (getInterfaceName() != null) {
				ni = NetworkInterface.getByName(getInterfaceName());
				dc = DatagramChannel.open(StandardProtocolFamily.INET)
						.setOption(StandardSocketOptions.SO_REUSEADDR, true)
						.bind(new InetSocketAddress(getPort()))
						.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
			} else {
				dc = DatagramChannel.open(StandardProtocolFamily.INET)
						.setOption(StandardSocketOptions.SO_REUSEADDR, true)
						.bind(new InetSocketAddress(getPort()));
			}

			InetAddress group = getIp();

			MembershipKey key;
			if (ni != null) {
				key = dc.join(group, ni);
			} else {
				// By default, we take the first network interface
				key = dc.join(group, NetworkInterface.getByIndex(0));
			}

			//dc.configureBlocking(true);
			dc.connect(new InetSocketAddress(getPort()));
			//dc.configureBlocking(false);

			//dc.register(Selector.open(), SelectionKey.OP_READ);

			Scanner in = new Scanner(dc);
			boolean stop = false;
			while (!stop) {
				if (in.hasNextByte()) {
					String text = in.nextLine();
					if (getLogger().isDebugEnabled()) {
						getLogger().debug("I received this message : " + text);
					}
					if (!text.startsWith(getMowerName())) {
						if (getLogger().isDebugEnabled()) {
							getLogger().debug(
									"I'm not interested in this message : "
											+ text);
						}
						continue;
					}
					stop = true;
					if (getLogger().isDebugEnabled()) {
						getLogger().debug("This message is for me : " + text);
					}
				}
			}
			in.close();
			dc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Logger getLogger() {
		return logger;
	}
}
