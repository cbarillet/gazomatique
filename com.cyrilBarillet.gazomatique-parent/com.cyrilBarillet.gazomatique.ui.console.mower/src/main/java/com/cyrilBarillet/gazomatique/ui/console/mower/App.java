package com.cyrilBarillet.gazomatique.ui.console.mower;

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
 * Run mower.
 * 
 */
@SuppressWarnings("static-access")
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		// Create options
		Options options = new Options();
		options.addOption("h", "help", false, "prints the help content");
		options.addOption(OptionBuilder.withArgName("file").hasArg()
				.withDescription("Input file path").withLongOpt("file")
				.create("f"));
		options.addOption(OptionBuilder
				.withArgName("data")
				.hasArg()
				.withDescription(
						"data to process (use \\n as end line) : 2 2\\n1 1 S\\nGAGA")
				.withLongOpt("data").create("d"));
		options.addOption(OptionBuilder.withArgName("index").hasArg()
				.isRequired().withType(Integer.class)
				.withDescription("index of the lawn mower")
				.withLongOpt("index").create("i"));
		options.addOption(OptionBuilder.withArgName("port").hasArg()
				.isRequired().withType(Integer.class)
				.withDescription("port to use (ex : 4003)").withLongOpt("port")
				.create("p"));
		options.addOption(OptionBuilder.withArgName("multicast group IP")
				.hasArg().isRequired()
				.withDescription("IP of multicast group (ex : 228.0.0.4)")
				.withLongOpt("multicast").create("m"));
		options.addOption(OptionBuilder.withArgName("network").hasArg()
				.withDescription("Network interface to use (ex : en1)")
				.withLongOpt("network").create("n"));

		// Create the parser
		CommandLineParser parser = new GnuParser();
		try {
			// Parse the command line arguments
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("f") ^ line.hasOption("d")) {
				ILawnService service = ServiceFactory.getInstance()
						.getLawnService();

				// Adding listener
				service.addFinishMowingEventListener(new FinishMowingEventListener() {

					@Override
					public void handleFinishMowingEvent(FinishMowingEvent event) {
						LawnMowerEntity mower = (LawnMowerEntity) event
								.getSource();
						if (getLogger().isInfoEnabled()) {
							getLogger().info(mower.getCurrentPosition()
									.getCoordinates().getX()
									+ " "
									+ mower.getCurrentPosition()
											.getCoordinates().getY()
									+ " "
									+ mower.getCurrentPosition()
											.getOrientation());
						}
					}
				});

				LawnInformationVO information;
				if (line.hasOption("f")) {
					information = new TextFileLawnInformationVO(
							line.getOptionValue("f"));
				} else {
					information = new DataLawnInformationVO(
							line.getOptionValue("d"));
				}
				Integer orderNumberMower = Integer.parseInt(line
						.getOptionValue("i"));
				Integer port = Integer.parseInt(line.getOptionValue("p"));
				service.start(information, orderNumberMower.intValue(), line
						.getOptionValue("m"), port,
						line.hasOption("n") ? line.getOptionValue("n") : null);
				return;
			}
		} catch (ParseException exp) {
			// oops, something went wrong
			if (logger.isErrorEnabled()) {
				logger.error("Parsing failed.  Reason: " + exp.getMessage(),
						exp);
			}
		}
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(App.class.getName()
				+ " - You must use only one parameter", options);
	}

	private static Logger getLogger() {
		return logger;
	}
}
