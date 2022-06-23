package apj.unicom.implement.dao;

import apj.unicom.dao.UserCredentialDao;
import apj.unicom.domain.UserCredential;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserCredentialDaoImp implements UserCredentialDao {

    final private JdbcTemplate jdbcTemplate;

    public UserCredentialDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean checkUserStudentId(String userStudentId) {
        String sql = "SELECT COUNT(*) FROM users WHERE student_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userStudentId) == 1;
    }

    @Override
    public boolean checkUserCredential(String userStudentId, String userPass) {
        String sql = "SELECT COUNT(*) FROM users WHERE student_id = ? AND user_pass = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userStudentId, userPass) == 1;
    }

    @Override
    public boolean registerUser(UserCredential userCredential) {
        String sql = "INSERT INTO users (" +
                "student_id," +
                "user_name," +
                "public, " +
                "user_email," +
                "user_pass) VALUES (?,?,?,?,?)";

        int insertStatus = jdbcTemplate.update(
                sql,
                userCredential.getStudentId(),
                userCredential.getUserName(),
                userCredential.isPublic(),
                userCredential.getUserEmail(),
                userCredential.getUserPass()
        );
        return insertStatus==1;
    }

    @Override
    public boolean updateUser(UserCredential userCredential) {
        return false;
    }

    @Override
    public boolean deleteUser(UserCredential userCredential) {
        return false;
    }
}

