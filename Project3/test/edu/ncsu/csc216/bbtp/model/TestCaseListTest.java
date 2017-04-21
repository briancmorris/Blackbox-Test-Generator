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
     * Tests the constructor of TestCaseList.
     */
    @Test
    public void testConstructor() {
        TestCaseList test = new TestCaseList("TestList", "TCL");
        assertTrue(test.isEmpty());
        assertEquals("TestList", test.getName());
    }
	
	/**
     * Tests the add and remove methods of TestCaseList.
     */
    @Test
    public void testAddRemove() {
        TestCaseList test = new TestCaseList("TestCaseList", "TCL");
        TestingType testType = new TestingType("test type", "test type", "test type");
        Date testDate1 = new Date();
        Date testDate2 = new Date();
        Date testDate3 = new Date();
        Date testDate4 = new Date();
        Date testDate5 = new Date();

        test.addTestCase("description", testType, testDate1, "expected", false, null, null, false);
        assertEquals(1, test.size());
        assertFalse(test.isEmpty());

        // invalid index
        try {
            test.getTestCaseAt(2);
            fail();
        } catch (Exception e) {
            // do nothing
        }

        assertEquals("description", test.getTestCaseAt(0).getDescription());
        assertEquals(0, test.indexOf("TCL-TC1"));

        Object[][] testArray = test.get2DArray();
        assertTrue(testArray[0][0].equals("TCL-TC1"));
        assertTrue(testArray[0][1].equals("description"));
        assertTrue(testArray[0][2].equals(testType));
        assertTrue(testArray[0][3].equals(testDate1));
        assertTrue(testArray[0][4] == null);
        assertTrue(testArray[0][5].equals(false));
        assertTrue(testArray[0][6].equals("expected"));
        assertTrue(testArray[0][7] == null);
        assertTrue(testArray[0][8].equals(false));

        assertTrue(test.addTestCase("description", testType, testDate1, "expected", false, null, null, false));
        assertTrue(test.addTestCase("description", testType, testDate2, "expected", true, testDate4, "actual", false));
        assertTrue(test.addTestCase("description", testType, testDate3, "expected", false, null, null, false));
        assertTrue(test.addTestCase("description", testType, testDate4, "expected", true, testDate5, "actual", false));
        assertTrue(test.addTestCase("description", testType, testDate5, "expected", false, null, null, false));

        assertTrue(test.removeTestCase("TCL-TC2"));
        assertTrue(test.removeTestCase("TCL-TC3"));
        assertTrue(test.removeTestCase("TCL-TC1"));

    }
}
