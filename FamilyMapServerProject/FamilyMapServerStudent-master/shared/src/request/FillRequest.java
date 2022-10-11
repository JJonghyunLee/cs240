package request;

/**
 * Object representing the fill request
 * Generates ancestors for a user and populates the database
 */
public class FillRequest {
    /**
     * Username of the user
     */
    private String username;
    /**
     * Number of generations generated to populate database (base of 4 if not specified)
     */
    private int numGeneration;

    /**
     * Constructor for the fill request
     * @param username
     * @param numGeneration
     */
    public FillRequest(String username, int numGeneration) {
        this.username = username;
        this.numGeneration = numGeneration;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumGeneration() {
        return numGeneration;
    }
    public void setNumGeneration(int numGeneration) {
        this.numGeneration = numGeneration;
    }
}
