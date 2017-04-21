package edu.ncsu.csc216.bbtp.ui;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.bbtp.model.TestCaseList;

/**
 * Maintains the list of TestCases.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestCaseListPane extends JScrollPane implements Serializable, Observer {
	
	/** Serial version UID */
	private static final long serialVersionUID = -2210716111020406799L;
	/** List of TestCases to display */
	private TestCaseList testCaseList;
	/** TestCaseTableModel which displays the list of TestCases */
	private TestCaseTableModel testCaseTableModel;
	/** Table for the TestCases */
	private JTable table;
	/** Widths of columns */
	private int[] colWidths = { 50, 500, 250, 300, 300, 250, 250, 250, 200};
	
	/**
     * Creates the TestCaseListPane that shows the TestCaseData in a
     * table.
     * @param testCaseList TestCaseList of TestCase objects
     */
    public TestCaseListPane(TestCaseList testCaseList) {
        super();
        this.testCaseList = testCaseList;
        this.testCaseList.addObserver(this);
        this.testCaseTableModel = new TestCaseTableModel(testCaseList.get2DArray());
        initView();
    }
	
    /**
     * Returns the TestCaseTableModel.
     * @return the TestCaseTableModel
     */
    public TestCaseTableModel getTestCaseTableModel() {
        return testCaseTableModel;
    }
	
	
    /**
     * Returns the JTable.
     * @return the JTable
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Initializes the view by creating the JTable (that is wrapped in
     * JScrollPane) and associating the JTable with the TestCaseTableModel.
     */
    private void initView() {
        // Associates the TestCaseTableModel with the JTable.
        // The TestingTypeTableModel contains the data that the JTable will
        // display.
        table = new JTable(testCaseTableModel);
        // Set up the column widths so the table will look nice.
        for (int i = 0; i < colWidths.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(colWidths[i]);
        }
        // Set the table so that only one row can be selected at a time.
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(false);
        setViewportView(table);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
	
	/**
     * Clears the selection.
     */
    public void clearSelection() {
        table.clearSelection();
    }

	/**
     * This method is called by the observed object, whenever the observed
     * object is changed. In this case, the observed object is the TestCaseList.
     * Any changes to the TestCaseList will lead to an update of the
     * TestCaseTableModel.
     * @param o the observable object
     * @param arg any additional information needed about the change.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TestCaseList) {
            TestCaseList tcl = (TestCaseList) o;
            // If there is a different number of rows, create a show new
            // TestCaseTableModel.
            if (tcl.size() != testCaseTableModel.getRowCount()) {
                testCaseTableModel = new TestCaseTableModel(tcl.get2DArray());
                table.setModel(testCaseTableModel);
            } else {
                // Otherwise, just update the values directly.
                Object[][] arr = tcl.get2DArray();
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < testCaseTableModel.getColumnCount(); j++) {
                        testCaseTableModel.setValueAt(arr[i][j], i, j);
                    }
                }
            }
        }
    }
}
