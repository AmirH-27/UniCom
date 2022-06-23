package apj.unicom.data;

public enum Error {
    INVALID_STUDENT_ID("Invalid Student ID"),
    INVALID_USER_NAME("Invalid User Name");

    private String errorMessage;
    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
