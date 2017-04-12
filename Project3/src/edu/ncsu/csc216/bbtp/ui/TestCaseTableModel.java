package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;

/**
 * GUI for the Model
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestCaseTableModel implements Serializable
{
	/** Serial version UID. */
	private static final long serialVersionUID = 5954551753060998701L;
	
	private String[] colNames;
	
	private Object[][] data;
	
	/**
	 * constructor for the table model
	 * @param data to add to the model
	 */
	public TestCaseTableModel(Object[][] data)
	{
		
	}
	
	/**
	 * returns the row count
	 * @return the row count
	 */
	public int getRowCount()
	{
		return 0;
	}
	
	/**
	 * returns the column count
	 * @return number of columns
	 */
	public int getColumnCount()
	{
		return 0;
	}
	
	//TODO improve this Javadoc
	/**
	 * Enter description.
	 * @param index something
	 * @return something
	 */
	public String getColumnName(int index)
	{
		return null;
	}
	
	/**
	 * returns the value at the row and column given
	 * @param row to get
	 * @param col to get
	 * @return the object at the given cords
	 */
	public Object getValueAt(int row, int col)
	{
		return null;
	}
	
	/**
	 * sets the value at the index and column
	 * @param object to set
	 * @param row to set
	 * @param col to set
	 */
	public void setValueAt(Object object, int row, int col)
	{
		
	}
	
	/**
	 * returns the data of the given row
	 * @param row to retrieve
	 * @return the data of the given row
	 */
	public TestCaseData getTestCaseRowData(int row)
	{
		return null;
	}
	
	/**
	 * sets the given row to the given data
	 * @param row to set
	 * @param data to set
	 */
	public void setTaskRowData(int row, TestCaseData data)
	{
		
	}
	
}
