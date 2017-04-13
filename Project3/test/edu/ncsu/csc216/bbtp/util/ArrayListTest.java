package edu.ncsu.csc216.bbtp.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The ArrayListTest class tests the ArrayList class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class ArrayListTest {

    /**
     * Tests the constructors of ArrayList.
     */
    @Test
    public void testArrayList() {
        ArrayList test = new ArrayList();
        assertEquals(0, test.size());
        test = new ArrayList(10);
        assertEquals(0, test.size());
    }

    /**
     * Tests the isEmpty method of ArrayList.
     */
    @Test
    public void testIsEmpty() {
        ArrayList test = new ArrayList();
        assertTrue(test.isEmpty());
        test.add("test");
        assertFalse(test.isEmpty());
    }
    
    /**
     * Tests the contains method of ArrayList.
     */
    @Test
    public void testContains() {
        ArrayList test = new ArrayList();
        assertFalse(test.contains("test"));
        test.add("test");
        assertTrue(test.contains("test"));
        assertFalse(test.contains("test2"));
    }
    
    /**
     * Tests the add method of ArrayList.
     */
    @Test
    public void testAdd() {
        ArrayList test = new ArrayList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test1"));
        
        //Test Array Growth
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        assertTrue(test.add("test4"));
        assertEquals(4, test.size());
        assertTrue(test.add("test5"));
        assertEquals(5, test.size());
        assertTrue(test.add("test6"));
        assertEquals(6, test.size());
        assertTrue(test.add("test7"));
        assertEquals(7, test.size());
        assertTrue(test.add("test8"));
        assertEquals(8, test.size());
        assertTrue(test.add("test9"));
        assertEquals(9, test.size());
        assertTrue(test.add("test10"));
        assertEquals(10, test.size());
        assertTrue(test.add("test11"));
        assertEquals(11, test.size());
        
        // Ensure that array growth kept everything in the correct order
        assertTrue(test.get(0).equals("test1"));
        assertTrue(test.get(1).equals("test2"));
        assertTrue(test.get(2).equals("test3"));
        assertTrue(test.get(3).equals("test4"));
        assertTrue(test.get(4).equals("test5"));
        assertTrue(test.get(5).equals("test6"));
        assertTrue(test.get(6).equals("test7"));
        assertTrue(test.get(7).equals("test8"));
        assertTrue(test.get(8).equals("test9"));
        assertTrue(test.get(9).equals("test10"));
        assertTrue(test.get(10).equals("test11"));
        
        // Error Handling
        try {
            test.add(null);
            fail("Did not throw NullPointerException");
        } catch (NullPointerException e) {
            assertEquals(11, test.size());
        }
        
        try {
            test.add("test1");
            fail("Did not throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(11, test.size());
        }
    }
    
    /**
     * Tests the get method of ArrayList.
     */
    @Test
    public void testGet() {
        ArrayList test = new ArrayList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test1"));
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.get(1).equals("test2"));
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        
        try {
            test.get(-1);
            fail("Did not throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(3, test.size());
        }
        try {
            test.get(3);
            fail("Did not throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(3, test.size());
        }
    }
    
    /**
     * Tests the add method of ArrayList that accepts an
     * index parameter.
     */
    @Test
    public void testAddIdx() {
        ArrayList test = new ArrayList();
        assertEquals(0, test.size());
        test.add(0, "test1");
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test1"));
        test.add(1, "test2");
        assertEquals(2, test.size());
        assertTrue(test.get(1).equals("test2"));
        test.add(0, "test3");
        assertEquals(3, test.size());
        assertTrue(test.get(0).equals("test3"));
        assertTrue(test.get(1).equals("test1"));
        assertTrue(test.get(2).equals("test2"));
        
        //Test Array Growth
        test.add(3, "test4");
        assertEquals(4, test.size());
        assertTrue(test.get(3).equals("test4"));
        test.add(4, "test5");
        assertEquals(5, test.size());
        assertTrue(test.get(4).equals("test5"));
        test.add(3, "test6");
        assertEquals(6, test.size());
        assertTrue(test.get(3).equals("test6"));
        test.add(0, "test7");
        assertEquals(7, test.size());
        assertTrue(test.get(0).equals("test7"));
        test.add(0, "test8");
        assertEquals(8, test.size());
        assertTrue(test.get(0).equals("test8"));
        test.add(0, "test9");
        assertEquals(9, test.size());
        assertTrue(test.get(0).equals("test9"));
        test.add(0, "test10");
        assertEquals(10, test.size());
        assertTrue(test.get(0).equals("test10"));
        test.add(0, "test11");
        assertEquals(11, test.size());
        assertTrue(test.get(0).equals("test11"));
        
        //Error Handling
        try {
            test.add(0, null);
            fail("Did not throw NullPointerException.");
        } catch (NullPointerException e){
            assertEquals(11, test.size());
        }
        
        try {
            test.add(0, "test1");
            fail("Did not throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(11, test.size());
        }
        
        try {
            test.add(-1, "fail");
            fail("Did not throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(11, test.size());
        }
        try {
            test.add(12, "fail");
            fail("Did not throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(11, test.size());
        }
    }
    
    /**
     * Tests the remove method of ArrayList.
     */
    @Test
    public void testRemove() {
        ArrayList test = new ArrayList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test1"));
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.get(1).equals("test2"));
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        
        assertTrue(test.remove(2).equals("test3"));
        assertEquals(2, test.size());
        assertTrue(test.remove(0).equals("test1"));
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test2"));
        
        //Error Handling
        try {
            test.remove(-1);
            fail("Did not throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(1, test.size());
        }
        try {
            test.remove(1);
            fail("Did not throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(1, test.size());
        }
    }
    
    /**
     * Tests the indexOf method of ArrayList.
     */
    @Test
    public void testIndexOf() {
        ArrayList test = new ArrayList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test1"));
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.get(1).equals("test2"));
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        
        assertEquals(0, test.indexOf("test1"));
        assertEquals(1, test.indexOf("test2"));
        assertEquals(2, test.indexOf("test3"));
        assertEquals(-1, test.indexOf("test"));
    }
}
