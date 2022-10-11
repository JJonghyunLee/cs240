package result;

import model.Event;

import java.util.List;

/**
 * Object representing the user event result
 */
public class EventAllResult {
    /**
     * List of events associated with the user
     */
    private List<Event> data;
    /**
     * Message displayed when request fails
     */
    private String message;
    /**
     * Boolean representing whether the request succeeded or failed
     */
    private boolean success;

    /**
     * Constructor of the user event result, called if request succeeds
     *
     * @param eventList List of events associated with the user
     * @param success   Boolean representing whether the request succeeded or failed
     */
    public EventAllResult(List<Event> eventList, boolean success) {
        this.data = eventList;
        this.success = success;
    }

    /**
     * Constructor of the user event result, called if request fails
     *
     * @param message Message displayed when request fails
     * @param success Boolean representing whether the request succeeded or failed
     */
    public EventAllResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public List<Event> getEventList() {
        return data;
    }

    public void setEventList(List<Event> eventList) {
        this.data = eventList;
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
