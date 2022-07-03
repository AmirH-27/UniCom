package apj.unicom.data;

public enum SqlQuery {
    //UserCredentialDao
    CHECK_STUDENT_ID("SELECT COUNT(*) FROM users WHERE student_id = ?"),
    CHECK_USER_PASS("SELECT COUNT(*) FROM users WHERE student_id = ? AND user_pass = ?"),
    REGISTER_USER("INSERT INTO users (" +
            "student_id," +
            "user_name," +
            "isPublic, " +
            "user_email," +
            "user_pass) VALUES (?,?,?,?,?)"),

    UPDATE_USER("UPDATE users (" +
            "student_id," +
            "user_name," +
            "isPublic, " +
            "user_email," +
            "user_pass) VALUES (?,?,?,?,?) WHERE student_id = ?"),

    //UserDao
    GET_USER("SELECT user_id, student_id, user_name, isPublic, user_email FROM users WHERE student_id = ?");

    private final String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
