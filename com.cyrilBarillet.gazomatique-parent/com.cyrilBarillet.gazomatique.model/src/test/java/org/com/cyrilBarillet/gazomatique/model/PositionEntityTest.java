package org.com.cyrilBarillet.gazomatique.model;

import com.cyrilBarillet.gazomatique.common.model.OrientationEnum;
import com.cyrilBarillet.gazomatique.common.model.PositionEntity;

import junit.framework.TestCase;

public class PositionEntityTest extends TestCase {

	public PositionEntityTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEqualsObject() {
		assertEquals(new PositionEntity(1, 1, OrientationEnum.EAST), new PositionEntity(1, 1, OrientationEnum.EAST));
		assertEquals(new PositionEntity(1, 1, OrientationEnum.WEST), new PositionEntity(1, 1, OrientationEnum.WEST));
		assertEquals(new PositionEntity(1, 1, OrientationEnum.SOUTH), new PositionEntity(1, 1, OrientationEnum.SOUTH));
		assertEquals(new PositionEntity(1, 1, OrientationEnum.NORTH), new PositionEntity(1, 1, OrientationEnum.NORTH));
		
		assertEquals(new PositionEntity(1, 2, OrientationEnum.EAST), new PositionEntity(1, 2, OrientationEnum.EAST));
		assertEquals(new PositionEntity(1, 2, OrientationEnum.WEST), new PositionEntity(1, 2, OrientationEnum.WEST));
		assertEquals(new PositionEntity(1, 2, OrientationEnum.SOUTH), new PositionEntity(1, 2, OrientationEnum.SOUTH));
		assertEquals(new PositionEntity(1, 2, OrientationEnum.NORTH), new PositionEntity(1, 2, OrientationEnum.NORTH));
		
		assertEquals(new PositionEntity(2, 1, OrientationEnum.EAST), new PositionEntity(2, 1, OrientationEnum.EAST));
		assertEquals(new PositionEntity(2, 1, OrientationEnum.WEST), new PositionEntity(2, 1, OrientationEnum.WEST));
		assertEquals(new PositionEntity(2, 1, OrientationEnum.SOUTH), new PositionEntity(2, 1, OrientationEnum.SOUTH));
		assertEquals(new PositionEntity(2, 1, OrientationEnum.NORTH), new PositionEntity(2, 1, OrientationEnum.NORTH));
	}

}
