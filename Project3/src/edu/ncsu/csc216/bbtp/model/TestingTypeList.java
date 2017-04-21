package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.ArrayList;

/**
 * The TestingTypeList class stores TestingTypes in an ArrayList with
 * various getter methods to retrieve the desired object.
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestingTypeList extends Observable implements Tabular, Serializable, Observer {

    /** Serial version UID */
    private static final long serialVersionUID = 984509L;
    /** The name of this TestingTypeList */
    private String name;
    /** The numeric value of the next TestingType ID */
    private int nextTestingTypeNum;
    /** The ArrayList that stores the TestingTypes */
    private ArrayList list;

    /**
     * The constructor for TestingTypeList initializes an empty
     * TestingTypeList with the name "Testing Types" and sets
     * nextTestingTypeNum to 1. After construction, its observers are
     * notified of the change.
     */
    public TestingTypeList() {
        this.list = new ArrayList();
        this.name = "Testing Types";
        this.nextTestingTypeNum = 1;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the name of this TestingTypeList.
     * @return the name of this TestingTypeList
     */
    public String getName() {
        return name;
    }

    /**
     * Constructs a TestingType with the provided name and description and adds
     * it to the end of the list. Returns true if the new TestingType was added
     * to the list successfully.
     * @param name the name of the new TestingType
     * @param description the description of the new TestingType
     * @return true if new TestingType is added to the list successfully, false otherwise
     */
    public boolean addTestingType(String name, String description) {

        try {
            TestingType newType = new TestingType("TT" + getNextTestingTypeNum(), name, description);
            newType.addObserver(this);
            if (list.size() == 0) {
                list.add(newType);
            } else {
                list.add(newType);
            }
            incNextTestingTypeNum();
            setChanged();
            notifyObservers(this);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the TestingType at the given index. If the index provided is less
     * than 0 or greater than or equal to the size of the list, an
     * IndexOutOfBoundsException is thrown.
     * 
     * @param index the index of the TestingType to retrieve.
     * @return the TestingType at the given index
     * @throws IndexOutOfBoundsException if the given index is less than 0 or greater
     *         than or equal to the size of the list
     */
    public TestingType getTestingTypeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return (TestingType) list.get(index);
    }

    /**
     * Returns the index of the TestingType with the given ID. If there is no
     * TestingType that has that ID in the list, the value of -1 is returned.
     * 
     * @param id the ID of the TestingType
     * @return the index of the TestingType with the given ID, or -1 if this
     *         list does not contain the TestingType with the associated ID
     */
    public int indexOf(String id) {
        int out = -1;
        TestingType compare;
        for (int i = 0; i < list.size(); i++) {
            compare = (TestingType) list.get(i);
            if (compare.getTestingTypeID().equals(id)) {
                out = i;
                break;
            }
        }
        return out;
    }

    /**
     * Returns the index of the TestingType with the given name. If there
     * is no TestingType that has that name in the list, the value of -1 is
     * returned.
     * 
     * @param name the name of the TestingType
     * @return the index of the TestingType with the given ID, or -1 if this list does not contain
     *         the TestingType with the associated name
     */
    public int indexOfName(String name) {
        int out = -1;
        TestingType compare;

        for (int i = 0; i < this.list.size(); i++) {
            compare = (TestingType) this.list.get(i);
            if (compare.getName().equals(name)) {
                // out = i;
                return list.indexOf(compare);
            }
        }
        return out;
    }

    /**
     * Returns the number of TestingTypes contained within this TestingTypeList.
     * @return the number of TestingTypes contained within this TestingTypeList.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns true if this TestingTypeList is empty, false otherwise.
     * @return true if this TestingTypeList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes the TestingType at the given index and returns it. If the index provided
     * is less than 0 or greater than or equal to the size of this list,
     * an IndexOutOBoundsException is thrown.
     * 
     * @param index the index of the TestingType to remove.
     * @return the TestingType that was removed
     * @throws IndexOutOfBoundsException if the given index is less than 0 or greater than or equal to
     * the size of this list
     */
    public TestingType removeTestingTypeAt(int index) {
        try {
            TestingType out = (TestingType) list.remove(index);
            out.deleteObserver(this);
            setChanged();
            notifyObservers(this);
            return out;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Removes the TestingType with the given ID and returns true if removed
     * successfully. If the ID cannot be found, false is returned.
     * 
     * @param id the ID of the TestingType to remove.
     * @return true if the TestingType is removed successfully, false if the
     *         TestingType does not exist in the list.
     */
    public boolean removeTestingType(String id) {
        int index = indexOf(id);
        if (index != -1) {
            TestingType out = removeTestingTypeAt(index);
            out.deleteObserver(this);
            setChanged();
            notifyObservers(this);
            return true;
        }
        return false;
    }

    /**
     * Returns the value of nextTestingTypeNum.
     * @return the value of nextTestingTypeNum
     */
    private int getNextTestingTypeNum() {
        return nextTestingTypeNum;
    }

    /**
     * Increments the value of nextTestingTypeNum.
     */
    private void incNextTestingTypeNum() {
        nextTestingTypeNum++;
    }

    /**
     * The update method notifies the observers of TestingTypeList
     * when any changes have occurred to a TestingType Object that is contained
     * within this TesingTypeList.
     * @param o the observable object that TestingTypeList observes
     * @param arg the Object that is used to update the observers of TestingTypeList
     */
    @Override
    public void update(Observable o, Object arg) {
        o = (TestingType) o;
        if (list.contains(o)) {
            setChanged();
            notifyObservers(arg);
        }
    }

    /**
     * Returns a 2D Object array containing the ID, name, and description of all
     * TestingType Objects contained within this TestingTypeList.
     * 
     * @return a 2D Object array containing the ID, name, and description of all
     *         TestingType Objects contained within this TestingTypeList.
     */
    @Override
    public Object[][] get2DArray() {
        Object[][] out = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            TestingType next = (TestingType) list.get(i);
            out[i][0] = next.getTestingTypeID();
            out[i][1] = next.getName();
            out[i][2] = next.getDescription();
        }

        return out;
    }

}
