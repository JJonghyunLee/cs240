package model;

import request.RegisterRequest;

import java.util.UUID;

/**
 * Model class for the Authtoken object
 */
public class Authtoken {
    /**
     * Unique authtoken
     */
    private String authtoken;
    /**
     * Username that is associated with the authtoken
     */
    private String username;

    /**
     * Constructor for the AuthToken class
     *
     * @param authToken Unique authtoken
     * @param username  Username associated with the authtoken
     */
    public Authtoken(String authToken, String username) {
        this.authtoken = authToken;
        this.username = username;
    }

    public Authtoken(String username) {
        this.authtoken = String.valueOf(UUID.randomUUID());
        this.username = username;
    }

    public Authtoken(RegisterRequest request) {
        this.authtoken = String.valueOf(UUID.randomUUID());
        this.username = request.getUsername();
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authToken) {
        this.authtoken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
