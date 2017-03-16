package iserba.dao.jdbc;

import iserba.Profiles;
import iserba.dao.UserDAO;
import iserba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
@Profile(Profiles.JDBC)
public class UserDAOImpl implements UserDAO{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public User save(User user) {
        int n = jdbcTemplate.update("INSERT INTO users(name, email, password) VALUES(?, ?, ?)",
                user.getName(), user.getEmail(), user.getPassword());
        return user;
    }

    @Override
    public User update(User user) {
        int n = jdbcTemplate.update("UPDATE users SET name = ?, email = ?, password = ? WHERE user_id = ?",
                user.getName(), user.getEmail(), user.getPassword(), user.getId());
        return user;
    }

    @Override
    @Transactional
    public User get(int id) {
        User user = this.jdbcTemplate.queryForObject(
                "select * from users where user_id = ?",
                new Object[]{id},
                (rs, rowNum) -> {
                    User user1 = new User();
                    user1.setId(id);
                    user1.setName(rs.getString("name"));
                    user1.setEmail(rs.getString("email"));
                    user1.setPassword(rs.getString("password"));
                    return user1;
                });
        return user;

    }

    @Override
    @Transactional
    public boolean delete(int id) {
        this.jdbcTemplate.update(
                "delete from quotations where user_id = ?", id);
        this.jdbcTemplate.update(
                "delete from users where user_id = ?", id);
        return true;
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return this.jdbcTemplate.queryForObject(
                "select user_id, name, password from users where email = ?",
                new Object[]{email},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString(email));
                    user.setPassword(rs.getString("password"));
                    return user;
                });
    }

    @Override
    @Transactional
    public User getByName(String name) {
        return this.jdbcTemplate.queryForObject(
                "select user_id, email, password from users where name = ?",
                new Object[]{name},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setEmail(rs.getString("email"));
                    user.setName(name);
                    user.setPassword(rs.getString("password"));
                    return user;
                });
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return this.jdbcTemplate.query(
                "select user_id, name, email, password from users",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    return user;
                });
    }
}
