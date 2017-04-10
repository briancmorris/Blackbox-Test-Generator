package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * personalized array list
 * @author Brian and Nat
 *
 */
public class LinkedList implements List, Serializable
{

	
	/** Serial version UID. */
	private static final long serialVersionUID = 349987L;

	/**
	 * basic constructor for linked list
	 */
	public LinkedList()
	{
		
	}
	
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * private class for a node to use in linked list
	 * @author Brian and Nat
	 *
	 */
	public class Node implements Serializable
	{
		/** Serial version UID. */
		private static final long serialVersionUID = 484909840L;
		
		Object value;
		
		/**
		 * basic node constructor
		 * @param item to store
		 * @param next node
		 */
		public Node(Object item, Node next)
		{
			
		}
		
	}
}
