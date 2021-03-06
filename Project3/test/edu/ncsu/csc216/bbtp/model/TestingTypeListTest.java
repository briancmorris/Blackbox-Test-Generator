package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The TestingTypeListTest class tests the TestingTypeList class for completion.
 * 
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestingTypeListTest {

    /**
     * Tests the constructor of TestingTypeList.
     */
    @Test
    public void testConstructor() {
        TestingTypeList test = new TestingTypeList();
        assertTrue(test.isEmpty());
        assertEquals("Testing Types", test.getName());

    }

    /**
     * Tests the add method of TestingTypeList.
     */
    @Test
    public void testAddR() {
        TestingTypeList test = new TestingTypeList();
        test.addTestingType("TestType", "test");

        assertEquals(1, test.size());
        assertFalse(test.isEmpty());

        // invalid index
        try {
            test.getTestingTypeAt(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(1, test.size());
        }

        assertEquals("TestType", test.getTestingTypeAt(0).getName());
        assertEquals(0, test.indexOf("TT1"));
        assertEquals(0, test.indexOfName("TestType"));

        Object[][] testArray = test.get2DArray();
        assertTrue(testArray[0][0].equals("TT1"));
        assertTrue(testArray[0][1].equals("TestType"));
        assertTrue(testArray[0][2].equals("test"));

        test.addTestingType("TestType2", "test2");
        test.addTestingType("TestType3", "test3");

        testArray = test.get2DArray();
        assertTrue(testArray[1][0].equals("TT2"));
        assertTrue(testArray[1][1].equals("TestType2"));
        assertTrue(testArray[1][2].equals("test2"));

        assertEquals("TestType2", test.removeTestingTypeAt(1).getName());
        assertTrue(test.removeTestingType("TT1"));

    }

    /**
     * Tests remove method of TestingTypeList.
     */
    @Test
    public void testRemoveIdx() {

        TestingTypeList test = new TestingTypeList();

        try {
            test.removeTestingTypeAt(0);
            fail();
        } catch (Exception e) {
            assertEquals(0, test.size());
        }

        test.addTestingType("TestType1", "test1");
        test.addTestingType("TestType2", "test2");
        test.addTestingType("TestType3", "test3");
        test.addTestingType("TestType4", "test4");
        test.addTestingType("TestType5", "test5");
        test.addTestingType("TestType6", "test6");
        test.addTestingType("TestType7", "test7");

        assertEquals("TestType4", test.removeTestingTypeAt(3).getName());

        try {
            test.removeTestingTypeAt(6);
            fail();
        } catch (Exception e) {
            assertEquals(6, test.size());
        }

    }

    /**
     * Tests the update method in TestingTypeList.
     */
    @Test
    public void testUpdate() {
        TestingTypeList test = new TestingTypeList();
        test.addTestingType("TestType", "test");
        TestingType testValue = test.getTestingTypeAt(0);
        test.update(testValue, testValue);
        TestingType notInList = new TestingType("NotInList", "notinlist", "notinlist");
        test.update(notInList, notInList);
        assertEquals(1, test.size());
    }

}
