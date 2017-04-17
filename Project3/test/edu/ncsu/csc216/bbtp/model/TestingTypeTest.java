package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The TestingTypeTest class tests the TestingType class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestingTypeTest {

    /**
     * test constructor getters and setters
     */
	@Test
	public void testType() 
	{
		TestingType test = null;
		//test invalid constructs
		try
		{
			test = new TestingType("", "", "");
			fail();
		}
		catch (Exception e) 
		{
			assertTrue(test == null);
		}
		try 
		{
			test = new TestingType("test", "", "");
			fail();
		} catch (Exception e) {
			assertTrue(test == null);
		}
		try 
		{
			test = new TestingType("test", "test", "");
			fail();
		} catch (Exception e) {
			assertTrue(test == null);
		}
		
		test = new TestingType("ID", "name", "description");
		TestingType test2 = new TestingType( "ID", "name", "description");
		
		assertEquals("name", test.getName());
		assertEquals("description", test.getDescription());
		assertEquals("ID", test.getTestingTypeID());
		
		assertTrue(test.equals(test2));
		assertTrue(0 == test.compareTo(test2));
		assertEquals(test.toString(), test2.toString());
	}

}
