package request;

/**
 * Object representing the event request
 * Returns an event associated with the provided event ID
 */
public class EventRequest {
    /**
     * Unique ID of the event
     */
    private String eventID;

    /**
     * Constructor of the event request
     * @param eventID Unique ID of the event
     */
    public EventRequest(String eventID) {
        this.eventID = eventID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
