package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * The ArrayList class stores provided objects in an array, with
 * various getter and setter methods to improve ease of use.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class ArrayList implements List, Serializable {
	
	/** Serial Version UID used to identify this object when performing IO operations */
	private static final long serialVersionUID = 28592L;
	/** Constant value used to resize a list */
	private static final int RESIZE = 0;
	/** The array used to store values in this ArrayList */
	private Object[] list;
	/** The number of elements in the list */
	private int size;
	
	/**
	 * The constructor for ArrayList initializes an empty list with
	 * size 0.
	 */
	public ArrayList() {
	    this.size = RESIZE;
	    this.list = new Object[10];
	}
	
	/**
	 * constructor with default size
	 * @param size of the new list
	 */
	public ArrayList(int size) {
		
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int index) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object element) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(int index) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
