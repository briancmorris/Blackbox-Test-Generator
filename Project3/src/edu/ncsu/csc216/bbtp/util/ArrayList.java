package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * personalized array list
 * @author Brian and Nat
 *
 */
public class ArrayList implements List, Serializable
{
	
	/** Serial version UID. */
	private static final long serialVersionUID = 28592L;

	private static final int RESIZE = 0;
	
	private Object[] list;
	
	private int size;
	
	/**
	 * basic constructor for the array list
	 */
	public ArrayList()
	{
		
	}
	
	/**
	 * constructor with default size
	 * @param size of the new list
	 */
	public ArrayList(int size)
	{
		
	}
	
	@Override
	public int size() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return false;
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
