package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * The TestCaseListTest class tests the TestCaseList class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestCaseListTest {

	/**
     * test constructor
     */
	@Test
	public void testConstructor() 
	{
		TestCaseList test = new TestCaseList("TestList", "TCL");
		assertTrue(test.isEmpty());
		assertEquals("TestList", test.getName());
	
	}
	
	/**
     * test add/remove
     */
	@Test
	public void testAddRemove() 
	{
		TestCaseList test = new TestCaseList("TestCaseList", "TCL");
		TestingType testType = new TestingType("test type", "test type", "test type");
		Date testDate = new Date();
		
		
		test.addTestCase("description", testType, testDate, "expected", false, null, null, false);
		assertEquals(1, test.size());
		assertFalse(test.isEmpty());
		
		//invalid index
		try 
		{
			test.getTestCaseAt(2);
			fail();
		} catch (Exception e) 
		{
			//do nothing
		}
		
		assertEquals("description", test.getTestCaseAt(0).getDescription());
		assertEquals(0, test.indexOf("TCL-TC1"));
		
		Object[][] testArray = test.get2DArray();
		assertTrue(testArray[0][0].equals("TCL-TC1"));
		assertTrue(testArray[0][1].equals("description"));
		assertTrue(testArray[0][2].equals(testType));
		assertTrue(testArray[0][3].equals(testDate));
		assertTrue(testArray[0][4] == null);
		assertTrue(testArray[0][5].equals(false));
		assertTrue(testArray[0][6].equals("expected"));
		assertTrue(testArray[0][7] == null);
		assertTrue(testArray[0][8].equals(false));
	
		assertTrue(test.addTestCase("description", testType, testDate, "expected", false, null, null, false));
		
		assertTrue(test.removeTestCase("TCL-TC1"));
		
	}
}
