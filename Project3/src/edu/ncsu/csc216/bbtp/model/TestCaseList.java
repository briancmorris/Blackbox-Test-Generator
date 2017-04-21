package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.LinkedList;

/**
 * The TestCaseList class stores TestCases in a LinkedList with
 * various getter methods to retrieve the desired object.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestCaseList extends Observable implements Tabular, Serializable, Observer {

    /** Serial version UID */
    private static final long serialVersionUID = 98734509L;
    /** The name of this TestCaseList */
    private String name;
    /** The numeric value of the next TestCase ID */
    private int nextTestCaseNum;
    /** The ID of this TestCaseList */
    private String testCaseListID;
    /** The LinkedList used to store TestCases */
    private LinkedList list;

    /**
     * The constructor for TestCaseList initializes an empty TestCaseList with
     * provided values, sets nextTestCaseNum to 1, and notifies the observers of
     * this TestCaseList. If either parameter is null or an empty String, an
     * IllegalArgumentException is thrown.
     * 
     * @param name the of this TestCaseList
     * @param testCaseListID the ID of this TestCaseList
     */
    public TestCaseList(String name, String testCaseListID) {
        
        this.nextTestCaseNum = 1;
        this.list = new LinkedList();
        setName(name);
        setTestCaseListID(testCaseListID);
        

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the name of this TestCaseList.
     * @return the name of this TestCaseList
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this TestCaseList to the provided value and notifies
     * the observers of this TestCaseList. If the provided name is null or an
     * empty String, an IllegalArgumentException is thrown.
     * 
     * @param name the new name of this TestCaseList
     * @throws IllegalArgumentException if the provided name is null or an empty
     *         String
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the ID of this TestCaseList.
     * 
     * @return the ID of this TestCaseList
     */
    public String getTestCaseListID() {
        return testCaseListID;
    }

    /**
     * Changes the ID of this TestCaseList to the ID provided and notifies
     * the observers of this TestCaseList. If the provided ID is null
     * or an empty String, an IllegalArgumentException is thrown.
     * 
     * @param testCaseListID the new ID of this TestCaseList
     * @throws IllegalArgumentException if the provided ID is null or an
     *         empty String
     */
    private void setTestCaseListID(String testCaseListID) {
        if (testCaseListID == null || testCaseListID.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.testCaseListID = testCaseListID;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the value of nextTestCaseNum.
     * 
     * @return the value of nextTestCaseNum
     */
    private int getNextTestCaseNum() {
        return nextTestCaseNum;
    }

    /**
     * Increments the value of nextTestCaseNum.
     */
    private void incNextTestCaseNum() {
        this.nextTestCaseNum++;
    }

    /**
     * Adds a new TestCase to the list in sorted order by constructing a new
     * TestCase object with the parameters provided, returns true if successful,
     * and notifies the observers of this TestCaseList. If the TestCase cannot
     * be constructed properly, false is returned instead.
     * 
     * @param desc the description of the new TestCase
     * @param type the TestingType of the new TestCase
     * @param creation the creation date of the new TestCase
     * @param exp the expected results of the new TestCase
     * @param tested the value of the testing status of the new TestCase
     * @param lastTestDate the last testing date of the new TestCase
     * @param act the actual results of the new TestCase
     * @param pass the boolean that represents whether or not the new TestCase has passed testing
     * @return true if the new TestCase has been added successfully, false otherwise
     */
    public boolean addTestCase(String desc, TestingType type, Date creation, String exp,
            boolean tested, Date lastTestDate, String act, boolean pass) {

        TestCase newTestCase = null;
        try {
            newTestCase = new TestCase(getTestCaseListID() + "-TC" + getNextTestCaseNum(), desc,
                    type, creation, exp, tested, lastTestDate, act, pass);
        } catch (IllegalArgumentException e) {
            return false;
        }
        newTestCase.addObserver(this);

        if (list.size() == 0) {
            list.add(newTestCase);
        } else {
            TestCase compare = (TestCase) list.get(0);
            for (int i = 0; i < list.size(); i++) {

                compare = (TestCase) list.get(i);
                if (newTestCase.compareTo(compare) < 0) {
                    list.add(i, newTestCase);
                    incNextTestCaseNum();
                    setChanged();
                    notifyObservers(this);
                    return true;
                }
            }
            list.add(list.size(), newTestCase);
        }

        incNextTestCaseNum();
        setChanged();
        notifyObservers(this);
        return true;

    }

    /**
     * Returns the TestCase located at the given index in this TestCaseList.
     * If the index provided is less than 0 or greater than the size of this
     * TestCaseList, an IndexOutOfBoundsException is thrown.
     * 
     * @param index the index of the TestCase to retrieve
     * @return the TestCase at the given index
     */
    public TestCase getTestCaseAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return (TestCase) list.get(index);
    }

    /**
     * Returns the index of the TestCase with the provided ID or -1
     * if it is not contained within this TestCaseList.
     * 
     * @param testCaseID the ID of the TestCase to search for
     * @return the index of the TestCase with the provided ID or -1
     *         if it is not contained within this TestCaseList
     */
    public int indexOf(String testCaseID) {
        for (int i = 0; i < list.size(); i++) {
            TestCase compare = (TestCase) list.get(i);
            if (compare.getTestCaseID().equals(testCaseID)) {
                return i;
                //break;
            }
        }
        return -1;
    }

    /**
     * Returns the number of TestCases contained within this TestCaseList.
     * 
     * @return the number of TestCases contained within this TestCaseList
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns true if this TestCaseList is empty.
     * 
     * @return true if this TestCaseList is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes the TestCase at the provided index, returns its value, and
     * notifies the observers of this TestCaseList. If the index provided is
     * less than 0 or greater than or equal to the size of this TestCaseList, an
     * IndexOutOfBoundsException is thrown.
     * 
     * @param index the index of the TestCase to remove
     * @return the TestCase that was removed
     * @throws IndexOutOfBoundsException if the index provided is less than 0
     *         or greater than or equal to the size of this TestCaseList
     */
    public TestCase removeTestCaseAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        TestCase out = (TestCase) list.remove(index);
        out.deleteObserver(this);
        setChanged();
        notifyObservers(this);
        return out;
    }

    /**
     * Removes the TestCase with the provided ID from this TestCaseList, returns
     * true if removed successfully, and notifies the observers of this
     * TestCaseList. If the TestCase was not found in this list, false is
     * returned instead.
     * 
     * @param testCaseID the ID of the TestCase to remove
     * @return true if the TestCase was removed successfully, false if it was
     *         not found within this TestCaseList
     */
    public boolean removeTestCase(String testCaseID) {
        int index = indexOf(testCaseID);
        if (index != -1) {
            TestCase out = removeTestCaseAt(index);
            out.deleteObserver(this);
            setChanged();
            notifyObservers(this);
            return true;
        }
        return false;
    }

    /**
     * Returns a 2D Object array containing the ID, description, TestingType,
     * creation date, last testing date, testing status, expected results,
     * actual results, and passing status for every TestCase contained within
     * this TestCaseList.
     * 
     * @return a 2D Object array containing the ID, description, TestingType,
     *         creation date, last testing date, testing status, expected
     *         results, actual results, and passing status for every TestCase
     *         contained within this TestCaseList
     */
    @Override
    public Object[][] get2DArray() {
        Object[][] out = new Object[list.size()][9];
        for (int i = 0; i < list.size(); i++) {
            TestCase next = (TestCase) list.get(i);
            out[i][0] = next.getTestCaseID();
            out[i][1] = next.getDescription();
            out[i][2] = next.getTestingType();
            out[i][3] = next.getCreationDateTime();
            out[i][4] = next.getLastTestedDateTime();
            out[i][5] = next.tested();
            out[i][6] = next.getExpectedResults();
            out[i][7] = next.getActualResults();
            out[i][8] = next.pass();
        }

        return out;
    }

    /**
     * The update method notifies the observers of TestCaseList
     * when any changes have occurred to a TestCase Object that is contained
     * within this TestCaseList.
     * @param o the observable object that TestCaseList observes
     * @param arg the Object that is used to update the observers of TestCase
     */
    @Override
    public void update(Observable o, Object arg) {
        o = (TestCase) o;
        if (list.contains(o)) {
            setChanged();
            notifyObservers(arg);
        }
    }

}
