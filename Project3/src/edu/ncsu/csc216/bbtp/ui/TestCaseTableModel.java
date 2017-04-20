package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.bbtp.model.TestingType;

/**
 * GUI for the Model
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestCaseTableModel extends AbstractTableModel implements Serializable
{
	/** Serial version UID. */
	private static final long serialVersionUID = 5954551753060998701L;
	
    private String[] colNames = { "ID", "Description", "Test Type", "Creation Date",
                                  "Last Tested Date", "Tested?", "Expected Results", "Actual Results", "Pass?" };
	
	private Object[][] data;
	
	/**
	 * constructor for the table model
	 * @param data to add to the model
	 */
	public TestCaseTableModel(Object[][] data)
	{
		super();
        this.data = data;
	}
	
	/**
	 * returns the row count
	 * @return the row count
	 */
	public int getRowCount()
	{
		return data.length;
	}
	
	/**
	 * returns the column count
	 * @return number of columns
	 */
	public int getColumnCount()
	{
		return colNames.length;
	}
	
	/**
	 * returns the name of the column at the index
	 * @param index to return
	 * @return the column name
	 */
	public String getColumnName(int index)
	{
		return colNames[index];
	}
	
	/**
	 * returns the value at the row and column given
	 * @param row to get
	 * @param col to get
	 * @return the object at the given cords
	 */
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}
	
	/**
	 * sets the value at the index and column
	 * @param object to set
	 * @param row to set
	 * @param col to set
	 */
	public void setValueAt(Object object, int row, int col)
	{
		data[row][col] = object;
        fireTableCellUpdated(row, col);
	}
	
	/**
	 * returns the data of the given row
	 * @param row to retrieve
	 * @return the data of the given row
	 */
	public TestCaseData getTestCaseRowData(int row)
	{
		
		String testCaseID = (String) data[row][0];
		String description = (String) data[row][1];
		TestingType testingType = (TestingType) data[row][2];
		Date creationDateTime = (Date) data[row][3];
        Date lastTestedDateTime = (Date) data[row][4];
        boolean testedStatusPass = (boolean) data[row][5];
        String expectedResults = (String) data[row][6];
        String actualResults = (String) data[row][7];
        boolean pass = (boolean) data[row][8];
		
		TestCaseData testCase = new TestCaseData(testCaseID, description, testingType, creationDateTime,
												lastTestedDateTime, testedStatusPass, expectedResults,
												actualResults, pass);		
		return testCase;
	}
	
	/**
	 * sets the given row to the given data
	 * @param row to set
	 * @param data to set
	 */
	public void setTaskRowData(int row, TestCaseData data)
	{
		setValueAt(data.getTestCaseID(), row, 0);
		setValueAt(data.getDescription(), row, 1);
		setValueAt(data.getTestingType().getName(), row, 2);
		setValueAt(data.getCreationDateTime(), row, 3);
		setValueAt(data.getExpectedResults(), row, 4);
		setValueAt(data.tested(), row, 5);
		setValueAt(data.getActualResults(), row, 6);
		setValueAt(data.pass(), row, 7);
		setValueAt(data.pass(), row, 8);
	}
	
}
