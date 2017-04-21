package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * The TestCaseTest class tests the TestCase class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 *
 */
public class TestCaseTest {

    /**
     * Tests the constructor, getters, and setters of TestCase.
     */
    @Test
    public void testTestCase() {
        TestCase test = null;
        TestingType testType = new TestingType("test type", "test type", "test type");
        Date testDate = new Date();
        // test invalid constructs
        try {
            test = new TestCase("", "", null, null, "", false, null, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }

        try {
            test = new TestCase("test", "", null, null, "", false, null, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }
        try {
            test = new TestCase("test", "test", null, null, "", false, null, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }
        try {
            test = new TestCase("test", "test", testType, null, "", false, null, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }
        try {
            test = new TestCase("test", "test", testType, testDate, "", false, null, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }
        try {
            test = new TestCase("test", "test", testType, testDate, "test", true, null, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }
        try {
            test = new TestCase("test", "test", testType, testDate, "test", true, testDate, "", false);
            fail();
        } catch (Exception e) {
            assertTrue(test == null);
        }

        // testValid constructor
        test = new TestCase("ID", "description", testType, testDate, "expected", false, null, null, false);
        TestCase test2 = new TestCase("ID", "description", testType, testDate, "expected", false, null, null, false);

        assertEquals("ID", test.getTestCaseID());
        assertEquals("description", test.getDescription());
        assertEquals(testType, test.getTestingType());
        assertEquals(testDate, test.getCreationDateTime());
        assertEquals("expected", test.getExpectedResults());
        assertEquals(false, test.tested());
        assertEquals(null, test.getLastTestedDateTime());
        assertEquals(null, test.getActualResults());
        assertEquals(false, test.pass());

        test.setTestedStatus(true);
        test.setLastTestedDateTime(testDate);

        test2.setTestedStatus(true);
        test2.setLastTestedDateTime(testDate);

        assertTrue(test.equals(test2));
        assertTrue(0 == test.compareTo(test2));
        assertEquals(test2.hashCode(), test.hashCode());

    }

}
