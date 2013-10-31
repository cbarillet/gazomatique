package com.cyrilBarillet.gazomatique.ui.console.mower;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyrilBarillet.gazomatique.business.api.ILawnService;
import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;
import com.cyrilBarillet.gazomatique.common.model.valueObject.DataLawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

/**
 * Hello world!
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
		options.addOption(OptionBuilder
				.withArgName("index")
				.hasArg()
				.isRequired()
				.withType(Integer.class)
				.withDescription(
						"index of the lawn mower")
				.withLongOpt("index").create("i"));
		options.addOption(OptionBuilder
				.withArgName("port")
				.hasArg()
				.isRequired()
				.withType(Integer.class)
				.withDescription(
						"port to use (ex : 4003)")
				.withLongOpt("port").create("p"));
		options.addOption(OptionBuilder
				.withArgName("multicast group IP")
				.hasArg()
				.isRequired()
				.withDescription(
						"IP of multicast group (ex : 228.0.0.4)")
				.withLongOpt("multicast").create("m"));
		options.addOption(OptionBuilder
				.withArgName("network")
				.hasArg()
				.isRequired()
				.withDescription(
						"Network interface to use (ex : en1)")
				.withLongOpt("network").create("n"));

		// Create the parser
		CommandLineParser parser = new GnuParser();
		try {
			// Parse the command line arguments
			CommandLine line = parser.parse(options, args);

			if (line.hasOption("f") ^ line.hasOption("d")) {
				ILawnService service = ServiceFactory.getInstance()
						.getLawnService();
				
				LawnInformationVO information;
				if (line.hasOption("f")) {
					information = new TextFileLawnInformationVO(
							line.getOptionValue("f"));
				} else {
					information = new DataLawnInformationVO(
							line.getOptionValue("d"));
				}
				Integer orderNumberMower = Integer.parseInt(line.getOptionValue("i"));
				Integer port = Integer.parseInt(line.getOptionValue("p"));
				service.start(information, orderNumberMower.intValue(), line.getOptionValue("m"), port, line.getOptionValue("n"));
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
	
	public static void client()
	{
		// Which port should we listen to
				int port = 5000;
				// Which address
				String group = "225.4.5.6";

				// Create the socket and bind it to port 'port'.
				try {
					MulticastSocket s = new MulticastSocket(port);
					// join the multicast group
					s.joinGroup(InetAddress.getByName(group));
					// Now the socket is set up and we are ready to receive packets

					// Create a DatagramPacket and do a receive
					byte buf[] = new byte[1024];
					DatagramPacket pack = new DatagramPacket(buf, buf.length);
					s.receive(pack);

					// Finally, let us do something useful with the data we just
					// received,
					// like print it on stdout :-)
					System.out.println("Received data from: "
							+ pack.getAddress().toString() + ":" + pack.getPort()
							+ " with length: " + pack.getLength());
					System.out.write(pack.getData(), 0, pack.getLength());
					System.out.println();

					// And when we have finished receiving data leave the multicast
					// group and
					// close the socket
					s.leaveGroup(InetAddress.getByName(group));
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static void send()
	{
		// Which port should we send to
		int port = 5000;
		// Which address
		String group = "225.4.5.6";
		// Which ttl
		int newttl = 15;
		
		// Create the socket but we don't bind it as we are only going to send data
		try {
			MulticastSocket s = new MulticastSocket();
			// Note that we don't have to join the multicast group if we are only
			// sending data and not receiving
			// Fill the buffer with some data
			byte buf[] = new byte[10];
			for (int i=0; i<buf.length; i++) buf[i] = (byte)i;
			// Create a DatagramPacket 
			DatagramPacket pack = new DatagramPacket(buf, buf.length,
								 InetAddress.getByName(group), port);
			// Do a send. Note that send takes a byte for the ttl and not an int.
			int ttl = s.getTimeToLive();
		    s.setTimeToLive(newttl);
		    s.send(pack);
		    s.setTimeToLive(ttl);
		    
		 // And when we have finished sending data close the socket
		    s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}
}
