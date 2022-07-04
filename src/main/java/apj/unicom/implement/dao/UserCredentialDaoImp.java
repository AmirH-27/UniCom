package apj.unicom.implement.dao;

import apj.unicom.dao.UserCredentialDao;
import apj.unicom.data.Response;
import apj.unicom.data.SqlQuery;
import apj.unicom.domain.UserCredential;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserCredentialDaoImp implements UserCredentialDao {

    final private JdbcTemplate jdbcTemplate;

    public UserCredentialDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Response checkUserStudentId(String userStudentId) {
        return jdbcTemplate.queryForObject(SqlQuery.CHECK_STUDENT_ID.getQuery(), Integer.class, userStudentId) == 1
                ? Response.SUCCESS : Response.STUDENT_ID_NOT_EXIST;
    }

    @Override
    public Response checkUserCredential(String userStudentId, String userPass) {
        return jdbcTemplate.queryForObject(SqlQuery.CHECK_USER_PASS.getQuery(), Integer.class, userStudentId, userPass) == 1
                ? Response.SUCCESS : Response.USER_PASS_NOT_MATCH;
    }

    @Override
    public Response registerUser(UserCredential userCredential) {
        int insertStatus = jdbcTemplate.update(
                SqlQuery.REGISTER_USER.getQuery(),
                userCredential.getStudentId(),
                userCredential.getUserName(),
                userCredential.isPublic(),
                userCredential.getUserEmail(),
                userCredential.getUserPass()
        );
        return insertStatus==1 ? Response.SUCCESS : Response.REGISTRATION_FAIL;
    }

    @Override
    public Response updateUser(UserCredential userCredential) {
        int updateStatus = jdbcTemplate.update(
                SqlQuery.UPDATE_USER.getQuery(),
                userCredential.getUserName(),
                userCredential.isPublic(),
                userCredential.getUserEmail(),
                userCredential.getUserPass(),
                userCredential.getStudentId()
        );
        return updateStatus == 1 ? Response.SUCCESS : Response.UPDATE_FAIL;
    }

    @Override
    public Response deleteUser(UserCredential userCredential) {
        int deleteStatus = jdbcTemplate.update(
                SqlQuery.DELETE_USER.getQuery(),
                userCredential.getStudentId()
        );
        return deleteStatus == 1 ? Response.SUCCESS : Response.UPDATE_FAIL;
    }
}

