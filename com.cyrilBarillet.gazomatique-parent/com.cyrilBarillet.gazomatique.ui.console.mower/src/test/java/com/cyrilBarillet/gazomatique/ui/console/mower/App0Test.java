package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class App0Test 
    extends TestCase
{
	final Logger logger = LoggerFactory.getLogger(App0Test.class);
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public App0Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( App0Test.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    	String[] args={"-d", "5 5\\n1 2 N\\nGAGAGAGAA\\n3 3 E\\nAADAADADDA", "-i", "0", "-m", "228.0.0.4", "-p", "4003", "-n", "en1"};
    	try {
			new Sender(InetAddress.getByName("228.0.0.4"), 4003);
			new Receiver(InetAddress.getByName("228.0.0.4"), 4003);
		} catch (Exception e) {
			if(getLogger().isErrorEnabled())
			{
				getLogger().error("An error occured while creating Emetteur and Recepteur instance", e);
			}
		}
    	App.main(args);	
    }
    
    private Logger getLogger()
    {
    	return logger;
    }
}
