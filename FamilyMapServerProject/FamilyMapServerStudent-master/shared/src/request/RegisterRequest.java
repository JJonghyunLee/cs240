package request;

/**
 * Object representing the register request
 * Adds new user to the database
 */
public class RegisterRequest {
    /**
     * Username of the user
     */
    private String username;
    /**
     * Password of the user
     */
    private String password;
    /**
     * Email of the user
     */
    private String email;
    /**
     * First name of the user
     */
    private String firstName;
    /**
     * Last name of the user
     */
    private String lastName;
    /**
     * Gender of the user
     */
    private String gender;

    /**
     * Constructor for the register request
     *
     * @param username  Username of the user
     * @param password  Password of the user
     * @param email     Email of the user
     * @param firstName First name of the user
     * @param lastName  Last name of the user
     * @param gender    Gender of the user
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    public void setPw(String pw) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
