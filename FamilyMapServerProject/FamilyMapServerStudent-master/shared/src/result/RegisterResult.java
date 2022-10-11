package result;

/**
 * Object representing the register result
 * Does not contain a constructor because object will be declared using the success or fail method
 */
public class RegisterResult {
    /**
     * Authtoken returned when request is successful
     */
    private String authtoken;
    /**
     * Username of the registered user
     */
    private String username;
    /**
     * Unique ID of the registered user
     */
    private String personID;
    /**
     * Message displayed when register request fails
     */
    private String message;
    /**
     * Boolean representing whether the request succeeded or failed
     */
    private boolean success;

    /**
     * Constructor called when register request succeeds
     *
     * @param authtoken Authtoken returned when request is successful
     * @param username  Username of the registered user
     * @param personID  Unique ID of the registered user
     * @param success   Boolean representing whether the request succeeded or failed
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /**
     * Constructor called when register request fails
     *
     * @param message Message displayed when register request fails
     * @param success Boolean representing whether the request succeeded or failed
     */
    public RegisterResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
