package org.com.cyrilBarillet.gazomatique.business.factory;

import com.cyrilBarillet.gazomatique.business.factory.ServiceFactory;

import junit.framework.TestCase;

public class ServiceFactoryTest extends TestCase {

	public ServiceFactoryTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetInstance() {
		ServiceFactory instance1 = ServiceFactory.getInstance();
		assertNotNull(instance1);
		ServiceFactory instance2 = null;
		for(int i = 0; i < 10; i++)
		{
			instance2 = ServiceFactory.getInstance();
			assertEquals(instance1, instance2);
		}
	}

	public void testGetLawnMowerService() {
		assertNotNull("testGetLawnMowerService", ServiceFactory.getInstance().getLawnMowerService());
	}

	public void testGetLawnService() {
		assertNotNull("testGetLawnMowerService", ServiceFactory.getInstance().getLawnService());
	}
}
