package iserba.dao.jdbc;

import iserba.dao.UserDAO;
import iserba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        int n = jdbcTemplate.update("INSERT INTO users(id, name, email, password) VALUES(?, ?, ?, ?)",
                user.getId(), user.getName(), user.getEmail(), user.getPassword());

        System.out.println("________________________________ result of user save = "+ n+" user_id= "+user.getId());
        return user;
    }

    @Override
    public User get(int id) {
        System.out.println("_________________in userDAO.get id="+id);
        User user = this.jdbcTemplate.queryForObject(
                "select * from users where id = ?",
                new Object[]{id},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        System.out.println(id + " " + rs.getString("name") + " " + rs.getString("email") + rs.getString("password"));
                        user.setId(id);
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
        System.out.println("______________________ get name="+user.getName());
        return user;

    }

    @Override
    public boolean delete(int id) {
        this.jdbcTemplate.update(
                "delete from users where id = ?", id);
        return true;
    }

    @Override
    public User getByEmail(String email) {
        return this.jdbcTemplate.queryForObject(
                "select id, name, password from users where email = ?",
                new Object[]{email},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString(email));
                    user.setPassword(rs.getString("password"));
                    return user;
                });
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(
                "select id, name, email, password from users",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    return user;
                });
    }
}
