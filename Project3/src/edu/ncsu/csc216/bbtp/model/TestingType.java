package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;
/**
 * A basic java object for testing types
 * @author Brian and Nat
 *
 */
public class TestingType extends Observable implements Serializable
{

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
	 * @param name of the type
	 * @param description of the type
	 * @param id of the type
	 */
	public TestingType(String name, String description, String id)
	{
		
	}

	/**
	 * returns the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * returns the testing type
	 * @return the testingTypeId
	 */
	public String getTestingTypeId() {
		return testingTypeId;
	}

	/**
	 * sets the testing type id
	 * @param testingTypeId the testingTypeId to set
	 */
	private void setTestingTypeId(String testingTypeId) {
		this.testingTypeId = testingTypeId;
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (testingTypeId == null) {
			if (other.testingTypeId != null)
				return false;
		} else if (!testingTypeId.equals(other.testingTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestingType [name=" + name + ", description=" + description + ", testingTypeId=" + testingTypeId + "]";
	}
	
	/**
	 * compares this test type to input
	 * @param input to compare this with
	 * @return 1 if true 
	 */
	public int compareTo(TestingType input)
	{
		return 0;
	}
	
	
}
