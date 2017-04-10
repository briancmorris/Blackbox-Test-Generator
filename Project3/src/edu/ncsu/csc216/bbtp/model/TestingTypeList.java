package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * A basic java object for holding a list of types
 * @author Brian and Nat
 *
 */
public class TestingTypeList extends Observable implements Tabular, Serializable, Observer
{

	/** Serial version UID. */
	private static final long serialVersionUID = 984509L;
	
	private String name;
	
	private int nextTestingTypeNum;
	
	/**
	 * constructor for the type list
	 */
	public TestingTypeList()
	{
		
	}
	
	/**
	 * return the name of the list
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * adds the type to the list
	 * @param name of the type
	 * @param description of the type
	 * @return true of added
	 */
	public boolean addTestingType(String name, String description)
	{
		return false;
	}
	
	/**
	 * returns the type at the index
	 * @param index to retrieve
	 * @return the type at the index
	 */
	public TestingType getTestingTypeAt(int index)
	{
		return null;
	}
	
	/**
	 * return the index of the type provided
	 * @param input the type
	 * @return the index
	 */
	public int indexOf(String input)
	{
		return 0;
	}

	/**
	 * returns the index of the type with name provided
	 * @param name of the type
	 * @return the type
	 */
	public int indexOfName(String name)
	{
		return 0;
	}
	
	/**
	 * returns the size
	 * @return the size
	 */
	public int size()
	{
		return 0;
	}
	
	/**
	 * returns true if its empty
	 * @return true if its empty
	 */
	public boolean isEmpty()
	{
		return false;
	}
	
	/**
	 * removes the testing type
	 * @param index to remove
	 * @return the type removed
	 */
	public TestingType removeTestingTypeAt(int index)
	{
		return null;
	}
	
	/**
	 * removes the testing type
	 * @param input type to remove
	 * @return true if removed
	 */
	public boolean removeTestingType(String input)
	{
		return false;
	}
	
	/**
	 * returns the next testing type number
	 * @return
	 */
	private int getNextTestingTypeNum()
	{
		return 0;
	}
	
	/**
	 * increases the next testing type number
	 */
	private void incNextTestingTypeNum()
	{
		nextTestingTypeNum++;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[][] get2DArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
