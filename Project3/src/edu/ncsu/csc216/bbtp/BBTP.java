package edu.ncsu.csc216.bbtp;

import edu.ncsu.csc216.bbtp.model.TestingTypeList;
import edu.ncsu.csc216.bbtp.util.ArrayList;   

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

    /** Serial version UID */
    private static final long serialVersionUID = 34992L;

    /** Constant used to resize the array of TestCaseLists */
    private static final int RESIZE = 3;
    /** An array of TestCaseLists used to store the TestCaseLists found in this BBTP */
    private TestCaseList[] testCases;
    /** The number of TestCaseLists contained within this BBTP */
    private int numLists;
    /** The TestingTypes contained within this BBTP */
    private TestingTypeList testingTypes;
    /** The name of the file used for IO operations */
    private String filename;
    /** Boolean that indicates whether or not this BBTP has changed */
    private boolean changed;
    /** The numeric value of the next TestCaseList ID */
    private int nextTestCaseListNum;

    /**
     * The constructor for BBTP initializes a new BBTP with an empty
     * TestingTypeList and TestCaseList named "New List", and adds this BBTP as
     * an observer to the TestingTypeList and TestCaseList. The observers of
     * BBTP are notified.
     */
    public BBTP() {
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
     * Returns true if this instance of BBTP has been changed, false if not.
     * 
     * @return true if this instance of BBTP has been changed, false if not
     */
    public boolean isChanged() {
        return changed;
    }
 
    /**
     * Sets the changed field to the boolean provided and notifies
     * the observers of BBTP.
     * @param changed the new value of the changed field
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
        //notifyObservers(this);
    }

	/**
	 * Returns the filename used for IO operations.
	 * @return the filename used for IO operations
	 */
    public String getFilename() {
        return filename;
    }

	/**
	 * Changes the name of the file used for IO operations and notifies
	 * the observers of this BBTP. If the provided filename is null,
	 * contains all whitespace, or is empty, an IllegalArgumentException
	 * is thrown.
	 * @param filename the new filename of the file used for IO operations
	 * @throws IllegalArgumentException if the filename provided is null,
	 *         empty, or contains all whitespace
	 */
    public void setFilename(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.filename = filename;

        setChanged(true);
        notifyObservers(this);
    }
	
    /**
     * Returns the value of nextTestCaseListNum.
     * 
     * @return the value of nextTestCaseListNum
     */
    private int getNextTestCaseListNum() {
        return nextTestCaseListNum;
    }
	
    /**
     * Increments the value of nextTestCaseListNum.
     */
    private void incNextTestCaseListNum() {
        this.nextTestCaseListNum++;
    }
	
    /**
     * Returns the number of TestCaseLists contained within this BBTP.
     * 
     * @return the number of TestCaseLists contained within this BBTP
     */
    public int getNumTestCaseLists() {
        return numLists;
    }
 
	/**
	 * Returns the TestCaseList at the given index. If the
	 * provided index is less than 0 or greater than or equal
	 * to numLists, an IndexOutOfBoundsException is thrown.
	 * @param index of the TestCaseList to retrieve
	 * @return the TestCaseList at the given index
	 * @throws IndexOutOfBoundsException if the provided index
	 *         is less than 0 or greater than or equal to numLists
	 */
    public TestCaseList getTestCaseList(int index) {

        if (index >= numLists || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return testCases[index];
    }
	
	/**
	 * Returns the TestingTypeList contained within this BBTP.
	 * @return the TestingTypeList contained within this BBTP
	 */
    public TestingTypeList getTestingTypeList() {
        return testingTypes;
    }

	/**
	 * Adds a TestCaseList named "New List" to the BBTP, returns its index,
	 * and notifies the observers of this BBTP.
	 * @return the index of the new TestCaseList
	 */
    public int addTestCaseList() {
        if (numLists + 1 == testCases.length) {
            growArray();
        }

        testCases[numLists] = new TestCaseList("New List", "TCL" + getNextTestCaseListNum());
        testCases[numLists].addObserver(this);
        setChanged();
        incNextTestCaseListNum();
        numLists++;

        setChanged(true);
        notifyObservers(testCases[numLists]);
        return numLists - 1;
    }
	
	/**
	 * Removes the TestCaseList at the given index and notifies
	 * the observers of this BBTP of the change. If the index
	 * is less than 0 or greater than or equal to the number
	 * of TestCaseLists, an IndexOutOfBoundsException is thrown.
	 * @param index the index of the TestCaseList to remove
	 * @throws IndexOutOfBoundsException if the provided index
	 *         is less than 0 or greater than or equal to numLists
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
	 * Increases the storage capacity of the testCases array when required by the add method.
	 */
    private void growArray() {
    	TestCaseList[] newList = new TestCaseList[testCases.length * RESIZE];
        for (int i = 0; i < numLists; i++) {
            newList[i] = testCases[i];
        }
        testCases = newList;
    }
    

    /**
     * Changes the changed value of this BBTP to true and notifies the observers of
     * this change with the provided Object parameter.
     */
    @Override
    public void update(Observable o, Object arg) {
        setChanged(true);
        setChanged();
        notifyObservers(arg);
    }

}