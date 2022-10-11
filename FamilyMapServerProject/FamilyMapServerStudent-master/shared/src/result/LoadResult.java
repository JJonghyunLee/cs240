package result;

/**
 * Object representing the load result
 */
public class LoadResult {
    /**
     * Message displayed when load request is executed
     */
    private String message;
    /**
     * Boolean representing whether the load request succeded or failed
     */
    private boolean success;

    /**
     * Constructor for the load result
     * @param message Message displayed when load request is executed
     * @param success Boolean representing whether the load request succeded or failed
     */
    public LoadResult(String message, boolean success) {
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
