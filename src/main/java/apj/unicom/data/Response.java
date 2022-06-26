package apj.unicom.data;

public enum Response {
    SUCCESS("Process Successful"),
    FAILURE("Process Failure"),

    //Login Responses - User Credential Service
    EMPTY_STUDENT_ID("Student ID can't be Empty"),
    INVALID_STUDENT_ID_FORMAT("Invalid Student ID Format"),
    EMPTY_USER_NAME("User Name can't be Empty"),
    INVALID_USER_NAME_LENGTH("User Name parts must be at least 2 characters long"),
    INVALID_USER_NAME_CHARACTER("User Name must be alphabetic characters, '.', ',' and ' '"),
    INVALID_USER_NAME_FIRST_CHARACTER("User Name parts first character must be upper case"),
    EMPTY_USER_PASS("User Pass can't be Empty"),
    INVALID_USER_PASS_LENGTH("User Pass must be at least 5 characters long"),
    INVALID_USER_PASS_CHARACTER("User Pass can't contain spaces"),
    EMPTY_CONFIRM_PASS("Confirm Pass can't be Empty"),
    INVALID_CONFIRM_PASS("Confirm Pass is not match"),

    //Login Responses - User Credential Dao
    STUDENT_ID_NOT_EXIST("Student ID not exist"),
    USER_PASS_NOT_MATCH("User Pass not match"),
    REGISTRATION_FAIL("Registration Fail");

    private final String message;

    Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
