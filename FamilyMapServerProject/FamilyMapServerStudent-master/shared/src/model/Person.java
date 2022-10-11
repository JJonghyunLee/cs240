package model;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Model class for the Person object.
 */
public class Person {
    /**
     * Person ID of the person
     */
    private String personID;
    /**
     * Username associated with the person
     */
    private String associatedUsername;
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
     * Person ID of the father of the person
     */
    private String fatherID = null;
    /**
     * Person ID of the mother of the person
     */
    private String motherID = null;
    /**
     * Person ID of the spouse of the person
     */
    private String spouseID = null;

    /**
     * Constructor for the Person class
     *
     * @param personID           Person ID of the person
     * @param associatedUsername Username associated with the person
     * @param firstName          First name of the person
     * @param lastName           Last name of the person
     * @param gender             Gender of the person
     * @param fatherID           Person ID of the father of the person
     * @param motherID           Person ID of the mother of the person
     * @param spouseID           Person ID of the spouse of the person
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName,
                  String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;

    }

    public Person(User user) {
        this.personID = user.getPersonID();
        this.associatedUsername = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
    }


    public Person(String username, String gender) {
        NameData nameData;
        NameData sNameData;
        Gson gson = new Gson();
        List<String> nameList;
        List<String> sNameList;
        File filePath;
        try {
            if (gender == "m") {
                filePath = new File("C:\\Users\\이종현\\FamilyMapServerProject\\FamilyMapServerStudent-master\\json\\mnames.json");
            } else {
                filePath = new File("C:\\Users\\이종현\\FamilyMapServerProject\\FamilyMapServerStudent-master\\json\\fnames.json");
            }

            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            nameData = gson.fromJson(br, NameData.class);

            filePath = new File("C:\\Users\\이종현\\FamilyMapServerProject\\FamilyMapServerStudent-master\\json\\snames.json");
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            sNameData = gson.fromJson(br, NameData.class);

            nameList = nameData.getList();
            sNameList = sNameData.getList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: File not found.");
            return;
        }
        this.personID = String.valueOf(UUID.randomUUID());
        this.associatedUsername = username;
        this.gender = gender;
        Random rand = new Random();
        this.firstName = nameList.get(rand.nextInt(nameList.size()));
        this.lastName = sNameList.get(rand.nextInt(sNameList.size()));
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) &&
                Objects.equals(associatedUsername, person.associatedUsername) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(fatherID, person.fatherID) &&
                Objects.equals(motherID, person.motherID) &&
                Objects.equals(spouseID, person.spouseID);
    }
}