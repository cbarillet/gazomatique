package org.com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.List;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;
import com.cyrilBarillet.gazomatique.dataAccess.impl.simple.CommandFromFileDAO;

import junit.framework.TestCase;

public class CommandFromFileDAOTest extends TestCase {

	protected class ExtendedCommandFromFileDAO extends CommandFromFileDAO{
		protected List<CommandEntity> createCommandsTest(String lineOfCommands)
		{
			return createCommands(lineOfCommands);
		}
	}
	
	public CommandFromFileDAOTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFindCommandByLawnMowerIndex() {
		CommandFromFileDAO dao = new CommandFromFileDAO();
		ExtendedCommandFromFileDAO daoTest = new ExtendedCommandFromFileDAO();
		URL resourceURL = getClass().getResource("/test.txt");
		try {
			File testFile = new File(resourceURL.toURI());
			LawnInformationVO information;
			List<CommandEntity> expected = daoTest.createCommandsTest("GAGAGAGAA");
			information = new TextFileLawnInformationVO(testFile.getPath());
			List<CommandEntity> result = dao.findCommandByLawnMowerIndex(information, 0);
			assertEquals(expected, result);
			
			expected = daoTest.createCommandsTest("AADAADADDA");
			result = dao.findCommandByLawnMowerIndex(information, 1);
			assertEquals(expected, result);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		//LawnEntity lawn = service.mow(new TextFileLawnInformationVO(resourceURL.toURI().getPath()));
	}

}
