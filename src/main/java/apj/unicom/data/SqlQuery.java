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
    JOIN_CHANNEL("INSERT INTO channel_member (user_id, channel_id) VALUES (?, ?)"),
    LEAVE_CHANNEL("DELETE FROM channel_member WHERE user_id = ? AND channel_id = ?"),

    //ChannelDao
    GET_CHANNEL("SELECT * FROM channel WHERE channel_id = ?"),
    GET_CHANNEL_MEMBERS("SELECT user_id, student_id, user_name, isPublic, user_email FROM users WHERE " +
            "user_id IN (SELECT user_id FROM channel_member WHERE channel_id = ?)"),
    SEARCH_CHANNEL("SELECT * FROM channel WHERE " +
            "course_id = ? AND channel_section = ? AND semester_id = " +
            "(SELECT semester_id FROM semester WHERE is_current = true)"),
    SEARCH_ARCHIVE("SELECT COUNT(*) FROM user_course_archive WHERE user_id = ? AND course_id = ? AND semester_id = " +
            "(SELECT semester_id FROM semester WHERE is_current = true)"),
    ADD_CHANNEL("INSERT INTO channel (" +
            "course_id, " +
            "channel_section, " +
            "semester_id) VALUES (?,?,(SELECT semester_id FROM semester WHERE is_current = true))"),
    GET_LAST_ID("SELECT MAX(channel_id) FROM channel"),

    //CourseDao
    GET_COURSE("SELECT * FROM courses WHERE course_id = ?"),
    SEARCH_COURSE("SELECT * FROM courses WHERE course_name LIKE ?"),
    ADD_COURSE_ARCHIVE("INSERT INTO user_course_archive (user_id, course_id, semester_id) " +
            "VALUES (?,?,(SELECT semester_id FROM semester WHERE is_current = true))");


    private final String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
