package apj.unicom.implement.dao;

import apj.unicom.dao.ChannelDao;
import apj.unicom.data.Response;
import apj.unicom.data.SqlQuery;
import apj.unicom.domain.Channel;
import apj.unicom.domain.Course;
import apj.unicom.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

public class ChannelDaoImp implements ChannelDao {
    JdbcTemplate jdbcTemplate;

    public ChannelDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Response addChannel(int courseId, String channelSection) {
        int insertStatus = jdbcTemplate.update(
                SqlQuery.ADD_CHANNEL.getQuery(),
                courseId,
                channelSection);
        return insertStatus==1 ? Response.SUCCESS : Response.CREAT_CHANNEL_FAIL;
    }

    @Override
    public Channel getChannel(int channelId) {
        return jdbcTemplate.queryForObject(
                SqlQuery.GET_CHANNEL.getQuery(),
                ((rs, rowNum) -> new Channel(
                        rs.getInt("channel_id"),
                        rs.getString("channel_section"),
                        rs.getInt("course_id"),
                        rs.getString("semester_id")
                )),
                channelId);
    }

    @Override
    public Map<String, Object> searchChannel(Course course, String channelSection, int userId) {
        Channel channel;
        Response response;
        try{
            channel = jdbcTemplate.queryForObject(
                    SqlQuery.SEARCH_CHANNEL.getQuery(),
                    (rs, rowNum) -> new Channel(
                            rs.getInt("channel_id"),
                            rs.getString("channel_section"),
                            rs.getInt("course_id"),
                            rs.getString("semester_id")),
                    course.getCourseId(), channelSection);
            response = Response.CHANNEL_EXIST;
        }catch (Exception e){
            channel = new Channel();
            channel.setCourseId(course.getCourseId());
            channel.setChannelSection(channelSection);
            channel.setCourse(course);
            channel.setMembers(new ArrayList<>());
            response = Response.CHANNEL_NOT_EXIST;
        }
        if(response == Response.CHANNEL_EXIST){
            int count = jdbcTemplate.queryForObject(
                    SqlQuery.SEARCH_ARCHIVE.getQuery(),
                    Integer.class,
                    userId,
                    course.getCourseId());
            if(count == 1){
                response = Response.CHANNEL_ARCHIVED;
            }
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("channel", channel);
        map.put("response", response);
        return map;
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