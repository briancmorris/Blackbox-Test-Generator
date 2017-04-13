package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * The ArrayList class stores provided Objects in an array, with
 * various getter and setter methods to improve ease of use.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class ArrayList implements List, Serializable {
	
	/** Serial Version UID used to identify an instance of this class when performing IO operations */
	private static final long serialVersionUID = 28592L;
	/** Constant value used to resize a list */
	private static final int RESIZE = 10;
	/** The array used to store values in this ArrayList */
	private Object[] list;
	/** The number of Objects in the list */
	private int size;
	
	/**
	 * The constructor for ArrayList initializes an empty list with
	 * size 0.
	 */
	public ArrayList() {
	    this.size = 0;
	    this.list = new Object[RESIZE];
	}
	
	/**
	 * The constructor for ArrayList initializes an empty list with size 0.
	 * @param initialSize the initial storage capacity of the underlying array used
	 *        to store objects.
	 */
	public ArrayList(int initialSize) {
		this.list = new Object[initialSize];
	}
	
	/**
	 * Returns the number of Objects contained within this ArrayList.
	 * @return the number of Objects contained within this ArrayList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this ArrayList contains no Objects, false otherwise.
	 * @return true if this ArrayList contains no Objects, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns true if this ArrayList already contains the given Object, false otherwise.
	 * @param o the given Object in question
	 * @return true if this ArrayList already contains the given Object, false otherwise
	 */
	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
		    if (list[i].equals(o)) {
		        return true;
		    }
		}
		return false;
	}

	/**
	 * Adds the given object to the end of this ArrayList and returns true if it was added successfully.
	 * If the given Object is null, a NullPointerException is thrown. If the given Object is already
	 * contained in this ArrayList, an IllegalArgumentException is thrown.
	 * @param o the Object to add to the ArrayList
	 * @return true if the Object was added successfully
	 * @throws NullPointerException if the Object to add is null
	 * @throws IllegalArgumentException if the Object provided is already contained in this ArrayList
	 */
	@Override
	public boolean add(Object o) {
	    if (o == null) {
	        throw new NullPointerException();
	    } else if (contains(o)) {
	        throw new IllegalArgumentException();
	    }
	    
	    if (size + 1 == list.length) {
	        growArray();
	    }
	    list[size] = o;
	    size++;
		return true;
	}

	/**
	 * Returns the Object that is located at the given index
	 * in this ArrayList. If the given index is less than 0, or
	 * greater than or equal to the size of the ArrayList, an
	 * IndexOutOfBoundsException is thrown.
	 * @param index the index of the Object in this ArrayList
	 * @return the Object retrieved at the given index
	 * @throws IndexOutOfBoundsException if the given index is less than 0
	 *         or greater than or equal to the size of the ArrayList.
	 */
    @Override
	public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
		return list[index];
	}

    /**
     * Inserts the given Object at the provided index in this ArrayList. If the given Object is null,
     * a NullPointerException is thrown. If the given Object is already contained in this ArrayList,
     * an IllegalArgumentException is thrown. If the given index is less than 0 or greater than the
     * size of the ArrayList, an IndexOutOfBoundsException is thrown.
     * @param index the index at which the specified Object is to be inserted
     * @param element the Object to be inserted
     * @throws NullPointerException if the given Object is null
     * @throws IllegalArgumentException if the given Object is already contained in this ArrayList
     * @throws IndexOutOfBoundsException if the given index is less than 0 or greater than the size
     *         of this list
     */
	@Override
	public void add(int index, Object element) {
		if (element == null) {
		    throw new NullPointerException();
		} else if (contains(element)) {
		    throw new IllegalArgumentException();
		} else if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
		if (size + 1 == list.length) {
		    growArray();
		}
		for (int i = size; i > index; i--) {
		    list[i] = list[i - 1];
		}
		list[index] = element;
		size++;
	}

	/**
	 * Removes the given Object located at the provided index in this ArrayList and returns the Object
	 * that was removed. If the given index is less than 0 or greater than or equal to the size
	 * of the ArrayList, an IndexOutOfBoundsException is thrown.
	 * @param index the index of the Object to remove
	 * @return the Object that was removed
	 * @throws IndexOutOfBoundsException if the given index is less than 0 or greater than or equal to
	 *         the size of the ArrayList
	 */
	@Override
	public Object remove(int index) {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
		Object element = list[index];
		for (int i = index; i < size; i++) {
		    list[i] = list [i + 1];
		}
		size--;
		return element;
	}

	/**
	 * Returns the index of the first occurrence of the specified Object in
     * this ArrayList, or -1 if this ArrayList does not contain the Object.
     * @param o the Object to search for
     * @return the index of the first occurrence of the specified Object in
     *         this ArrayList, or -1 if this ArrayList does not contain the Object
	 */
	@Override
	public int indexOf(Object o) {
	    for (int i = 0; i < size; i++) {
	        if (list[i].equals(o)) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	/**
	 * Increases the storage capacity of the list array when required by the add methods.
	 */
    private void growArray() {
        Object[] newList = new Object[list.length * RESIZE];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
}
