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
 * GUI for the edit panel
 * 
 * @author Brian Morris
 * @author Nat Ellis
 *
 */
public class TestCaseEditPane extends JPanel implements Serializable, Observer {
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
     * 
     * @param list
     *            to display
     */
    public TestCaseEditPane(TestingTypeList list) {
        this(new TestCaseData(), list);
    }

    /**
     * constructor for a panel with inputed data
     * 
     * @param data
     *            to add
     * @param list
     *            to display
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
     * Initializes the panel
     */
    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        initView();
        fillFields();
    }

    /**
     * Initializes the view
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
     * return the creation date spinner
     * 
     * @return the testCreationDate
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
     * get last tested date spinner
     * 
     * @return the testLastTestedDate spinner
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
     * return the creation date
     * 
     * @return the testCreationDate
     */
    Date getTestCreationDate() {
        return (Date) getTestCreationDateSpinner().getValue();
    }

    /**
     * returns the creation date
     * 
     * @return the testCreationDate
     */
    Date getLastTestedDate() {
        return (Date) getLastTestedDateSpinner().getValue();
        //return data.getLastTestedDateTime();
    }

    /**
     * returns the case ID
     * 
     * @return the testCaseID
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
     * returns the description
     * 
     * @return the testCaseDescription
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
     * returns the testing types
     * 
     * @return the testingTypes
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
     * returns the expected results
     * 
     * @return the expectedResults
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
     * returns the actual results
     * 
     * @return the actualResults
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
     * returns the value of pass
     * 
     * @return the pass
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
     * returns true if tested
     * 
     * @return the tested
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
     * sets the creation date
     * 
     * @param date
     *            the date to set
     */
    void setCreationDate(Date date) {
//        data = new TestCaseData(data.getTestCaseID(), data.getDescription(), data.getTestingType(), date,
//                data.getLastTestedDateTime(), data.tested(), data.getExpectedResults(), data.getActualResults(),
//                data.pass());
        if(date == null) {
            getTestCreationDateSpinner().getModel().setValue(new Date());
        } else {
            getTestCreationDateSpinner().getModel().setValue(date);
        }
        
    }

    /**
     * sets the tested date
     * 
     * @param date
     *            the date to set
     */
    void setLastTestedDate(Date date) {
//        data = new TestCaseData(data.getTestCaseID(), data.getDescription(), data.getTestingType(),
//                data.getCreationDateTime(), date, data.tested(), data.getExpectedResults(), data.getActualResults(),
//                data.pass());
        if(date == null) {
            getLastTestedDateSpinner().getModel().setValue(new Date());
        } else {
            getLastTestedDateSpinner().getModel().setValue(date);
        }
    }

    /**
     * retursn true if added
     * 
     * @return the add
     */
    boolean isAddMode() {
        return add;
    }

    /**
     * returns true if edited
     * 
     * @return the edit
     */
    boolean isEditMode() {
        return edit;
    }

    /**
     * enables add mode
     */
    void enableAdd() {
        if (!add) {
            add = true;
            edit = false;
            clearFields();
        }
    }

    /**
     * disables add mode
     */
    void disableAdd() {
        add = false;
        clearFields();
    }

    /**
     * enables edit mode
     * 
     * @param data
     *            to edit
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
     * disables edit mode
     */
    void disableEdit() {
        edit = false;
        clearFields();
    }

    /**
     * returns true if the field is not empty
     * 
     * @return true if the field is not empty
     */
    boolean fieldsNotEmpty() {
        return tcTestingType.getSelectedItem() != null && testCreationDate.getValue() != null
                && expectedResults.getDocument().getLength() != 0 && testCaseDescription.getDocument().getLength() != 0;
    }

    /**
     * sets the test case data
     * 
     * @param data
     *            to set
     */
    void setTestCaseData(TestCaseData data) {
        this.data = data;
    }

    /**
     * adds a listener
     * 
     * @param listener
     *            to add
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
     * fills the field
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
     * clears the field
     */
    void clearFields() {
        data = null;
        fillFields();
    }

    /**
     * returns the field data
     * 
     * @return the field data
     */
    TestCaseData getFields() {
        return new TestCaseData(getTestCaseID().getText(), getTestCaseDescription().getText(),
                (TestingType) getTestingType().getSelectedItem(), getTestCreationDate(), getLastTestedDate(),
                getTested().isSelected(), getExpectedResults().getText(), getActualResults().getText(),
                pass().isSelected());
    }

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
