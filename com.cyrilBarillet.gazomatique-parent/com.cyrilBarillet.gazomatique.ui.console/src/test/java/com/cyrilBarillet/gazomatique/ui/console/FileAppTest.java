package com.cyrilBarillet.gazomatique.ui.console;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class FileAppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FileAppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FileAppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	URL resourceURL = getClass().getResource("/test.txt");
    	try {
    		File testFile = new File(resourceURL.toURI());
    		String[] args={"-i", testFile.getPath()};
    		App.main(args);
    	} 
    	catch (URISyntaxException e)
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
}
