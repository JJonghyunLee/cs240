package request;

/**
 * Object representing the person request
 * Returns a single person using a provided person ID
 */
public class PersonRequest {
    /**
     * Unique ID of the person being found
     */
    private String personID;

    /**
     * Constructor for the person request
     * @param personID Unique ID of the person being found
     */
    public PersonRequest(String personID) {
        this.personID = personID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
