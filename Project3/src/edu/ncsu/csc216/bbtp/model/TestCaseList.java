package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.LinkedList;

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
	private int nextTestCaseNum = 1;
	
	/**
	 * stores the list ID
	 */
	private String testCaseListID;
	
	/**
	 * holds the list of cases
	 */
	private LinkedList list;
	
	/**
	 * Constructs a test case list
	 * @param name of the list
	 * @param testCaseListID id of the list
	 */
	public TestCaseList(String name, String testCaseListID) 
	{
		setName(name); 
		setTestCaseListID(testCaseListID);
		nextTestCaseNum = 1;
		list = new LinkedList();
		
		setChanged();
        notifyObservers(this);
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
		if (!(name.trim().length() > 0) || name == null)
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
		setChanged();
        notifyObservers(this);
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
		if (!(testCaseListID.trim().length() > 0) || testCaseListID == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.testCaseListID = testCaseListID;
		setChanged();
        notifyObservers(this);
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
		try
		{
			TestCase newTestCase = new TestCase(getTestCaseListID() + "-TC" + getNextTestCaseNum(), desc, type,
												creation, exp, tested, lastTestDate, act, pass);
			newTestCase.addObserver(this);
			
			if (list.size() == 0)
			{
				list.add(newTestCase);
			}
			else 
			{
				int indexToAdd = 0;
				TestCase compare = (TestCase) list.get(0);
				for(int i = 0; i < list.size(); i++)
				{
					compare = (TestCase) list.get(i);
					if(compare.compareTo(newTestCase) >= 0) {
					    indexToAdd++;
					}
//					if(newTestCase.getLastTestedDateTime() == null || compare.getLastTestedDateTime() == null)
//					{
//						indexToAdd = i + 1;
//					}
//					else if (compare.compareTo(newTestCase) == 1)
//					{
//						indexToAdd = i + 1;
//					}
//					else if(compare.compareTo(newTestCase) == 0)
//					{
//						indexToAdd = i + 1;
//					}
				}
				list.add(indexToAdd, newTestCase);
			}
			
			incNextTestCaseNum();
			setChanged();
	        notifyObservers(this);
			return true;
		}
		catch (IllegalArgumentException e) 
		{
			return false;
		}
		
	}
	
	/**
	 * returns the test case at the index
	 * @param index to retrieve
	 * @return the test case at the index
	 */
	public TestCase getTestCaseAt(int index) 
	{
		if (index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		return (TestCase) list.get(index);
	}
	
	/**
	 * returns the index of the test case that matches the string
	 * @param testCaseID test case to look for
	 * @return the index of the test case that matches the string
	 */
	public int indexOf(String testCaseID) 
	{
		int out = -1;
		TestCase compare;
		for (int i = 0; i < list.size(); i++)
		{
			compare = (TestCase) list.get(i);
			if (compare.getTestCaseID().equals(testCaseID))
			{
				out = i;
			}
		}
		return out;
	}
	
	/** 
	 * returns the size
	 * @return the size
	 */
	public int size() 
	{
		return list.size();
	}
	
	/**
	 * returns true if list is empty
	 * @return true if list is empty
	 */
	public boolean isEmpty() 
	{
		return list.size() == 0;
	}
	
	/**
	 * removes the test case at the index
	 * @param index of test case
	 * @return the test case removed
	 */
	public TestCase removeTestCaseAt(int index) 
	{
		if (index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		TestCase out = (TestCase) list.remove(index);
		out.deleteObserver(this);
		setChanged();
        notifyObservers(this);
		return out;
	}
	
	/**
	 * removes the test case that matches the string
	 * @param testCaseID test case to remove
	 * @return true if removed
	 */
	public boolean removeTestCase(String testCaseID) 
	{
		int index = indexOf(testCaseID);
		if (index != -1)
		{
			TestCase out = removeTestCaseAt(index);
			out.deleteObserver(this);
			setChanged();
	        notifyObservers(this);
			return true;
		}
		return false;
	}
	
	@Override
	public Object[][] get2DArray() 
	{
		Object[][] out = new Object[list.size()][9];
		for (int i = 0 ; i < list.size(); i++)
		{
			TestCase next = (TestCase) list.get(i);
			out[i][0] = next.getTestCaseID();
			out[i][1] = next.getDescription();
			out[i][2] = next.getTestingType();
			out[i][3] = next.getCreationDateTime();
			out[i][4] = next.getLastTestedDateTime();
			out[i][5] = next.tested();
			out[i][6] = next.getExpectedResults();
			out[i][7] = next.getActualResults();
			out[i][8] = next.pass();
		}
		
		return out;
	}

	@Override
	public void update(Observable o, Object arg) {
	    if (list.contains(o)) {
	        notifyObservers(arg);
	    }
	}

}
