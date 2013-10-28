package com.cyrilBarillet.gazomatique.ui.console;

import java.net.URISyntaxException;
import java.net.URL;

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
    	URL resourceURL = getClass().getResource("/test.txt");
    	try {
    		String[] args={"-i", resourceURL.toURI().getPath()};
    		App.main(args);
    	} 
    	catch (URISyntaxException e)
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
}
