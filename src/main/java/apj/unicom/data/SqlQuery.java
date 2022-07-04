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

    UPDATE_USER("UPDATE users SET " +
            "user_name = ?, " +
            "isPublic = ?, " +
            "user_email = ?, " +
            "user_pass = ? " +
            "WHERE student_id = ?"),

    DELETE_USER("Delete FROM users WHERE student_id = ?"),
        //UserDao

    GET_USER("SELECT user_id, student_id, user_name, isPublic, user_email FROM users WHERE student_id = ?"),
    GET_USER_CHANNEL("SELECT * FROM channel WHERE " +
                "channel_id IN (SELECT channel_id FROM channel_member WHERE user_id = ?) " +
                "AND semester_id = (SELECT semester_id FROM semester WHERE is_current = true)"),

    //ChannelDao
    GET_CHANNEL("SELECT * FROM channel WHERE channel_id = ?"),
    GET_CHANNEL_MEMBERS("SELECT user_id, student_id, user_name, isPublic, user_email FROM users WHERE " +
            "user_id IN (SELECT user_id FROM channel_member WHERE channel_id = ?)"),

    //CourseDao
    GET_COURSE("SELECT * FROM courses WHERE course_id = ?");

    private final String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
