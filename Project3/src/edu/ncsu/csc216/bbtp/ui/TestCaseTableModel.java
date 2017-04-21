package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.bbtp.model.TestingType;

/**
 * TestCaseTableModel is a wrapper for the information in TestCaseList that can
 * be used by a JTable.
 * 
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestCaseTableModel extends AbstractTableModel implements Serializable {
    /** Serial version UID */
    private static final long serialVersionUID = 5954551753060998701L;
    /** The names of the columns in the TableModel */
    private String[] colNames = { "ID", "Description", "Test Type", "Creation Date",
            "Last Tested Date", "Tested?", "Expected Results", "Actual Results", "Pass?" };
    /** The 2D array of TestCaseList information */
    private Object[][] data;

    /**
     * Creates the model from the given data.
     * 
     * @param data the data to populate the TableModel
     */
    public TestCaseTableModel(Object[][] data) {
        super();
        this.data = data;
    }

    /**
     * Returns the number of rows in the data.
     * 
     * @return the number of rows in the data
     */
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the data.
     * 
     * @return the number of columns in the data
     */
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * Returns the column name at the given index.
     * 
     * @param index the index for finding the column name
     * @return the column name at the given index
     */
    public String getColumnName(int index) {
        return colNames[index];
    }

    /**
     * Returns the value at the given cell in the TableModel.
     * 
     * @param row the index for the row
     * @param col the index for the column
     * @return the value in the data at the given row and col
     */
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /**
     * Sets the Object to the given cell in the TableModel.
     * 
     * @param object the Object to set
     * @param row the index for the row
     * @param col the index for the column
     */
    public void setValueAt(Object object, int row, int col) {
        data[row][col] = object;
        fireTableCellUpdated(row, col);
    }

    /**
     * Returns the TestCaseData object associated with the given row in the
     * TableModel.
     * 
     * @param row the TestCaseData to return
     * @return the TestCaseData for the given row
     */
    public TestCaseData getTestCaseRowData(int row) {

        String testCaseID = (String) data[row][0];
        String description = (String) data[row][1];
        TestingType testingType = (TestingType) data[row][2];
        Date creationDateTime = (Date) data[row][3];
        Date lastTestedDateTime = (Date) data[row][4];
        boolean testedStatusPass = (boolean) data[row][5];
        String expectedResults = (String) data[row][6];
        String actualResults = (String) data[row][7];
        boolean pass = (boolean) data[row][8];

        TestCaseData testCase = new TestCaseData(testCaseID, description, testingType,
                creationDateTime, lastTestedDateTime, testedStatusPass, expectedResults,
                actualResults, pass);
        return testCase;
    }

    /**
     * Sets the given row with the provided TestCaseData.
     * 
     * @param data TestCaseData to set in the row
     * @param row the row to set
     */
    public void setTaskRowData(int row, TestCaseData data) {
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
