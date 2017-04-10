package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;

import edu.ncsu.csc216.bbtp.model.TestCaseList;

/**
 * GUI for the list panel
 * @author Brian and Nat
 *
 */
public class TestCaseListPane implements Serializable, Observer
{
	
	/** Serial version UID. */
	private static final long serialVersionUID = -2210716111020406799L;
	/**
	 * holds the test cases
	 */
	private TestCaseList testCases;
	/**
	 * holds the table GUI
	 */
	private JTable table;
	/**
	 * holds the column width
	 */
	private int[] colWidth;
	
	/**
	 * constructor for the list panel
	 * @param testLists to display
	 */
	public TestCaseListPane(TestCaseList testLists)
	{
		
	}
	
	/**
	 * returns the table
	 * @return the table
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * initilizes the GUI
	 */
	private void initView()
	{
		
	}
	
	/**
	 * clears the selection
	 */
	public void clearSelection()
	{
		
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
}
