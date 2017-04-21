package edu.ncsu.csc216.bbtp.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.Date;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.bbtp.model.TestingType;
import edu.ncsu.csc216.bbtp.model.TestingTypeList;

/**
 * GUI panel for editing a TestCase.
 * 
 * @author Brian Morris
 * @author Nat Ellis
 *
 */
public class TestCaseEditPane extends JPanel implements Serializable, Observer {
    /** Serial version UID */
    private static final long serialVersionUID = 5479139338455751629L;

    /** The TestingTypeList used to create a TestCase */
    private TestingTypeList testingTypes;
    /** Text field for the TestCase ID */
    private JTextField testCaseID;
    /** Drop down menu for TestingTypes */
    private JComboBox<TestingType> tcTestingType;
    /** Text area for expected results of the TestCase */
    private JTextArea expectedResults;
    /** Text area for the actual results of the TestCase */
    private JTextArea actualResults;
    /** Text area for the TestCase description */
    private JTextArea testCaseDescription;
    /** JSpinner that maintains the creation date */
    private JSpinner testCreationDate;
    /** JSpinner that maintains the last testing date */
    private JSpinner testLastTestedDate;
    /** Check box used to indicate if a TestCase has been tested */
    private JCheckBox tested;
    /** Check box used to indicate if a TestCase has passed testing */
    private JCheckBox pass;
    /** Boolean that represents whether or not TestCaseEditPane is in add mode */
    private boolean add;
    /** Boolean that represents whether or not TestCaseEditPane is in edit mode */
    private boolean edit;
    /** Represents the current TestCase being edited */
    private TestCaseData data;

    /**
     * Creates a new edit pane with an empty TestingTypeData.
     * @param list the list of TestingTypes used when creating a TestCase
     */
    public TestCaseEditPane(TestingTypeList list) {
        this(new TestCaseData(), list);
    }

    /**
     * Creates a new edit pane with the given TestCaseData.
     * 
     * @param data information to populate the pane with
     * @param list the list of TestingTypes used when creating a TestCase
     */
    public TestCaseEditPane(TestCaseData data, TestingTypeList list) {
        super();
        this.data = data;
        this.testingTypes = list;
        testingTypes.addObserver(this);
        add = false;
        edit = false;
        init();
    }

    /**
     * Initializes the GUI.
     */
    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        initView();
        fillFields();
    }

    /**
     * Initializes the view.
     */
    private void initView() {
        // Row 1
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Test Case ID: ", SwingConstants.LEFT));
        p.add(getTestCaseID());
        p.add(new JLabel("Testing Type: "));
        p.add(getTestingType());
        p.add(new JLabel("Test Creation Date & Time: "));
        p.add(getTestCreationDateSpinner());
        this.add(p);

        // Row 2
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Description:", SwingConstants.LEFT));
        this.add(p);

        // Row 3
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(getTestCaseDescription());
        this.add(p);

        // Row 4
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Tested?", SwingConstants.LEFT));
        p.add(getTested());
        p.add(new JLabel("Last Tested Date & Time:"));
        p.add(getLastTestedDateSpinner());
        this.add(p);

        // Row 5
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Expected Results:", SwingConstants.LEFT));
        this.add(p);

        // Row 6
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(getExpectedResults());
        this.add(p);

        // Row 7
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Actual Results:", SwingConstants.LEFT));
        this.add(p);

        // Row 8
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(getActualResults());
        this.add(p);

        // Row 9
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Pass?", SwingConstants.LEFT));
        p.add(pass());
        this.add(p);
    }

    /**
     * Returns the JSpinner used to control the creation date of a TestCase.
     * 
     * @return the JSpinner used to control the creation date of a TestCase.
     */
    JSpinner getTestCreationDateSpinner() {
        if (testCreationDate == null) {
            testCreationDate = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editor = new DateEditor(testCreationDate, "EEE MMM dd yyyy, kk:mm");
            testCreationDate.setEditor(editor);
            testCreationDate.setEnabled(false);
            testCreationDate.setVisible(true);
        }
        return testCreationDate;
    }

    /**
     * Returns the JSpinner used to control the creation date of a TestCase.
     * 
     * @return the JSpinner used to control the creation date of a TestCase
     */
    JSpinner getLastTestedDateSpinner() {
        if (testLastTestedDate == null) {
            testLastTestedDate = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editor = new DateEditor(testLastTestedDate, "EEE MMM dd yyyy, kk:mm");
            testLastTestedDate.setEditor(editor);
            testLastTestedDate.setEnabled(false);
            testLastTestedDate.setVisible(true);
        }
        return testLastTestedDate;
    }

    /**
     * Returns the creation date of the TestCase as determined by the value on
     * the testCreationDate JSpinner.
     * 
     * @return the creation date of the TestCase as determined by the value on
     *         the testCreationDate JSpinner
     */
    Date getTestCreationDate() {
        return (Date) getTestCreationDateSpinner().getValue();
    }

    /**
     * Returns the last testing date of the TestCase as determined by the value
     * on the testLastTestedDate JSpinner.
     * 
     * @return the last testing date of the TestCase as determined by the value
     *         on the testLastTestedDate JSpinner
     */
    Date getLastTestedDate() {
        return (Date) getLastTestedDateSpinner().getValue();
    }

    /**
     * Returns the text field for the TestCase's ID.
     * 
     * @return the text field for the TestCases's ID
     */
    JTextField getTestCaseID() {
        if (testCaseID == null) {
            testCaseID = new JTextField(5);
            testCaseID.setEditable(false);
            testCaseID.setVisible(true);
            testCaseID.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return testCaseID;
    }

    /**
     * Returns the text area for the TestCases's description.
     * 
     * @return the text area for the TestCases's description
     */
    JTextArea getTestCaseDescription() {
        if (testCaseDescription == null) {
            testCaseDescription = new JTextArea(5, 80);
            testCaseDescription.setEditable(false);
            testCaseDescription.setVisible(true);
            testCaseDescription.setLineWrap(true);
            testCaseDescription.setAutoscrolls(true);
        }
        return testCaseDescription;
    }

    /**
     * Returns the drop down menu used to control TestingTypes.
     * 
     * @return the drop down menu used to control TestingTypes
     */
    JComboBox<TestingType> getTestingType() {
        if (tcTestingType == null) {
            tcTestingType = new JComboBox<TestingType>();
            tcTestingType.addItem(null);
            for (int i = 0; i < testingTypes.size(); i++) {
                tcTestingType.addItem(testingTypes.getTestingTypeAt(i));
            }
            tcTestingType.setEnabled(false);
            tcTestingType.setVisible(true);
        }
        return tcTestingType;
    }

    /**
     * Returns the text area for the TestCase's expected results.
     * 
     * @return the text area for the TestCase's expected results
     */
    JTextArea getExpectedResults() {
        if (expectedResults == null) {
            expectedResults = new JTextArea(5, 80);
            expectedResults.setEditable(false);
            expectedResults.setVisible(true);
            expectedResults.setLineWrap(true);
            expectedResults.setAutoscrolls(true);
        }
        return expectedResults;
    }

    /**
     * Returns the text area for the TestCase's actual results.
     * 
     * @return the text area for the TestCase's actual results
     */
    JTextArea getActualResults() {
        if (actualResults == null) {
            actualResults = new JTextArea(5, 80);
            actualResults.setEditable(false);
            actualResults.setVisible(true);
            actualResults.setLineWrap(true);
            actualResults.setAutoscrolls(true);
        }
        return actualResults;
    }

    /**
     * Returns the check box used to indicate the TestCase's passing status.
     * 
     * @return the check box used to indicate the TestCase's passing status
     */
    JCheckBox pass() {
        if (pass == null) {
            pass = new JCheckBox();
            pass.setEnabled(false);
            pass.setVisible(true);
        }
        return pass;
    }

    /**
     * Returns the check box used to indicate the TestCase's testing status.
     * 
     * @return the check box used to indicate the TestCase's testing status
     */
    JCheckBox getTested() {
        if (tested == null) {
            tested = new JCheckBox();
            tested.setEnabled(false);
            tested.setVisible(true);
        }
        return tested;
    }

    /**
     * Sets the date on the testCreationDate JSpinner to the date provided.
     * @param date the new date of the testCreationDate JSpinner
     */
    void setCreationDate(Date date) {
        if(date == null) {
            getTestCreationDateSpinner().getModel().setValue(new Date());
        } else {
            getTestCreationDateSpinner().getModel().setValue(date);
        }
        
    }

    /**
     * Sets the date on the testLastTestedDate JSpinner to the date provided.
     * @param date the new date of the testLastTestedDate JSpinner
     */
    void setLastTestedDate(Date date) {
        if(date == null) {
            getLastTestedDateSpinner().getModel().setValue(new Date());
        } else {
            getLastTestedDateSpinner().getModel().setValue(date);
        }
    }

    /**
     * Returns true if TestCaseEditPane is in add mode.
     * 
     * @return true if TestCaseEditPane is in add mode
     */
    boolean isAddMode() {
        return add;
    }

    /**
     * Returns true if TestCaseEditPane is in edit mode.
     * 
     * @return true if TestCaseEditPane is in edit mode
     */
    boolean isEditMode() {
        return edit;
    }

    /**
     * Enables add mode and disables edit mode.
     */
    void enableAdd() {
        if (!add) {
            add = true;
            edit = false;
            clearFields();
        }
    }

    /**
     * Disables add mode.
     */
    void disableAdd() {
        add = false;
        clearFields();
    }

    /**
     * Enables edit mode and disables add mode.
     * 
     * @param data TestCaseData used to populate the edit area with
     */
    void enableEdit(TestCaseData data) {
        if (!edit) {
            edit = true;
            add = false;
            this.data = data;
            fillFields();
        }
    }

    /**
     * Disables edit mode.
     */
    void disableEdit() {
        edit = false;
        clearFields();
    }

    /**
     * Returns true if the required fields are not empty.
     * 
     * @return true if the required fields are not empty
     */
    boolean fieldsNotEmpty() {
        return tcTestingType.getSelectedItem() != null && testCreationDate.getValue() != null
                && expectedResults.getDocument().getLength() != 0 && testCaseDescription.getDocument().getLength() != 0;
    }

    /**
     * Initializes the TestCaseData to the given value.
     * 
     * @param data new TestCaseData
     */
    void setTestCaseData(TestCaseData data) {
        this.data = data;
    }

    /**
     * Adds the given EventListener to the following component fields: testCreationDate, testLastTestedDate,
     * testCaseDescription, expectedResults, actualResults.
     * 
     * @param listener EventListern to add to the described fields
     */
    void addFieldListener(EventListener listener) {
        getTestCreationDateSpinner().addChangeListener((ChangeListener) listener);
        getLastTestedDateSpinner().addChangeListener((ChangeListener) listener);
        //getTestingType().addItemListener((ItemListener) listener);
        getTestCaseDescription().getDocument().addDocumentListener((DocumentListener) listener);
        getExpectedResults().getDocument().addDocumentListener((DocumentListener) listener);
        getActualResults().getDocument().addDocumentListener((DocumentListener) listener);
        //getTested().addItemListener((ItemListener) listener);
        //pass().addItemListener((ItemListener) listener);
    }

    /**
     * Fills the fields with the appropriate data from the TestCaseData.
     */
    void fillFields() {
        if (data == null) {
            testCaseID.setText("");
            tcTestingType.setSelectedItem(null);
            tcTestingType.setEnabled(false);
            setCreationDate(new Date());
            testCreationDate.setEnabled(false);
            setLastTestedDate(new Date());
            testLastTestedDate.setEnabled(false);
            testCaseDescription.setText("");
            testCaseDescription.setEditable(false);
            expectedResults.setText("");
            expectedResults.setEditable(false);
            actualResults.setText("");
            actualResults.setEditable(false);
            tested.setSelected(false);
            tested.setEnabled(false);
            pass.setSelected(false);
            pass.setEnabled(false);
        } else {
            testCaseID.setText(data.getTestCaseID());
            tcTestingType.setSelectedItem(data.getTestingType());
            setCreationDate(data.getCreationDateTime());
            setLastTestedDate(data.getLastTestedDateTime());
            testCaseDescription.setText(data.getDescription());
            expectedResults.setText(data.getExpectedResults());
            actualResults.setText(data.getActualResults());
            tested.setSelected(data.tested());
            pass.setSelected(data.pass());
        }
        if (add || edit) {
            tcTestingType.setEnabled(true);
            testCreationDate.setEnabled(true);
            testLastTestedDate.setEnabled(true);
            testCaseDescription.setEditable(true);
            expectedResults.setEditable(true);
            actualResults.setEditable(true);
            tested.setEnabled(true);
            pass.setEnabled(true);
        }
    }

    /**
     * Clears all fields to their default values.
     */
    void clearFields() {
        data = null;
        fillFields();
    }

    /**
     * Returns the fields as a TestCaseData object.
     * 
     * @return the fields as a TestCaseData object
     */
    TestCaseData getFields() {
        return new TestCaseData(getTestCaseID().getText(), getTestCaseDescription().getText(),
                (TestingType) getTestingType().getSelectedItem(), getTestCreationDate(), getLastTestedDate(),
                getTested().isSelected(), getExpectedResults().getText(), getActualResults().getText(),
                pass().isSelected());
    }

    /**
     * Updates the tcTestingType field with the provided observable object if it is
     * a TestingTypeList and displays the change.
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof TestingTypeList) {
            testingTypes = (TestingTypeList) o;
            JComboBox<TestingType> newTypes = new JComboBox<TestingType>();
            newTypes.addItem(null);
            for (int i = 0; i < testingTypes.size(); i++) {
                newTypes.addItem(testingTypes.getTestingTypeAt(i));
            }
            getTestingType().setModel(newTypes.getModel());
            tcTestingType.setEnabled(false);
            tcTestingType.setVisible(true);
        }
    }

}
