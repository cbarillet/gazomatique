package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFromFileTest extends TestCase {

	final Logger logger = LoggerFactory.getLogger(AppFromFileTest.class);

	public AppFromFileTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMain() {
		URL resourceURL = getClass().getResource("/test.txt");
		try {
			String[] args = { "-f",
					resourceURL.toURI().getPath(), "-i", "1",
					"-m", "228.0.0.4", "-p", "4003", "-n", "en1" };
			try {
				new Sender(InetAddress.getByName("228.0.0.4"), 4003);
				new Receiver(InetAddress.getByName("228.0.0.4"), 4003);
			} catch (Exception e) {
				if (getLogger().isErrorEnabled()) {
					getLogger()
							.error("An error occured while creating Emetteur and Recepteur instance",
									e);
				}
			}
			App.main(args);
		} catch (URISyntaxException urie) {
			if (getLogger().isErrorEnabled()) {
				getLogger()
						.error("An error occured while building path to /test.txt",
								urie);
			}
		}
	}

	private Logger getLogger() {
		return logger;
	}
}
