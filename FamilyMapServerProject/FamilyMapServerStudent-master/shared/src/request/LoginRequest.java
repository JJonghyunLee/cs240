package request;

/**
 * Object representing the login request
 * User attempts to login
 */
public class LoginRequest {
    /**
     * Username of the user attempting login
     */
    private String username;

    /**
     * Password of the user attempting login
     */
    private String password;

    /**
     * Constructor for the login request
     *
     * @param username Username of the user attempting login
     * @param password Password of the user attempting login
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
