package apj.unicom.implement.dao;

import apj.unicom.dao.UserDao;
import apj.unicom.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserDaoImp implements UserDao {
    final private JdbcTemplate jdbcTemplate;

    public UserDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(String studentId) {
        String sql = "SELECT user_id, student_id, user_name, isPublic, user_email FROM users WHERE student_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                ((rs, rowNum) -> new User(
                        rs.getInt("user_id"),
                        rs.getString("student_id"),
                        rs.getString("user_name"),
                        rs.getBoolean("isPublic"),
                        rs.getString("user_email")
                )),
                studentId);
    }
}
