package iserba.dao.jdbc;

import iserba.dao.UserQuotationsDAO;
import iserba.model.User;
import iserba.model.UserQuotations;
import iserba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserQuotationsDAOimpl implements UserQuotationsDAO {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserQuotations save(UserQuotations userQuotations, int userId) {
        System.out.println("_____________________________________ userQuotationsID= "+userQuotations.getId());
        jdbcTemplate.update("INSERT INTO quotations(id, date_time, description, user_id) VALUES(?, ?, ?, ?)",
                userQuotations.getId(),
                userQuotations.getDateTime(),
                userQuotations.getDescription(),
                userId);
        return userQuotations;
    }

    @Override
    public boolean delete(int id, int userId) {
        return true;
    }

    @Override
    public UserQuotations get(int id, int userId) {
        return this.jdbcTemplate.queryForObject(
                "select date_time, description from quotations where id = ? and user_id = ?",
                new Object[]{userId},
                (rs, rowNum) -> {
                    UserQuotations userQuotations = new UserQuotations();
                    userQuotations.setId(id);
                    userQuotations.setDateTime(LocalDateTime.parse(rs.getString("date_time"), DATE_TIME_FORMATTER));
                    userQuotations.setDescription(rs.getString("description"));
                    userQuotations.setUser(userService.get(userId));
                    return userQuotations;
                });
    }

    @Override
    public List<UserQuotations> getAll() {
        return this.jdbcTemplate.query(
                "select id, date_time, description, user_id from quotations",
                (rs, rowNum) -> {
                    UserQuotations userQuotations = new UserQuotations();
                    userQuotations.setId(rs.getInt("id"));
                    userQuotations.setDateTime(LocalDateTime.parse(rs.getString("date_time"), DATE_TIME_FORMATTER));
                    userQuotations.setDescription(rs.getString("description"));
                    userQuotations.setUser(userService.get(rs.getInt("user_id")));
                    return userQuotations;
                });
    }

    @Override
    public List<UserQuotations> getAll(int userId) {
        return this.jdbcTemplate.query(
                "select id, date_time, description, user_id from quotations WHERE user_id = ?",
                (rs, rowNum) -> {
                    UserQuotations userQuotations = new UserQuotations();
                    userQuotations.setId(rs.getInt("id"));
                    userQuotations.setDateTime(LocalDateTime.parse(rs.getString("date_time"), DATE_TIME_FORMATTER));
                    userQuotations.setDescription(rs.getString("description"));
                    userQuotations.setUser(userService.get(userId));
                    return userQuotations;
                });
    }

    @Override
    public List<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }

    @Override
    public int getUserId(int userQuotationsId){
        return this.jdbcTemplate.queryForObject(
                "select user_id from quotations where id = ?",
                new Object[]{userQuotationsId},
                (rs, rowNum) -> rs.getInt("user_id"));
    }
}
