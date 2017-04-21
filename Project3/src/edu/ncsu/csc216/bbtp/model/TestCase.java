package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * The TestCase class stores and maintains information about a test case found
 * in a black box test plan. This includes: its ID, its creation date, its
 * description, its expected results, its actual results, its last testing date,
 * test completion status, and passing status.
 * 
 * @author Brian Morris
 * @author Nat Ellis
 *
 */
public class TestCase extends Observable implements Serializable {

    /** Serial version UID */
    private static final long serialVersionUID = 7459L;

    /** The ID of this TestCase */
    private String testCaseID;
    /** The creation date of this TestCase */
    private Date creationDate;
    /** The description of this TestCase */
    private String description;
    /** The expected results of this TestCase */
    private String expectedResult;
    /** The actual results of this TestCase */
    private String actualResults;
    /** The last testing date of this TestCase */
    private Date lastTestedDate;
    /** Boolean that is true if this TestCase has been tested */
    private boolean testedStatus;
    /** Boolean that is true if this TestCase has passed testing */
    private boolean pass;
    /** The TestingType of this TestCase */
    private TestingType type;

    /**
     * The constructor for TestCase creates a TestCase Object with the provided parameters and
     * notifies the observers of this TestCase. If any of these parameters are null, an empty String,
     * or invalid as determined by the setter methods, an IllegalArgumentException is thrown.
     * @param id the ID of this TestCase
     * @param desc the description of this TestCase
     * @param type the TestingType of this TestCase
     * @param creation the creation date of this TestCase
     * @param exp the expected results of this TestCase
     * @param tested the boolean that represents whether or not this TestCase has been tested
     * @param lastTestDate the last testing date of this TestCase
     * @param act the actual results of this TestCase
     * @param pass the boolean that represents whether or not this TestCase has passed testing
     * @throws IllegalArgumentException if the provided parameters are null, an empty String,
     *         or invalid as determined by the setter methods
     */
    public TestCase(String id, String desc, TestingType type, Date creation, String exp,
                    boolean tested, Date lastTestDate, String act, boolean pass) {
        setTestCaseID(id);
        setDescription(desc);
        setTestingType(type);
        setCreationDateTime(creation);
        setExpectedResults(exp);
        setTestedStatus(tested);
        setLastTestedDateTime(lastTestDate);
        setActualResults(act);
        setPass(pass);

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the creation date of this TestCase.
     * 
     * @return the creation date of this TestCase
     */
    public Date getCreationDateTime() {
        return creationDate;
    }

    /**
     * Changes the creation date of this TestCase to the provided
     * date and notifies the observers of this TestCase. If the provided
     * creation date is null, an IllegalArgumentException is thrown.
     * 
     * @param creationDate the new creation date of this TestCase
     * @throws IllegalArgumentException if the provided creation date is null
     */
    public void setCreationDateTime(Date creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException();
        }

        this.creationDate = creationDate;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the description of this TestCase.
     * 
     * @return the description of this TestCase
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the description of this TestCase to the description provided and
     * notifies the observers of this TestCase. If the given description is
     * null, contains all whitespace, or empty, an IllegalArgumentException
     * is thrown.
     * 
     * @param description the new description of this TestCase
     * @throws IllegalArgumentException if the given description is null, contains
     *         all whitespace, or empty
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.description = description;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the expected results of this TestCase.
     * 
     * @return the expected results of this TestCase
     */
    public String getExpectedResults() {
        return expectedResult;
    }

    /**
     * Changes the expected results of this TestCase to the provided expected
     * results and notifies the observers of this TestCase. If the provided
     * expected results are null, all whitespace, or an empty String, an
     * IllegalArgumentException is thrown.
     * 
     * @param expectedResult the new expected results of this TestCase
     * @throws IllegalArgumentException if the provided expected results are null, all whitespace,
     *         or an empty String
     */
    public void setExpectedResults(String expectedResult) {
        if (expectedResult == null || expectedResult.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.expectedResult = expectedResult;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the actual results of this TestCase.
     * 
     * @return the actual results of this TestCase
     */
    public String getActualResults() {
        return actualResults;
    }

    /**
     * Changes the actual results of this TestCase to the provided actual
     * results and notifies the observers of this TestCase. If the provided
     * actual results are null, all whitespace, or an empty String, an
     * IllegalArgumentException is thrown.
     * 
     * @param actualResults the new actual results of this TestCase
     * @throws IllegalArgumentException if the provided actual results are null, all whitespace,
     *         or an empty String
     */
    public void setActualResults(String actualResults) {
        if (testedStatus && (actualResults == null || actualResults.trim().isEmpty())) {
            throw new IllegalArgumentException();
        }

        this.actualResults = actualResults;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the date that this TestCase was last tested.
     * 
     * @return the date that this TestCase was last tested.
     */
    public Date getLastTestedDateTime() {
        return lastTestedDate;
    }

    /**
     * Changes the last testing date of this TestCase to the date provided and notifies
     * the observers of this TestCase. If this provided date is null and this TestCase
     * has not been marked as tested, an IllegalArgumentException is thrown.
     * 
     * @param lastTestedDate the updated last testing date of this TestCase
     * @throws IllegalArgumentException if the provided date is null and
     *         this TestCase has not been marked as tested
     */
    public void setLastTestedDateTime(Date lastTestedDate) {
        if (lastTestedDate == null && testedStatus) {
            throw new IllegalArgumentException();
        }

        this.lastTestedDate = lastTestedDate;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns true if this TestCase has passed testing, false if not.
     * 
     * @return true if this TestCase has passed testing, false if not
     */
    public boolean pass() {
        return pass;
    }

    /**
     * Changes the pass field to the boolean provided and notifies the
     * observers of this TestCase.
     * 
     * @param pass the updated passing status of this TestCase
     */
    public void setPass(boolean pass) {
        this.pass = pass;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the TestingType of this TestCase.
     * 
     * @return the TestingType of this TestCase
     */
    public TestingType getTestingType() {
        return type;
    }

    /**
     * Changes the TestingType of this TestCase to the TestingType provided and notifies
     * the observers of this TestCase. If the TestingType that is provided is null,
     * an IllegalArgumentException is thrown.
     * 
     * @param type the new TestingType of this TestCase
     * @throws IllegalArgumentException if the provided TestingType is null
     */
    public void setTestingType(TestingType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }

        this.type = type;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns true if this TestCase has been tested, false if not.
     * 
     * @return true if this TestCase has been tested, false if not
     */
    public boolean tested() {
        return testedStatus;
    }

    /**
     * Changes the testedStatus field to the boolean provided and notifies the
     * observers of this TestCase.
     * 
     * @param testedStatus the updated testing status of this TestCase
     */
    public void setTestedStatus(boolean testedStatus) {
        this.testedStatus = testedStatus;

        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns the ID of this TestCase.
     * 
     * @return the ID of this TestCase
     */
    public String getTestCaseID() {
        return testCaseID;
    }

    /**
     * Changes the ID of this TestCase to the provided ID and notifies
     * the observers of this TestCase. If the provided ID is null or an
     * empty String, an IllegalArgumentException is thrown.
     * 
     * @param testCaseID the new ID of this TestCase
     * @throws IllegalArgumentException if the provided ID is null or an empty
     *         String
     */
    private void setTestCaseID(String testCaseID) {
        if (testCaseID == null || testCaseID.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.testCaseID = testCaseID;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Generates and returns the hash code for this TestCase.
     * @return the hash code for this TestCase
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

    /**
     * Returns true if the provided Object is equal to this TestCase.
     * @return true if the provided Object is equal to this TestCase
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
        if (testCaseID == null) {
            if (other.testCaseID != null)
                return false;
        } else if (!testCaseID.equals(other.testCaseID))
            return false;

        return true;
    }

    /**
     * Compares the last testing date of this TestCase to that of the given TestCase.
     * Returns a negative value if the testing data of the provided TestCase is less
     * than the testing date of this TestCase, a positive value if it is greater than
     * the testing date of this TestCase, or 0 if the testing dates are both equal. If the
     * provided TestCase is null, a NullPointerException is thrown.
     * 
     * @param input the TestCase to compare with this TestCase
     * @return 0 if the testing dates of both TestCases are equal, a negative value if
     *         the provided TestCase has a testing date smaller than the testing date of this
     *         TestCase, or a positive value if the provided TestCase has
     *         a testing date greater than the testing date of this TestCase
     */
    public int compareTo(TestCase input) {
        if (input == null) {
            throw new NullPointerException();
        }

        if (this.getLastTestedDateTime() == null) {
            if (input.getLastTestedDateTime() == null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (input.getLastTestedDateTime() == null) {
                return -1;
            } else {
                return this.getLastTestedDateTime().compareTo(input.getLastTestedDateTime());
            }
        }
    }

}
