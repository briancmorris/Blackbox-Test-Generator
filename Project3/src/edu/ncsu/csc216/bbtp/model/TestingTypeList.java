package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.ArrayList;

/**
 * A basic java object for holding a list of types
 * 
 * @author Brian and Nat
 *
 */
public class TestingTypeList extends Observable implements Tabular, Serializable, Observer {

    /** Serial version UID. */
    private static final long serialVersionUID = 984509L;
    /**
     * holds the list name
     */
    private String name;
    /**
     * holds the next type num
     */
    private int nextTestingTypeNum;
    /**
     * holds the list of types
     */
    private ArrayList list;

    /**
     * constructor for the type list
     */
    public TestingTypeList() {
        list = new ArrayList();
        this.name = "Testing Types";
        this.nextTestingTypeNum = 1;

        setChanged();
        notifyObservers(this);
    }

    /**
     * return the name of the list
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * adds the type to the list
     * 
     * @param name
     *            of the type
     * @param description
     *            of the type
     * @return true of added
     */
    public boolean addTestingType(String name, String description) {

        try {
            TestingType newType = new TestingType("TT" + getNextTestingTypeNum(), name, description);
            newType.addObserver(this);
            if (list.size() == 0) {
                list.add(newType);
            } 
            else 
            {
            	/*
                int indexToAdd = 0;
                TestingType compare = (TestingType) list.get(0);
                for (int i = 0; i < list.size(); i++) {
                    compare = (TestingType) list.get(i);
                    if (compare.compareTo(newType) >= 0) {
                        indexToAdd++;
                    }
                }
                */
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
     * returns the type at the index
     * 
     * @param index
     *            to retrieve
     * @return the type at the index
     */
    public TestingType getTestingTypeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return (TestingType) list.get(index);
    }

    /**
     * return the ID of the type provided
     * 
     * @param input
     *            the type
     * @return the index
     */
    public int indexOf(String input) 
    {
        int out = -1;
        TestingType compare;
        for (int i = 0; i < list.size(); i++) {
            compare = (TestingType) list.get(i);
            if (compare.getTestingTypeID().equals(input)) {
                out = i;
            }
        }
        return out;
    }

    /**
     * returns the index of the type with name provided
     * 
     * @param name
     *            of the type
     * @return the type
     */
    public int indexOfName(String name) 
    {
        int out = -1;
        TestingType compare;
        
        for (int i = 0; i < this.list.size(); i++) 
        {
            compare = (TestingType) this.list.get(i);
            if (compare.getName().equals(name)) 
            {
            	//out = i;
            	return list.indexOf(compare);
            }
        }
        return out;
    }

    /**
     * returns the size
     * 
     * @return the size
     */
    public int size() {
        return list.size();
    }

    /**
     * returns true if its empty
     * 
     * @return true if its empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * removes the testing type
     * 
     * @param index
     *            to remove
     * @return the type removed
     */
    public TestingType removeTestingTypeAt(int index) 
    {
    	/*
    	if (list == null || index < 0 || index >= size()) 
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	 */
    	
    	TestingType out = null;
    	
    	try
    	{
    		out = (TestingType) list.remove(index);  
    		out.deleteObserver(this);
    		setChanged();
    		notifyObservers();
    		return out;
    	}	
    	catch (Exception e) 
    	{
    		throw new IndexOutOfBoundsException();
    	}
    }

    /**
     * removes the testing type
     * 
     * @param input
     *            Name of type to remove
     * @return true if removed
     */
    public boolean removeTestingType(String input) {
        int index = indexOfName(input);
        if (index != -1) {
            TestingType out = removeTestingTypeAt(index);
            out.deleteObserver(this);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * returns the next testing type number
     * 
     * @return
     */
    private int getNextTestingTypeNum() {
        return nextTestingTypeNum;
    }

    /**
     * increases the next testing type number
     */
    private void incNextTestingTypeNum() {
        nextTestingTypeNum++;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        o = (TestingType) o;
        if (list.contains(o)) {
            setChanged();
            notifyObservers(arg);
        }
    }

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
