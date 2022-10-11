package result;

/**
 * Object representing the clear result
 */
public class ClearResult {
    /**
     * Message displayed when the request is executed
     */
    private String message;
    /**
     * Boolean representing whether the execution succeeded or not
     */
    private boolean success;

    /**
     * Constructor for the clear result
     * @param message Message displayed when the request is executed
     * @param success Boolean representing whether the execution succeeded or not
     */
    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
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
