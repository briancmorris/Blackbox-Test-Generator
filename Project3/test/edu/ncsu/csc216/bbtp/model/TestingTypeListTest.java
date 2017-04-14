package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The TestingTypeListTest class tests the TestingTypeList
 * class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 *
 */
public class TestingTypeListTest {

    /**
     * test constructor
     */
	@Test
	public void testConstructor() 
	{
		TestingTypeList test = new TestingTypeList();
		assertTrue(test.isEmpty());
		assertEquals("Testing Types", test.getName());
	
	}
	
	/**
     * test add/remove
     */
	@Test
	public void testAddRemove() 
	{
		TestingTypeList test = new TestingTypeList();
		test.addTestingType("TestType", "test");
		
		assertEquals(1, test.size());
		assertFalse(test.isEmpty());
		
		//invalid index
		try 
		{
			test.getTestingTypeAt(2);
			fail();
		} catch (Exception e) 
		{
			//do nothing
		}
		
		assertEquals("TestType", test.getTestingTypeAt(0).getName());
		assertEquals(0, test.indexOf("TT1"));
		assertEquals(0, test.indexOfName("TestType"));
		
		Object[][] testArray = test.get2DArray();
		assertTrue(testArray[0][0].equals("TT1"));
		assertTrue(testArray[0][1].equals("TestType"));
		assertTrue(testArray[0][2].equals("test"));
		
		assertTrue(test.removeTestingType("TestType"));
		
	}
	
	

}
