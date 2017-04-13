package edu.ncsu.csc216.bbtp.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The LinkedListTest class tests the LinkedList class for completion.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class LinkedListTest {

    /**
     * Tests the constructor of LinkedList.
     */
    @Test
    public void testLinkedList() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
    }
    
    /**
     * Tests the isEmpty method of LinkedList.
     */
    @Test
    public void testIsEmpty() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
        assertTrue(test.isEmpty());
        test.add("test");
        assertEquals(1, test.size());
        assertFalse(test.isEmpty());
    }
    
    /**
     * Tests the contains method of LinkedList.
     */
    @Test
    public void testContains() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
        assertFalse(test.contains("false"));
        test.add("test1");
        assertEquals(1, test.size());
        assertTrue(test.contains("test1"));
        test.add("test2");
        assertEquals(2, test.size());
        assertTrue(test.contains("test2"));
        assertFalse(test.contains("false"));
    }
    
    /**
     * Tests the add method of LinkedList.
     */
    @Test
    public void testAdd() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        
        try {
            test.add(null);
            fail("Did not throw NullPointerException.");
        } catch (NullPointerException e) {
            assertEquals(2, test.size());
        }
        
        try {
            test.add("test1");
            fail("Did not throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals(2, test.size());
        }
    }

    /**
     * Tests the get method of LinkedList.
     */
    @Test
    public void testGet() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        
        assertTrue(test.get(0).equals("test1"));
        assertTrue(test.get(1).equals("test2"));
        assertTrue(test.get(2).equals("test3"));
        
        try {
            test.get(-1);
            fail("Did not throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(3, test.size());
        }
        try {
            test.get(3);
            fail("Did not throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(3, test.size());
        }
    }
    
    /**
     * Tests the add method of LinkedList that accepts an
     * index parameter.
     */
    @Test
    public void testAddIdx() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
        test.add(0, "test1");
        assertEquals(1, test.size());
        assertTrue(test.get(0).equals("test1"));
        test.add(1, "test3");
        assertEquals(2, test.size());
        assertTrue(test.get(1).equals("test3"));
        test.add(1, "test2");
        assertEquals(3, test.size());
        assertTrue(test.get(1).equals("test2"));
        assertTrue(test.get(2).equals("test3"));
        test.add(0, "test4");
        assertEquals(4, test.size());
        assertTrue(test.get(0).equals("test4"));
        test.add(2, "test5");
        assertEquals(5, test.size());
        test.get(2).equals("test5");
        
        try {
            test.add(0, null);
            fail("Did not throw NullPointerException.");
        } catch (NullPointerException e) {
            assertEquals(5, test.size());
        }
        
        try {
            test.add(0, "test5");
            fail("Did not throw IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals(5, test.size());
        }
        
        try {
            test.add(6, "fail");
            fail("Did not throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(5, test.size());
        }
        
        try {
            test.add(-1, "fail");
            fail("Did not throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(5, test.size());
        }
        
    }
    
    /**
     * Tests the remove method of LinkedList.
     */
    @Test
    public void testRemove() {
        LinkedList test = new LinkedList();
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        assertTrue(test.add("test4"));
        assertEquals(4, test.size());
        assertTrue(test.add("test5"));
        assertEquals(5, test.size());
        
        try {
            test.remove(5);
            fail("Did not throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(5, test.size());
        }
        try {
            test.remove(-1);
            fail("Did not throw IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(5, test.size());
        }
      
        assertTrue(test.remove(4).equals("test5"));
        assertEquals(4, test.size());
        assertTrue(test.remove(2).equals("test3"));
        assertEquals(3, test.size());
        assertTrue(test.remove(2).equals("test4"));
        assertEquals(2, test.size());
        assertTrue(test.remove(0).equals("test1"));
        assertEquals(1, test.size());
        assertTrue(test.remove(0).equals("test2"));
        assertEquals(0, test.size());
    }
    
    /**
     * Tests the indexOf method of LinkedList.
     */
    @Test
    public void testIndexOf() {
        LinkedList test = new LinkedList();
        assertEquals(-1, test.indexOf("empty"));
        
        assertEquals(0, test.size());
        assertTrue(test.add("test1"));
        assertEquals(1, test.size());
        assertTrue(test.add("test2"));
        assertEquals(2, test.size());
        assertTrue(test.add("test3"));
        assertEquals(3, test.size());
        assertTrue(test.add("test4"));
        assertEquals(4, test.size());
        assertTrue(test.add("test5"));
        assertEquals(5, test.size());
        
        assertEquals(0, test.indexOf("test1"));
        assertEquals(1, test.indexOf("test2"));
        assertEquals(2, test.indexOf("test3"));
        assertEquals(3, test.indexOf("test4"));
        assertEquals(4, test.indexOf("test5"));
        assertEquals(-1, test.indexOf("not in list"));
    }
}
