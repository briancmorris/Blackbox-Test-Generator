package edu.ncsu.csc216.bbtp;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import edu.ncsu.csc216.bbtp.model.TestingType;

/**
 * The BBTPTest class tests the BBTP class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class BBTPTest {

    /**
     * test BBTP.
     */
	@Test
	public void testBBTP() 
	{
		BBTP test = new BBTP();
		assertEquals( "Testing Types" , test.getTestingTypeList().getName());
		assertEquals(1, test.getNumTestCaseLists());
		
		//adds a new test case
		assertEquals(1, test.addTestCaseList());
		assertEquals(2, test.getNumTestCaseLists());
		
		
		//sets name and checks isChanged true
		test.setFilename("test name");
		assertTrue(test.isChanged());
		assertEquals("test name", test.getFilename());
		
		assertTrue(test.getTestCaseList(0) != null);
		
		//tests remove
		test.removeTestCaseList(0);
		assertEquals(1, test.getNumTestCaseLists());
		
		test.addTestCaseList();
		test.addTestCaseList();
		test.addTestCaseList();
		test.addTestCaseList();
		assertEquals(5, test.getNumTestCaseLists());
		
		
	}
	
	/**
     * test BBTP save and open.
     */
	@Test
	public void testSaveOpen() 
	{
		BBTP test = new BBTP();
		TestingType testType = new TestingType("test type", "test type", "test type");
		Date testDate = new Date();		
		
		
		test.addTestCaseList();
		test.getTestCaseList(0).addTestCase("description", testType, testDate, "expected", false, null, null, false);
		
		test.getTestingTypeList().addTestingType("test", "testdescrip");
		
		assertTrue(test.saveDataFile("test_files/actualSave.bbtp"));
		
		test = new BBTP();
		assertTrue(test.openDataFile("test_files/actualSave.bbtp"));
		
		assertEquals("description", test.getTestCaseList(0).getTestCaseAt(0).getDescription());
		assertEquals("test", test.getTestingTypeList().getTestingTypeAt(0).getName());
	}

}
