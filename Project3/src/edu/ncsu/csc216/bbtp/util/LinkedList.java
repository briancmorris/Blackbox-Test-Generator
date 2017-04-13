package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * The LinkedList class stores provided Objects in Nodes, with various getter
 * and setter methods to improve ease of use.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class LinkedList implements List, Serializable {
	
	/** Serial Version UID used to identify an instance of this class when performing IO operations */
	private static final long serialVersionUID = 349987L;
	/** The number of Objects in the LinkedList */
	private int size;
	/** The front Node of the LinkedList */
	private Node front;
	/** The back Node of the LinkedList */
	private Node back;

	/**
	 * The constructor for LinkedList initializes an empty LinkedList with
     * size 0.
	 */
	public LinkedList() {
	    this.size = 0;
	    this.front = null;
	    this.back = null;
		
	}

    /**
     * Returns the number of Objects contained within this LinkedList.
     * @return the number of Objects contained within this LinkedList
     */
	@Override
	public int size() {
		return size;
	}

	/**
     * Returns true if this LinkedList contains no Objects, false otherwise.
     * @return true if this LinkedList contains no Objects, false otherwise.
     */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
     * Returns true if this LinkedList already contains the given Object, false otherwise.
     * @param o the given Object in question
     * @return true if this LinkedList already contains the given Object, false otherwise
     */
	@Override
	public boolean contains(Object o) {
		Node current = front;
		while (current != null && current.next != null) {
		    if (current.value.equals(o)) {
		        return true;
		    }
		    current = front.next;
		}
		return false;
	}

	/**
     * Adds the given object to the end of this LinkedList and returns true if it was added successfully.
     * If the given Object is null, a NullPointerException is thrown. If the given Object is already
     * contained in this LinkedList, an IllegalArgumentException is thrown.
     * @param o the Object to add to the LinkedList
     * @return true if the Object was added successfully
     * @throws NullPointerException if the Object to add is null
     * @throws IllegalArgumentException if the Object provided is already contained in this LinkedList
     */
	@Override
	public boolean add(Object o) {
	    if(o == null) {
	        throw new NullPointerException();
	    } else if (contains(o)) {
	        throw new IllegalArgumentException();
	    }
		Node toAdd = new Node(o, null);
		if (size == 0) {
		    front = toAdd;
		    back = toAdd;
		} else {
		    back.next = toAdd;
		    back = back.next;
		}
		size++;
		return true;
	}

	/**
     * Returns the Object that is located at the given index
     * in this LinkedList. If the given index is less than 0, or
     * greater than or equal to the size of the LinkedList, an
     * IndexOutOfBoundsException is thrown.
     * @param index the index of the Object in this LinkedList
     * @return the Object retrieved at the given index
     * @throws IndexOutOfBoundsException if the given index is less than 0
     *         or greater than or equal to the size of the LinkedList.
     */
	@Override
	public Object get(int index) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    if (index == 0) {
	        return front.value;
	    } else if (index == size - 1) {
	        return back.value;
	    } else {
	        Node current = front;
	        for (int i = 0; i < index; i++) {
	            current = front.next;
	        }
	        return current.value;
	    }
	}

	/**
     * Inserts the given Object at the provided index in this LinkedList. If the given Object is null,
     * a NullPointerException is thrown. If the given Object is already contained in this LinkedList,
     * an IllegalArgumentException is thrown. If the given index is less than 0 or greater than the
     * size of the LinkedList, an IndexOutOfBoundsException is thrown.
     * @param index the index at which the specified Object is to be inserted
     * @param element the Object to be inserted
     * @throws NullPointerException if the given Object is null
     * @throws IllegalArgumentException if the given Object is already contained in this LinkedList
     * @throws IndexOutOfBoundsException if the given index is less than 0 or greater than the size
     *         of this LinkedList
     */
	@Override
	public void add(int index, Object element) {
	    if (element == null) {
	        throw new NullPointerException();
	    } else if (contains(element)) {
	        throw new IllegalArgumentException();
	    } else if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException();
	    }
	    if (index == 0) {
	        front = new Node(element, front);
	        if (size == 0) {
	            back = front;
	        }
	    } else if (index == size) {
	        back.next = new Node(element, null);
	        back = back.next;
	    } else {
	        Node current = front;
	        for (int i = 0; i < index - 1; i++) {
	            current = current.next;
	        }
	        current.next = new Node(element, current.next.next);
	    }
	    size++;
	}

	/**
     * Removes the given Object located at the provided index in this LinkedList and returns the Object
     * that was removed. If the given index is less than 0 or greater than or equal to the size
     * of the LinkedList, an IndexOutOfBoundsException is thrown.
     * @param index the index of the Object to remove
     * @return the Object that was removed
     * @throws IndexOutOfBoundsException if the given index is less than 0 or greater than or equal to
     *         the size of the LinkedList
     */
	@Override
	public Object remove(int index) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    Object retrieved = null;
	    if (index == 0) {
	        retrieved = front.value;
	        front = front.next;
	    } else {
	        Node current = front;
	        for (int i = 0; i < index - 1; i++) {
	            current = current.next;
	        }
	        retrieved = current.next.value;
	        current.next = current.next.next;
	        if(index == size - 1) {
	            back = current;
	        }
	    }
	    size--;
		return retrieved;
	}
	
	/**
     * Returns the index of the first occurrence of the specified Object in
     * this LinkedList, or -1 if this LinkedList does not contain the Object.
     * @param o the Object to search for
     * @return the index of the first occurrence of the specified Object in
     *         this LinkedList, or -1 if this LinkedList does not contain the Object
     */
	@Override
	public int indexOf(Object o) {
		Node current = front;
		int counter = 0;
	    while(front != null && front.next != null) {
	        if (current.value.equals(o)) {
	            return counter;
	        }
	        current = front.next;
	        counter++;
	    }
		return -1;
	}
	
	/**
	 * The Node class stores an Object and a reference to the next Node in the LinkedList.
	 * @author Brian Morris
	 * @author Nat Ellis
	 */
	private class Node implements Serializable {
		/** Serial Version UID used to identify an instance of this class when performing IO operations */
		private static final long serialVersionUID = 484909840L;
		/** The Object stored within this Node */
		private Object value;
		/** The next Node in the LinkedList */
		private Node next;
		
		/**
		 * The Node constructor creates a Node Object with the data provided and
		 * a reference to the next Node in the LinkedList.
		 * @param value the Object to store
		 * @param next the next Node in the LinkedList
		 */
		public Node(Object value, Node next) {
		    this.value = value;
		    this.next = next;
		}
	}
}
