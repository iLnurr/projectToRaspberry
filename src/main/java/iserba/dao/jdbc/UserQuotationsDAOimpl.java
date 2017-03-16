package iserba.dao.jdbc;

import iserba.Profiles;
import iserba.dao.UserQuotationsDAO;
import iserba.model.UserQuotations;
import iserba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@Profile(Profiles.JDBC)
public class UserQuotationsDAOimpl implements UserQuotationsDAO {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd HH:mm:ss")
                    .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true)
                    .toFormatter();
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public UserQuotations save(UserQuotations userQuotations, int userId) {
        jdbcTemplate.update("INSERT INTO quotations(date_time, description, user_id) VALUES(?, ?, ?)",
                userQuotations.getDateTime(),
                userQuotations.getDescription(),
                userId);
        return userQuotations;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        this.jdbcTemplate.update(
                "delete from quotations where user_id = ?", userId);
        return true;
    }

    @Override
    @Transactional
    public UserQuotations get(int id, int userId) {
        return this.jdbcTemplate.queryForObject(
                "select date_time, description from quotations where id = ? and user_id = ?",
                new Object[]{id, userId},
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
    @Transactional
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
    @Transactional
    public List<UserQuotations> getAll(int userId) {
        return this.jdbcTemplate.query(
                "select id, date_time, description, user_id from quotations WHERE user_id = ?",
                new Object[]{userId},
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
    @Transactional
    public List<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }

    @Override
    @Transactional
    public int getUserId(int userQuotationsId){
        return this.jdbcTemplate.queryForObject(
                "select user_id from quotations where id = ?",
                new Object[]{userQuotationsId},
                (rs, rowNum) -> rs.getInt("user_id"));
    }
}
