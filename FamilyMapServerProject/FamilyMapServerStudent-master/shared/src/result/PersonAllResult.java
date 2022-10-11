package result;

import model.Person;

import java.util.List;

/**
 * Object representing the family result
 */
public class PersonAllResult {
    /**
     * List of family members of the current user
     */
    private List<Person> data;
    /**
     * Message displayed when the request fails
     */
    private String message;
    /**
     * Boolean representing whether the request succeeded or failed
     */
    private boolean success;

    /**
     * Constructor for the family result, called when request succeeds
     *
     * @param personList List of family members of the current user
     * @param success    Boolean representing whether the request succeeded or failed
     */
    public PersonAllResult(List<Person> personList, boolean success) {
        this.data = personList;
        this.success = success;
    }

    /**
     * Constructor for the family result, called when request fails
     *
     * @param message List of family members of the current user
     * @param success Boolean representing whether the request succeeded or failed
     */
    public PersonAllResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public List<Person> getPersonList() {
        return data;
    }

    public void setPersonList(List<Person> personList) {
        this.data = personList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
