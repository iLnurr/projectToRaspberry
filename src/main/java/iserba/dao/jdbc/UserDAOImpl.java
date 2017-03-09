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
@Transactional(readOnly = true)
@Profile(Profiles.JDBC)
public class UserDAOImpl implements UserDAO{
    private static int globalUserSequence = 10000;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setId(generateId());
        int n = jdbcTemplate.update("INSERT INTO users(id, name, email, password) VALUES(?, ?, ?, ?)",
                user.getId(), user.getName(), user.getEmail(), user.getPassword());
        return user;
    }

    private int generateId() {
        return globalUserSequence++;
    }

    @Override
    public User update(User user) {
        int n = jdbcTemplate.update("UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?",
                user.getName(), user.getEmail(), user.getPassword(), user.getId());
        return user;
    }

    @Override
    @Transactional
    public User get(int id) {
        User user = this.jdbcTemplate.queryForObject(
                "select * from users where id = ?",
                new Object[]{id},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(id);
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
        return user;

    }

    @Override
    @Transactional
    public boolean delete(int id) {
        this.jdbcTemplate.update(
                "delete from quotations where user_id = ?", id);
        this.jdbcTemplate.update(
                "delete from users where id = ?", id);
        return true;
    }

    @Override
    @Transactional
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
    @Transactional
    public User getByName(String name) {
        return this.jdbcTemplate.queryForObject(
                "select id, email, password from users where name = ?",
                new Object[]{name},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
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
