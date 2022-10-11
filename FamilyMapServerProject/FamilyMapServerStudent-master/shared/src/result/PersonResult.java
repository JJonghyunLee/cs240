package result;

import model.Person;

public class PersonResult {
    /**
     * Username associated with the person
     */
    private String associatedUsername;
    /**
     * Unique ID of the person
     */
    private String personID;
    /**
     * First name of the person
     */
    private String firstName;
    /**
     * Last name of the person
     */
    private String lastName;
    /**
     * Gender of the person
     */
    private String gender;
    /**
     * Unique ID of the mother of the person
     * May be null
     */
    private String motherID = null;
    /**
     * Unique ID of the father of the person
     * May be null
     */
    private String fatherID = null;
    /**
     * Unique ID of the spouse of the person
     * May be null
     */
    private String spouseID = null;
    /**
     * Boolean representing whether the request succeeded or failed
     */
    private String message;
    boolean success;

    public PersonResult(Person person, boolean success) {
        this.associatedUsername = person.getAssociatedUsername();
        this.personID = person.getPersonID();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.motherID = person.getMotherID();
        this.fatherID = person.getFatherID();
        this.spouseID = person.getSpouseID();
        this.success = success;
    }

    public PersonResult(String associatedUsername, String personID, String firstName, String lastName,
                        String gender, String motherID, String fatherID, String spouseID, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.motherID = motherID;
        this.fatherID = fatherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    public PersonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
