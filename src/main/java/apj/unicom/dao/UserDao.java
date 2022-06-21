package apj.unicom.dao;

import apj.unicom.domain.User;
import apj.unicom.utilities.UserDaoUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserDao implements UserDaoUtil {

    final private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public boolean checkUserEmail(String userEmail) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userEmail) == 1;
    }

    @Override
    public boolean checkUserCredential(String userEmail, String userPass) {
        return false;
    }

    @Override
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (" +
                "student_id," +
                "user_name," +
                "user_email," +
                "user_pass) VALUES (?,?,?,?)";

        int insertStatus = jdbcTemplate.update(
                sql,
                user.getStudentId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPass()
        );
        return insertStatus==1;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
