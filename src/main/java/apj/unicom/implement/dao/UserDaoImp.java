package apj.unicom.implement.dao;

import apj.unicom.dao.UserDao;
import apj.unicom.data.Response;
import apj.unicom.data.SqlQuery;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
    final private JdbcTemplate jdbcTemplate;

    public UserDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(String studentId) {
        User user = jdbcTemplate.queryForObject(
                SqlQuery.GET_USER.getQuery(),
                ((rs, rowNum) -> new User(
                        rs.getInt("user_id"),
                        rs.getString("student_id"),
                        rs.getString("user_name"),
                        rs.getBoolean("isPublic"),
                        rs.getString("user_email")
                )),
                studentId);
        user.setChannels(getUserChannels(user.getUserId()));
        return user;
    }

    @Override
    public ArrayList<Channel> getUserChannels(int userId) {
        List<Channel> channels = jdbcTemplate.query(
                SqlQuery.GET_USER_CHANNEL.getQuery(),
                ((rs, rowNum) -> new Channel(
                        rs.getInt("channel_id"),
                        rs.getString("channel_section"),
                        rs.getInt("course_id"),
                        rs.getString("semester_id")
                )),
                userId);
        return channels.isEmpty() ? new ArrayList<>() : new ArrayList<>(channels);
    }

    @Override
    public Response leaveChannel(int userId, int channelId) {
        return null;
    }

    @Override
    public Response joinChannel(int userId, int channelId) {
        return null;
    }
}
