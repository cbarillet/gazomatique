/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
import com.cyrilBarillet.gazomatique.common.model.OrientationEnum;
import com.cyrilBarillet.gazomatique.common.model.PositionEntity;
import com.cyrilBarillet.gazomatique.common.model.valueObject.TextFileLawnInformationVO;

import junit.framework.TestCase;

/**
 * @author cyrilbarillet
 *
 */
public class LawnServiceTest extends TestCase {

	/**
	 * @param name
	 */
	public LawnServiceTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.cyrilBarillet.gazomatique.business.impl.simple.LawnService#load(com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO)}.
	 */
	public void testLoad() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test.txt");
		try {
			File testFile = new File(resourceURL.toURI());
			LawnEntity lawn = service.load(new TextFileLawnInformationVO(testFile.getPath()));
			assertNotNull(lawn);
			assertEquals(2, lawn.getLawnMowers().size());
			int index = 0;
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				assertEquals(index, mower.getIndex());
				index++;
			}
		} catch (URISyntaxException e) {
			fail();
		}
	}
	
	/**
	 * Test method for {@link com.cyrilBarillet.gazomatique.business.impl.simple.LawnService#Mow(com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO)}.
	 */
	public void testMow() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test1.txt");
		try {
			File testFile = new File(resourceURL.toURI());
			LawnEntity lawn = service.mow(new TextFileLawnInformationVO(testFile.getPath()));
			assertNotNull(lawn);
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				if(!mower.getStartingPosition().equals(mower.getCurrentPosition()))
				{
					fail();
				}
				assertEquals(mower.getStartingPosition(), mower.getCurrentPosition());
			}
		} catch (URISyntaxException e) {
			fail();
		}
	}
	
	/**
	 * Test method for {@link com.cyrilBarillet.gazomatique.business.impl.simple.LawnService#Mow(com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO)}.
	 */
	public void testMow2() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test2.txt");
		try {
			File testFile = new File(resourceURL.toURI());
			LawnEntity lawn = service.mow(new TextFileLawnInformationVO(testFile.getPath()));
			assertNotNull(lawn);
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				switch(mower.getIndex())
				{
				case 0: // AAAAAAAAAAAAAAAAAAAA
					assertEquals(mower.getStartingPosition(), mower.getCurrentPosition());
					break;
				case 1: // G
					assertEquals(new PositionEntity(1, 1, OrientationEnum.WEST), mower.getCurrentPosition());
					break;
				case 2: // GA
					assertEquals(new PositionEntity(0, 1, OrientationEnum.WEST), mower.getCurrentPosition());
					break;
				case 3: // GD
					assertEquals(mower.getStartingPosition(), mower.getCurrentPosition());
					break;
				case 4: // GG
					assertEquals(new PositionEntity(1, 1, OrientationEnum.SOUTH), mower.getCurrentPosition());
					break;
				case 5:
					assertEquals(new PositionEntity(0, 0, OrientationEnum.SOUTH), mower.getCurrentPosition());
					break;
				case 6:
					assertEquals(mower.getStartingPosition(), mower.getCurrentPosition());
					break;
				}
				
			}
		} catch (URISyntaxException e) {
			fail();
		}
	}

	public void testMow3() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test3.txt");
		try {
			File testFile = new File(resourceURL.toURI());
			LawnEntity lawn = service.mow(new TextFileLawnInformationVO(testFile.getPath()));
			assertNotNull(lawn);
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				switch(mower.getIndex())
				{
				case 0: // GAGAGAGAA
					assertEquals(new PositionEntity(1, 3, OrientationEnum.NORTH), mower.getCurrentPosition());
					break;
				case 1: // AADAADADDA
					assertEquals(new PositionEntity(5, 1, OrientationEnum.EAST), mower.getCurrentPosition());
					break;
				}
			}
		} catch (URISyntaxException e) {
			fail();
		}
	}
	
	public void testMow4() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test4.txt");
		try {
			File testFile = new File(resourceURL.toURI());
			LawnEntity lawn = service.mow(new TextFileLawnInformationVO(testFile.getPath()));
			assertNotNull(lawn);
			for (LawnMowerEntity mower : lawn.getLawnMowers()) {
				switch(mower.getIndex())
				{
				case 0: // GAGAGAGAA
					assertEquals(new PositionEntity(2, 2, OrientationEnum.NORTH), mower.getCurrentPosition());
					break;
				}
			}
		} catch (URISyntaxException e) {
			fail();
		}
	}

}
