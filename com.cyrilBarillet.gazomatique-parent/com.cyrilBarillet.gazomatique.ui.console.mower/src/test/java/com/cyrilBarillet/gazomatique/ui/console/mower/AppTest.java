package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.net.InetAddress;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    	String[] args={"-d", "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA", "-i", "0", "-m", "228.0.0.4", "-p", "4003", "-n", "en1"};
    	try {
			new Emetteur(InetAddress.getByName("228.0.0.4"), 4003);
			new Recepteur(InetAddress.getByName("228.0.0.4"), 4003);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	App.main(args);
    	
    }
}
