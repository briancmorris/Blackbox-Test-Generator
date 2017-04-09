package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * A basic java object for holding a list of test cases
 * @author Brian and Nat
 *
 */
public class TestCaseList extends Observable implements Tabular, Serializable, Observer
{

	/** Serial version UID. */
	private static final long serialVersionUID = 98734509L;
	
	/**
	 * stores the name of the test case
	 */
	private String name;
	
	/**
	 * stores the next test case number
	 */
	private int nextTestCaseNum;
	
	/**
	 * stores the list ID
	 */
	private String testCaseListID;
	
	/**
	 * Constructs a test case list
	 * @param name of the list
	 * @param testCaseListID id of the list
	 */
	public TestCaseList(String name, String testCaseListID) 
	{
		this.name = name;
		this.testCaseListID = testCaseListID;
	}
	
	/**
	 * returns the list name
	 * @return the list name
	 */
	public String getName() 
	{
		return name;
	}


	/**
	 * sets the list name
	 * @param name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * returns the list ID
	 * @return the list ID
	 */
	public String getTestCaseListID() 
	{
		return testCaseListID;
	}

	/**
	 * sets the test case list ID
	 * @param testCaseListID ID to set
	 */
	private void setTestCaseListID(String testCaseListID) 
	{
		this.testCaseListID = testCaseListID;
	}

	/**
	 * returns the next test case number
	 * @return the next test case number
	 */
	private int getNextTestCaseNum() 
	{
		return nextTestCaseNum;
	}

	/**
	 * increases the next test case number
	 */
	private void incNextTestCaseNum() 
	{
		this.nextTestCaseNum++;
	}

	/**
	 * adds the test case to the list
	 * @param desc description of the test
	 * @param type type of the test
	 * @param creation date test was made
	 * @param exp expected results
	 * @param tested true if it has been tested
	 * @param lastTestDate last tested date
	 * @param act actual results
	 * @param pass true if it passed
	 * @return true if test case added
	 */
	public boolean addTestCase(String desc, TestingType type, Date creation, 
			                   String exp, boolean tested, Date lastTestDate,
			                   String act, boolean pass) 
	{
		return false;
	}
	
	/**
	 * returns the test case at the index
	 * @param index to retrieve
	 * @return the test case at the index
	 */
	public TestCase getTestCaseAt(int index) 
	{
		return null;
	}
	
	/**
	 * returns the index of the test case that matches the string
	 * @param testCaseString test case to look for
	 * @return the index of the test case that matches the string
	 */
	public int indexOf(String testCaseString) 
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
	 * returns true if list is empty
	 * @return true if list is empty
	 */
	public boolean isEmpty() 
	{
		return false;
	}
	
	/**
	 * removes the test case at the index
	 * @param index of test case
	 * @return the test case removed
	 */
	public TestCase removeTestCaseAt(int index) 
	{
		return null;
	}
	
	/**
	 * removes the test case that matches the string
	 * @param testCaseString test case to remove
	 * @return true if removed
	 */
	public boolean removeTestCase(String testCaseString) 
	{
		return false;
	}
	
	@Override
	public Object[][] get2DArray() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
