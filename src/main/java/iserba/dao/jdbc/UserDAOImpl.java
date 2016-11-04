package iserba.dao.jdbc;

import iserba.dao.UserDAO;
import iserba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    private JdbcTemplate jdbcTemplate;

    private static User userS = new User(0, "name", "password", "email");//mock

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        jdbcTemplate.update("INSERT INTO users(id, name, email, password) VALUES(?, ?, ?, ?)",
                user.getId(), user.getName(), user.getEmail(), user.getPassword());
        return user;
    }

    @Override
    public User get(int id) {
        return userS;
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public User getByEmail(String email) {
        return userS;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        users.add(userS);
        return users;
    }
}
