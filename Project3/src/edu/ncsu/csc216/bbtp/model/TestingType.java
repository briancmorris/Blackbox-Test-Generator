package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * A basic java object for testing types
 * 
 * @author Brian and Nat
 *
 */
public class TestingType extends Observable implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 459188L;
    /**
     * holds the name
     */
    private String name;
    /**
     * holds the description
     */
    private String description;
    /**
     * holds the testing type
     */
    private String testingTypeId;

    /**
     * constructs a testing type
     * 
     * @param name
     *            of the type
     * @param description
     *            of the type
     * @param id
     *            of the type
     */
    public TestingType( String id, String name, String description) {
        setName(name);
        setDescription(description);
        setTestingTypeId(id);

        setChanged();
        notifyObservers(this);
    }

    /**
      * returns the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else
        {
        	this.name = name;

            setChanged();
            notifyObservers(this);
        }
    }

    /**
     * returns the description
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.description = description;

        setChanged();
        notifyObservers(this);
    }

    /**
     * returns the testing type
     * 
     * @return the testingTypeId
     */
    public String getTestingTypeID() {
        return testingTypeId;
    }

    /**
     * sets the testing type id
     * 
     * @param testingTypeId
     *            the testingTypeId to set
     */
    private void setTestingTypeId(String testingTypeId) {
        if (testingTypeId == null || testingTypeId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.testingTypeId = testingTypeId;

        setChanged();
        notifyObservers(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((testingTypeId == null) ? 0 : testingTypeId.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestingType other = (TestingType) obj;
        if (testingTypeId == null) {
            if (other.testingTypeId != null)
                return false;
        } else if (this.testingTypeId.equals(other.getTestingTypeID()))
            return true;
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * compares this test type to input
     * 
     * @param input
     *            to compare this with
     * @return 0 if equal
     */
    public int compareTo(TestingType input) {
        if (input == null) {
            throw new NullPointerException();
        }

        if (this.getTestingTypeID() == null) {
            if (input.getTestingTypeID() == null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (input.getTestingTypeID() == null) {
                return -1;
            } else {
                return this.getTestingTypeID().compareTo(input.getTestingTypeID());
            }
        }
    }
}