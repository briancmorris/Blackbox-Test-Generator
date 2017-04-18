package edu.ncsu.csc216.bbtp;

import edu.ncsu.csc216.bbtp.model.TestingTypeList;
import edu.ncsu.csc216.bbtp.util.ArrayList;   

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
// TODO add later
//import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.model.TestCaseList;

/**
 * The main class for the BBTP tools. Holds references to the top-level data
 * structures that contain TestCase and TestingType objects and acts as the
 * controller between the model and the GUI presentation view.
 * 
 * @author Brian Morris
 * @author Nat Ellis
 */
public class BBTP extends Observable implements Serializable, Observer {

    /** Serial version UID. */
    private static final long serialVersionUID = 34992L;

    /**
     * Increment for increasing the capacity of the array of TestCaseLists
     */
    private static final int RESIZE = 3;
    /** A collection of TestCaseList */
    private TestCaseList[] testCases;
    /** Number of TestCaseList */
    private int numLists;
    /** A collection of TestingTypes */
    private TestingTypeList testingTypes;
    /** Filename for saving the bbtp information */
    private String filename;
    /** True if bbtp has changed since last save */
    private boolean changed;
    /** The next number for a task list id */
    private int nextTestCaseListNum;

    /**
     * constructor for a BBTP
     */
    public BBTP() 
    {
    	testCases = new TestCaseList[RESIZE];
    	numLists = 0;
    	testingTypes = new TestingTypeList();
    	testingTypes.addObserver(this);
    	nextTestCaseListNum = 1;
    	addTestCaseList();
    	changed = false;
    	notifyObservers(this);
	}

    /**
     * returns true if a change occurred
     * @return true if a change occurred
     */
    public boolean isChanged()
    {
    	return changed;
    }
 
    /**
     * sets if there has been a change
     * @param changed stores the value of 
     * true if change occurred false otherwise
     */
	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}

	/**
	 * returns the file name
	 * @return the file name
	 */
	public String getFilename() 
	{
		return filename;
	}

	/**
	 * sets the file name
	 * @param filename to set
	 */
	public void setFilename(String filename) 
	{
		if (filename == null || filename.trim().isEmpty())
		{
			throw new IllegalArgumentException();
		}
		this.filename = filename;
		
		setChanged(true);
		notifyObservers(this);
	}
	
	/**
	 * returns the next test case number
	 * @return the next test case number
	 */
	private int getNextTestCaseListNum() 
	{
		return nextTestCaseListNum;
	}
	
	/**
	 * increases the next test case list number
	 * @param nextTestCaseListNum
	 */
	private void incNextTestCaseListNum() 
	{
		this.nextTestCaseListNum++;
	}
	
	/**
	 * returns the number of test case lists
	 * @return the number of test case lists
	 */
	public int getNumTestCaseLists() 
	{
		return numLists;
	}
 
	/**
	 * returns the test case list at index provided
	 * @param index of test case list
	 * @return the list at the index
	 */
	public TestCaseList getTestCaseList(int index) 
	{
		
		if (index >= numLists || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		return testCases[index];
	}
	
	/**
	 * returns the list of types
	 * @return the list of types
	 */
	public TestingTypeList getTestingTypeList() 
	{
		return testingTypes;
	}

	/**
	 * adds the test case to the list
	 * @return index of new test case
	 */
	public int addTestCaseList()
	{
		if (numLists + 1 == testCases.length) {
		    growArray();
		}
		
		testCases[numLists] = new TestCaseList( "New List", "TCL" + getNextTestCaseListNum());
		testCases[numLists].addObserver(this);
		setChanged();
        incNextTestCaseListNum();
        numLists++;
        
        setChanged(true);
        notifyObservers(testCases[numLists]);
		return numLists - 1;
	}
	
	/**
	 * removes the test case at the given index
	 * @param index of test case 
	 */
	public void removeTestCaseList(int index)
	{
		if (index < 0 || index >= numLists) {
	        throw new IndexOutOfBoundsException();
	    }
		TestCaseList removed = testCases[index];
		removed.deleteObserver(this);
		for (int i = index; i < numLists; i++) {
			testCases[i] = testCases[i + 1];
		}
		numLists--;
		
		setChanged(true);
        notifyObservers(removed);
	}

	/**
     * Saves the TestingTypeList and the array of TestCaseLists to the given
     * file using object serialization.
     * 
     * @param fname filename to save BBTP information to.
     * @return true is saved successfully
     */
    public boolean saveDataFile(String fname) {
        if (fname == null || fname.trim().equals("")) {
            System.err.println("Invalid filename" + fname);
            return false;
        } else {
            try {
                FileOutputStream fileOut = new FileOutputStream(fname);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                for (int i = 0; i < numLists; i++) {
                    out.writeObject(testCases[i]);
                }
                out.writeObject(testingTypes);
                out.writeObject(filename);
                out.writeInt(nextTestCaseListNum);
                changed = false;
                out.close();
                fileOut.close();
                return true;
            } catch (IOException e) {
                System.err.println("An error occurred while saving file " + fname);
                e.printStackTrace(System.err);
                return false;
            }
        }
    }

    /**
     * Opens a data file with the given name and creates the data structures
     * from the serialized objects in the file.
     * 
     * @param fname filename to create BBTP information from.
     * @return true is opened successfully
     */
    public boolean openDataFile(String fname) {
        if (changed) {
            saveDataFile(filename);
        }
        try {
            FileInputStream fileIn = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList temp = new ArrayList();
            Object tl = in.readObject();
            while (tl instanceof TestCaseList) {
                TestCaseList l = (TestCaseList) tl;
                l.addObserver(this);
                temp.add(l);
                tl = in.readObject();
            }
            testCases = new TestCaseList[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                testCases[i] = (TestCaseList) temp.get(i);
            }
            numLists = temp.size();
            testingTypes = (TestingTypeList) tl;
            testingTypes.addObserver(this);
            filename = (String) in.readObject();
            nextTestCaseListNum = (int) in.readInt();
            for (int i = 0; i < numLists; i++) {
                TestCaseList list = testCases[i];
                for (int j = 0; j < list.size(); j++) {
                    list.getTestCaseAt(j).addObserver(list);
                }
            }
            for (int i = 0; i < testingTypes.size(); i++) {
                testingTypes.getTestingTypeAt(i).addObserver(testingTypes);
            }
            changed = false;
            in.close();
            fileIn.close();
            return true;
        } catch (IOException e) {
            System.err.println("An error occurred while reading file " + fname);
            e.printStackTrace(System.err);
            return false;
        } catch (ClassNotFoundException c) {
            System.err.println("Error reconstructing BBTP from file " + fname);
            c.printStackTrace(System.err);
            return false;
        }
    }
    
    /**
	 * Increases the storage capacity of the list array when required by the add methods.
	 */
    private void growArray() {
    	TestCaseList[] newList = new TestCaseList[testCases.length * RESIZE];
        for (int i = 0; i < numLists; i++) {
            newList[i] = testCases[i];
        }
        testCases = newList;
    }
    

	@Override
	public void update(Observable o, Object arg) 
	{
		setChanged(true);
		setChanged();
		notifyObservers(arg);
	}

}