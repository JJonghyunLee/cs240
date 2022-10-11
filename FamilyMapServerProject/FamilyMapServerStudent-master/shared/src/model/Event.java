package model;

import com.google.gson.Gson;

import java.util.Objects;
import java.util.UUID;

/**
 * Model class for the Event object
 */
public class Event {
    /**
     * Event ID of the event
     */
    private String eventID;
    /**
     * Username associated with the event
     */
    private String associatedUsername;
    /**
     * Person ID of the person related to the event
     */
    private String personID;
    /**
     * Latitude location of the event
     */
    private float latitude;
    /**
     * Longitude location of the event
     */
    private float longitude;
    /**
     * Country in which event occurred
     */
    private String country;
    /**
     * City in which event occurred
     */
    private String city;
    /**
     * Type of event
     */
    private String eventType;
    /**
     * Year in which event occurred
     */
    private int year;

    /**
     * Constructor for the Event class.
     *
     * @param eventID            Event ID of the event
     * @param associatedUsername Username associated with the event
     * @param personID           Person ID of the person related to the event
     * @param latitude           Latitude location of the event
     * @param longitude          Longitude location of the event
     * @param country            Country in which event occurred
     * @param city               City in which event occurred
     * @param eventType          Type of event
     * @param year               Year in which event occurred
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    // User birth event constructor
    public Event(Person person, Location location) {
        this.eventID = String.valueOf(UUID.randomUUID());
        this.associatedUsername = person.getAssociatedUsername();
        this.personID = person.getPersonID();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.country = location.getCountry();
        this.city = location.getCity();
        this.eventType = "birth";
        this.year = (int) ((Math.random() * (1970 - 2022)) + 1970);
    }

    // Birth and marriage event constructor
    public Event(Person person, Location location, String type, int year) {
        if (type == "birth") {
            this.eventID = String.valueOf(UUID.randomUUID());
            this.associatedUsername = person.getAssociatedUsername();
            this.personID = person.getPersonID();
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
            this.country = location.getCountry();
            this.city = location.getCity();
            this.eventType = type;
            if (person.getGender() == "f")
                this.year = (int) (Math.random() * ((year - 13) - (year - 50)) + (year - 50));
            else
                this.year = (int) (Math.random() * ((year - 13) - (year - 70)) + (year - 70));
        } else if (type == "marriage") {
            this.eventID = String.valueOf(UUID.randomUUID());
            this.associatedUsername = person.getAssociatedUsername();
            this.personID = person.getPersonID();
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
            this.country = location.getCountry();
            this.city = location.getCity();
            this.eventType = type;
            this.year = year;
        }
    }

    // Death event constructor
    public Event(Person person, Location location, String type, int birthYear, int marriageYear) {
        this.eventID = String.valueOf(UUID.randomUUID());
        this.associatedUsername = person.getAssociatedUsername();
        this.personID = person.getPersonID();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.country = location.getCountry();
        this.city = location.getCity();
        this.eventType = type;
        this.year = (int) (Math.random() * ((birthYear + 120) - (marriageYear)) + marriageYear);
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }
}
