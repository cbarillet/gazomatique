package org.com.cyrilBarillet.gazomatique.dataAccess.impl.simple;

import java.util.List;

import com.cyrilBarillet.gazomatique.common.model.CommandEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.DataLawnInformationVO;
import com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO;
import com.cyrilBarillet.gazomatique.dataAccess.impl.simple.CommandFromDataDAO;

import junit.framework.TestCase;

public class CommandFromDataDAOTest extends TestCase {

	protected class ExtendedCommandFromFileDAO extends CommandFromDataDAO {
		protected List<CommandEntity> createCommandsTest(String lineOfCommands) {
			return createCommands(lineOfCommands);
		}
	}

	public CommandFromDataDAOTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFindCommandByLawnMowerIndex() {
		CommandFromDataDAO dao = new CommandFromDataDAO();
		ExtendedCommandFromFileDAO daoTest = new ExtendedCommandFromFileDAO();
		LawnInformationVO information = new DataLawnInformationVO("5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA");
		// Test 1
		List<CommandEntity> expected = daoTest.createCommandsTest("GAGAGAGAA");
		List<CommandEntity> result = dao.findCommandByLawnMowerIndex(
				information, 0);
		assertEquals(expected, result);
		// Test 2
		expected = daoTest.createCommandsTest("AADAADADDA");
		result = dao.findCommandByLawnMowerIndex(information, 1);
		assertEquals(expected, result);
	}

}
