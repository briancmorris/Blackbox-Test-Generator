package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;
import java.sql.Date;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.ncsu.csc216.bbtp.model.TestingType;
import edu.ncsu.csc216.bbtp.model.TestingTypeList;

/**
 * GUI for the edit panel
 * @author Brian Morris
 * @author Nat Ellis
 *
 */
public class TestCaseEditPane extends JScrollPane implements Serializable, Observer
{
	/** Serial version UID. */
	private static final long serialVersionUID = 5479139338455751629L;

	/**
	 * holds the testing types list
	 */
	private TestingTypeList testingTypes;
	/**
	 * holds test id box
	 */
	private JTextField testCaseID;
	/**
	 * holds the testing type box object
	 */
	private JComboBox<TestingType> tcTestingType;
	/**
	 * holds the expected results box object
	 */
	private JTextArea expectedResults;
	/**
	 * holds the actual results box object
	 */
	private JTextArea actualResults;
	/**
	 * holds the description box object
	 */
	private JTextArea testCaseDescription;
	/**
	 * holds the creation date object
	 */
	private JSpinner testCreationDate;
	/**
	 * holds the last tested date object
	 */
	private JSpinner testLastTestedDate;
	/**
	 * holds the tested check box object
	 */
	private JCheckBox tested;
	/**
	 * holds the pass check box object
	 */
	private JCheckBox pass;
	/**
	 * holds boolean for if its an addition
	 */
	private boolean add;
	/**
	 * holds boolean for if its an edit
	 */
	private boolean edit;
	/**
	 * holds the test case data
	 */
	private TestCaseData data;
	
	
	/**
	 * basic edit panel
	 * @param list to display
	 */
	public TestCaseEditPane(TestingTypeList list)
	{
		
	}

	/**
	 * constructor for a panel with inputed data
	 * @param data to add
	 * @param list to display
	 */
	public TestCaseEditPane(TestCaseData data, TestingTypeList list)
	{
		
	}
	
	/**
	 * Initializes the panel
	 */
	private void init()
	{
		
	}
	
	/**
	 * Initializes the view
	 */
	private void initView()
	{
		
	}
	
	/**
	 * return the creation date spinner
	 * @return the testCreationDate
	 */
	JSpinner getTestCreationDateSpinner() {
		return testCreationDate;
	}
	
	/**
	 * return the creation date
	 * @return the testCreationDate
	 */
	Date getTestCreationDate() {
		return null;
	}

	/**
	 * get last tested date spinner
	 * @return the testLastTestedDate spinner
	 */
	JSpinner getLastTestedDateSpinner() {
		return testLastTestedDate;
	}
	
	/**
	 * returns the creation date
	 * @return the testCreationDate
	 */
	Date getLastTestedDate() {
		return null;
	}
	

	/**
	 * returns the testing types
	 * @return the testingTypes
	 */
	TestingTypeList getTestingTypes() {
		return testingTypes;
	}

	/**
	 * returns the case ID
	 * @return the testCaseID
	 */
	JTextField getTestCaseID() {
		return testCaseID;
	}

	/**
	 * returns the expected results
	 * @return the expectedResults
	 */
	JTextArea getExpectedResults() {
		return expectedResults;
	}

	/**
	 * returns the actual results
	 * @return the actualResults
	 */
	JTextArea getActualResults() {
		return actualResults;
	}

	/**
	 * returns the description
	 * @return the testCaseDescription
	 */
	JTextArea getTestCaseDescription() {
		return testCaseDescription;
	}

	/**
	 * returns the value of pass
	 * @return the pass
	 */
	JCheckBox pass() {
		return pass;
	}

	/**
	 * returns true if tested
	 * @return the tested
	 */
	JCheckBox getTested() {
		return tested;
	}

	/**
	 * sets the creation date
	 * @param date the date to set
	 */
	void setCreationDate(Date date) {
		
	}

	/**
	 * sets the tested date
	 * @param date the date to set
	 */
	void setLastTestedDate(Date date) {
		
	}

	/**
	 * retursn true if added
	 * @return the add
	 */
	boolean isAdd() {
		return add;
	}

	/**
	 * returns true if edited
	 * @return the edit
	 */
	boolean isEdit() {
		return edit;
	}
	
	/**
	 * enables add mode
	 */
	void enableAdd() {

	}
	
	/**
	 * disables add mode
	 */
	void disableAdd() {

	}
	
	/**
	 * enables edit mode
	 * @param data to edit
	 */
	void enableEdit(TestCaseData data) {

	}
	
	/**
	 * disables edit mode
	 */
	void disableEdit() {

	}
	
	/**
	 * returns true if the field is not empty
	 * @return true if the field is not empty
	 */
	boolean fieldsNotEmpty()
	{
		return false;
	}
	
	/**
	 * sets the test case data
	 * @param data to set
	 */
	void setTestCaseData(TestCaseData data)
	{
		
	}
	
	/**
	 * adds a listener
	 * @param listener to add
	 */
	void addFieldListener(EventListener listener)
	{
		
	}
	
	/**
	 * fills the field
	 */
	void fillFields()
	{
		
	}
	
	/**
	 * clears the field
	 */
	void clearFields()
	{
		
	}
	
	/**
	 * returns the field data
	 * @return the field data
	 */
	TestCaseData getFields()
	{
		return null;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
