/**
 * 
 */
package com.cyrilBarillet.gazomatique.business.impl.simple;

import java.net.URISyntaxException;
import java.net.URL;

import com.cyrilBarillet.gazomatique.common.model.LawnEntity;
import com.cyrilBarillet.gazomatique.common.model.LawnMowerEntity;
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
	 * Test method for {@link com.cyrilBarillet.gazomatique.business.impl.simple.LawnService#Mow(com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO)}.
	 */
	public void testMow() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test1.txt");
		try {
			LawnEntity lawn = service.mow(new TextFileLawnInformationVO(resourceURL.toURI().getPath()));
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
	 * Test method for {@link com.cyrilBarillet.gazomatique.business.impl.simple.LawnService#load(com.cyrilBarillet.gazomatique.common.model.valueObject.LawnInformationVO)}.
	 */
	public void testLoad() {
		LawnService service = new LawnService();
		URL resourceURL = getClass().getResource("/test.txt");
		try {
			LawnEntity lawn = service.load(new TextFileLawnInformationVO(resourceURL.toURI().getPath()));
			assertNotNull(lawn);
			assertEquals(2, lawn.getLawnMowers().size());
		} catch (URISyntaxException e) {
			fail();
		}
	}

}
