package com.cyrilBarillet.gazomatique.ui.console;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DataAppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataAppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataAppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	String[] args={"-d", "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA"};
    	App.main(args);
    }
}
