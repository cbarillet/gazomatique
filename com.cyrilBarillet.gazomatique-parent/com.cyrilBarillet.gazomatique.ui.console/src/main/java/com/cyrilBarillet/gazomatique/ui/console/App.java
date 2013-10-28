package com.cyrilBarillet.gazomatique.ui.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

/**
 * Console management.
 * 
 * @author cyrilbarillet
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Options options = new Options();
 	   options.addOption("h", "help", false, "prints the help content");
 	   options.addOption(OptionBuilder
 	 	       .withArgName("file")
 	 	       .hasArg()
 	 	       .isRequired()
 	 	       .withDescription("input file")
 	 	       .withLongOpt("input")
 	 	       .create("i"));
    	
    	// create the parser
        CommandLineParser parser = new GnuParser();
        try {
        	
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );
            ServiceFactory.getInstance().getLawnService().mow(new TextFileLawnInformationVO(""));
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
    }
}
