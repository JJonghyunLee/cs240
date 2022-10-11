package result;

/**
 * Object class representing the login result
 */
public class LoginResult {
    /**
     * Authtoken of the user attempting login
     */
    private String authtoken;
    /**
     * Username of the user attempting login
     */
    private String username;
    /**
     * Person ID of the user attempting login
     */
    private String personID;
    /**
     * Message displayed when login request fails
     */
    private String message;
    /**
     * Boolean representing whether the request succeeded or failed
     */
    private boolean success;

    /**
     * Constructor called when request is successful
     *
     * @param authtoken Authtoken of the user attempting login
     * @param username  Username of the user attempting login
     * @param personID  Person ID of the user attempting login
     * @param success   Boolean representing whether the request succeeded or failed
     */
    public LoginResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    /**
     * Constructor called when request fails
     *
     * @param message Message displayed when login request fails
     * @param success Boolean representing whether the request succeeded or failed
     */
    public LoginResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
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
