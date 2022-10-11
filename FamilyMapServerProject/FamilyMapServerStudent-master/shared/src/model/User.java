package model;

import request.RegisterRequest;

import java.util.Objects;
import java.util.UUID;

/**
 * Model class for the User object
 */
public class User {
    /**
     * Username of the user
     */
    private String username;
    /**
     * Password of the user
     */
    private String password;
    /**
     * Email address of the user
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
     * Person ID of the user
     */
    private String personID;

    /**
     * Constructor for the User class
     *
     * @param username  Username of the user
     * @param password  Password of the user
     * @param email     Email address of the user
     * @param firstName First name of the user
     * @param lastName  Last name of the user
     * @param gender    Gender of the user
     * @param personID  Person ID of the user
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public User(RegisterRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.email = request.getEmail();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.gender = request.getGender();
        this.personID = String.valueOf(UUID.randomUUID());


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

    public void setPassword(String pw) {
        this.username = pw;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(personID, user.personID);
    }
}
