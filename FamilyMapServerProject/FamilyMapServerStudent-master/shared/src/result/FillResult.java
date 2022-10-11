package result;

/**
 * Object representing the fill result
 */
public class FillResult {
    /**
     * Message displayed when request is executed
     */
    private String message;
    /**
     * Boolean representing whether request succeeded or failed
     */
    private boolean success;

    /**
     * Constructor for the fill result
     *
     * @param message Message displayed when request is executed
     * @param success Boolean representing whether request succeeded or failed
     */
    public FillResult(String message, boolean success) {
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

