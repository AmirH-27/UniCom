package apj.unicom.implement.dao;

import apj.unicom.dao.ChannelDao;
import apj.unicom.data.Response;
import apj.unicom.data.SqlQuery;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ChannelDaoImp implements ChannelDao {
    JdbcTemplate jdbcTemplate;

    public ChannelDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Response addChannel(int courseId, String channelSection) {
        return null;
    }

    @Override
    public Channel getChannel(int channelId) {
        return null;
    }

    @Override
    public Channel searchChannel(int courseId, String channelSection) {
        return null;
    }

    @Override
    public ArrayList<User> getChannelMembers(int channelId) {
        List<User> members = jdbcTemplate.query(
                SqlQuery.GET_CHANNEL_MEMBERS.getQuery(),
                ((rs, rowNum) -> new User(
                        rs.getInt("user_id"),
                        rs.getString("student_id"),
                        rs.getString("user_name"),
                        rs.getBoolean("isPublic"),
                        rs.getString("user_email")
                )),
                channelId);
        return members.isEmpty() ? new ArrayList<>() : new ArrayList<>(members);
    }
}