package com.cyrilBarillet.gazomatique.ui.console;

import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class FileAppTest 
    extends TestCase
{
	final Logger logger = LoggerFactory.getLogger(FileAppTest.class);
	
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
    		String[] args={"-i", resourceURL.toURI().getPath()};
    		App.main(args);
    	} 
    	catch (URISyntaxException e)
    	{
    		if(getLogger().isErrorEnabled())
    		{
    			getLogger().error("Error occured while getting resource /text.txt", e);
    		}
    	}
    }
    
    private Logger getLogger()
    {
    	return logger;
    }
}
