package result;

import model.Event;

/**
 * Object representing the event result
 */
public class EventResult {
    /**
     * Username associated with the event
     */
    private String associatedUsername;
    /**
     * Unique ID of the event
     */
    private String eventID;
    /**
     * Unique ID of the person of the event
     */
    private String personID;
    /**
     * Latitude location of the event
     */
    private float latitude;
    /**
     * Latitude location of the event
     */
    private float longitude;
    /**
     * Country in which the event occurs
     */
    private String country;
    /**
     * City in which the event occurs
     */
    private String city;
    /**
     * Type of the event
     */
    private String eventType;
    /**
     * Year in which the event occurred
     */
    private int year;
    /**
     * Message displayed when the request fails
     */
    private String message;
    /**
     * Boolean representing whether the request was successful or not
     */
    private boolean success;

    /**
     * Constructor for the event request, called if request succeeds
     *
     * @param associatedUsername Username associated with the event
     * @param eventID            Unique ID of the event
     * @param personID           Unique ID of the person of the event
     * @param latitude           Latitude location of the event
     * @param longitude          Latitude location of the event
     * @param country            Country in which the event occurs
     * @param city               City in which the event occurs
     * @param eventType          Type of the event
     * @param year               Year in which the event occurred
     * @param success            Boolean representing whether the request was successful or not
     */
    public EventResult(String associatedUsername, String eventID, String personID, float latitude, float longitude,
                       String country, String city, String eventType, int year, boolean success) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    public EventResult(Event event, boolean success) {
        this.associatedUsername = event.getAssociatedUsername();
        this.eventID = event.getEventID();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
        this.success = success;
    }

    /**
     * Constructer of the event result, called if request fails
     *
     * @param message Message displayed when the request fails
     * @param success Boolean representing whether the request was successful or not
     */
    public EventResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
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
