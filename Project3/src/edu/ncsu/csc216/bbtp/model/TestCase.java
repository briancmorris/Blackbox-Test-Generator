package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * A basic java object for test cases
 * @author Brian and Nat
 *
 */
public class TestCase extends Observable implements Serializable
{

	/** Serial version UID. */
	private static final long serialVersionUID = 7459L;
	
	/**
	 * holds the test id
	 */
	private String testCaseID;
	/**
	 * holds the creation date
	 */
	private Date creationDate;
	/**
	 * holds the description
	 */
	private String description;
	/**
	 * holds the expected results
	 */
	private String expectedResult;
	/**
	 * holds the actual results
	 */
	private String actualResults;
	/**
	 * holds the last test date
	 */
	private Date lastTestedDate;
	/**
	 * holds boolean for if it has been tested
	 */
	private boolean testedStatus;
	/**
	 * holds boolean for if it is passing
	 */
	private boolean pass;
	/**
	 * holds the test type
	 */
	private TestingType type;
	
	/**
	 * constructor for Test case
	 * @param id the id of the test
	 * @param desc description of the test
	 * @param type type of the test
	 * @param creation date test was made
	 * @param exp expected results
	 * @param tested true if it has been tested
	 * @param lastTestDate last tested date
	 * @param act actual results
	 * @param pass true if it passed
	 */
	public TestCase(String id, String desc, TestingType type, Date creation, 
			 		String exp, boolean tested, Date lastTestDate,
			 		String act, boolean pass)
	{
		
	}


	/**
	 * returns the creation date
	 * @return the creationDate
	 */
	public Date getCreationDateTime() {
		return creationDate;
	}


	/**
	 * sets the creation date
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDateTime(Date creationDate) {
		this.creationDate = creationDate;
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
	 * returns the expected results
	 * @return the expectedResult
	 */
	public String getExpectedResults() {
		return expectedResult;
	}


	/**
	 * sets the expected results
	 * @param expectedResult the expectedResult to set
	 */
	public void setExpectedResults(String expectedResult) {
		this.expectedResult = expectedResult;
	}


	/**
	 * returns the actual results
	 * @return the actualResults
	 */
	public String getActualResults() {
		return actualResults;
	}


	/**
	 * sets the actual results
	 * @param actualResults the actualResults to set
	 */
	public void setActualResults(String actualResults) {
		this.actualResults = actualResults;
	}


	/**
	 * returns the last tested date
	 * @return the lastTestedDate
	 */
	public Date getLastTestedDateTime() {
		return lastTestedDate;
	}


	/**
	 * sets the last tested date
	 * @param lastTestedDate the lastTestedDate to set
	 */
	public void setLastTestedDateTime(Date lastTestedDate) {
		this.lastTestedDate = lastTestedDate;
	}

	/**
	 * returns true if it is passing
	 * @return the pass
	 */
	public boolean pass() {
		return pass;
	}


	/**
	 * sets true if it is passing
	 * @param pass the pass to set
	 */
	public void setPass(boolean pass) {
		this.pass = pass;
	}


	/**
	 * returns the type
	 * @return the type
	 */
	public TestingType getTestingType() {
		return type;
	}


	/**
	 * sets the type
	 * @param type the type to set
	 */
	public void setTestingType(TestingType type) {
		this.type = type;
	}


	/**
	 * returns the tested status
	 * @return the testedStatus
	 */
	public boolean tested() {
		return testedStatus;
	}


	/**
	 * sets the tested status
	 * @param testedStatus the testedStatus to set
	 */
	public void setTestedStatus(boolean testedStatus) {
		this.testedStatus = testedStatus;
	}


	/**
	 * returns the test case id
	 * @return the testCaseID
	 */
	public String getTestCaseID() {
		return testCaseID;
	}


	/**
	 * sets the test case id
	 * @param testCaseID the testCaseID to set
	 */
	private void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualResults == null) ? 0 : actualResults.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expectedResult == null) ? 0 : expectedResult.hashCode());
		result = prime * result + ((lastTestedDate == null) ? 0 : lastTestedDate.hashCode());
		result = prime * result + (pass ? 1231 : 1237);
		result = prime * result + ((testCaseID == null) ? 0 : testCaseID.hashCode());
		result = prime * result + (testedStatus ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TestCase other = (TestCase) obj;
		if (actualResults == null) {
			if (other.actualResults != null)
				return false;
		} else if (!actualResults.equals(other.actualResults))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expectedResult == null) {
			if (other.expectedResult != null)
				return false;
		} else if (!expectedResult.equals(other.expectedResult))
			return false;
		if (lastTestedDate == null) {
			if (other.lastTestedDate != null)
				return false;
		} else if (!lastTestedDate.equals(other.lastTestedDate))
			return false;
		if (pass != other.pass)
			return false;
		if (testCaseID == null) {
			if (other.testCaseID != null)
				return false;
		} else if (!testCaseID.equals(other.testCaseID))
			return false;
		if (testedStatus != other.testedStatus)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	/**
	 * compares this test case to input
	 * @param input to compare this with
	 * @return 1 if true 
	 */
	public int compareTo(TestCase input)
	{
		return 0;
	}
	
}
