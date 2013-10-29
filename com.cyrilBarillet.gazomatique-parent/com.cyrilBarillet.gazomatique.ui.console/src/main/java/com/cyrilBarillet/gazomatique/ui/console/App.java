package com.cyrilBarillet.gazomatique.ui.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.FinishMowingEvent;
import com.cyrilBarillet.gazomatique.business.api.FinishMowingEventListener;
import com.cyrilBarillet.gazomatique.business.api.ILawnService;
import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.DataLawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

/**
 * Console management.
 * 
 * @author cyrilbarillet
 * 
 */
public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// Create options
		Options options = new Options();
		options.addOption("h", "help", false, "prints the help content");
		options.addOption(OptionBuilder.withArgName("file").hasArg()
				.withDescription("Input file path").withLongOpt("input")
				.create("i"));
		options.addOption(OptionBuilder
				.withArgName("data")
				.hasArg()
				.withDescription(
						"data to process (use \\n as end line) : 2 2\\n1 1 S\\nGAGA")
				.withLongOpt("data").create("d"));

		// Create the parser
		CommandLineParser parser = new GnuParser();
		try {
			// Parse the command line arguments
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("i") ^ line.hasOption("d")) {
				ILawnService service = ServiceFactory.getInstance()
						.getLawnService();
				service.addFinishMowingEventListener(new FinishMowingEventListener() {

					@Override
					public void handleFinishMowingEvent(FinishMowingEvent event) {
						LawnMowerEntity mower = (LawnMowerEntity) event
								.getSource();
						System.out.println(mower.getCurrentPosition()
								.getCoordinates().getX()
								+ " "
								+ mower.getCurrentPosition().getCoordinates()
										.getY()
								+ " "
								+ mower.getCurrentPosition().getOrientation());
					}
				});
				LawnInformationVO information;
				if(line.hasOption("i"))
				{
					information = new TextFileLawnInformationVO(line
							.getOptionValue("i")); 
				}
				else
				{
					information = new DataLawnInformationVO(line.getOptionValue("d"));
				}
				service.mow(information);
				return;
			}
		} catch (ParseException exp) {
			// oops, something went wrong
			if(logger.isErrorEnabled())
			{
				logger.error("Parsing failed.  Reason: " + exp.getMessage(), exp);
			}
		}
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(App.class.getName()
				+ " - You must use only one parameter", options);
	}
}
