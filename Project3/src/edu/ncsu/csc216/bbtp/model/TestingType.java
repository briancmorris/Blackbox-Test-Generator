package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * The TestingType class maintains information relevant to a TestingType
 * including its name, description, and ID.
 * 
 * @author Brian Morris
 * @author Nat Ellis
 */
public class TestingType extends Observable implements Serializable {

    /** Serial version UID */
    private static final long serialVersionUID = 459188L;
    /** The name of the TestingType */
    private String name;
    /** The description of the testing type */
    private String description;
    /** The ID of the testing type */
    private String testingTypeId;

    /**
     * Constructs a TestingType with the given ID, name, and description and notifies
     * any of the observers of this TestingType. If any of these parameters are null or
     * an empty String, an IllegalArgumentException is thrown.
     * 
     * @param name the name of the TestingType
     * @param description the description of the TestingType
     * @param id the ID of the TestingType
     * @throws IllegalArgumentException if any of the provided parameters are null or empty
     */
    public TestingType( String id, String name, String description) {
        setName(name);
        setDescription(description);
        setTestingTypeId(id);

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the name of this TestingType.
     * @return the name of this TestingType
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this TestingType to the provided name and notifies the observers
     * of this TestingType. If the given name is null or an empty string, an IllegalArgumentException
     * is thrown.
     * @param name the new name of this TestingType
     * @throws IllegalArgumentException if name is null or an empty String
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
     * Returns the description of this TestingType.
     * @return the description of this TestingType
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of this TestingType to the description provided
     * and notifies the observers of this TestingType. If the provided description is
     * null or an empty String, an IlegalArgumentException is thrown.
     * @param description the new description of this TestingType
     * @throws IllegalArgumentException if description is null or an empty String
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
     * Returns the ID of this TestingType.
     * @return the ID of this TestingType
     */
    public String getTestingTypeID() {
        return testingTypeId;
    }

    /**
     * Changes the ID of this TestingType to the ID provided
     * and notifies the observers of this TestingType. If the provided
     * ID is null or an empty String, an IllegalArgumentException is thrown.
     * @param testingTypeId the new ID of this TestingType
     * @throws IllegalArgumentException if testingTypeId is null or an empty String
     */
    private void setTestingTypeId(String testingTypeId) {
        if (testingTypeId == null || testingTypeId.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.testingTypeId = testingTypeId;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Generates and returns the hash code for this TestingType.
     * @return the hash code for this TestingType
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


    /**
     * Returns true if the provided Object is equal to this TestingType.
     * @return true if the provided Object is equal to this TestingType
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

    /**
     * Returns the String representation of this TestingType.
     * @return the String representation of this TestingType
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Compares the ID of this TestingType to that of the given TestingType.
     * Returns a negative value if the ID of the provided TestingType is less
     * than the ID of this TestingType, a positive value if it is greater than
     * the ID of this TestingType, or 0 if the ID's are both equal. If the
     * provided TestingType is null, a NullPointerException is thrown.
     * 
     * @param input the TestingType to compare with this TestingType
     * @return 0 if the ID's of both TestingTypes are equal, a negative value if
     *         the provided TestingType has an ID smaller than the ID of this
     *         TestingType, or a positive value if the provided TestingType has
     *         an ID greater than the ID of this TestingType
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