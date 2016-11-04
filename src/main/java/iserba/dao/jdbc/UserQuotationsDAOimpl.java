package iserba.dao.jdbc;

import iserba.dao.UserQuotationsDAO;
import iserba.model.UserQuotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserQuotationsDAOimpl implements UserQuotationsDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static UserQuotations userQuotationsMock = new UserQuotations(0, "quota-text",LocalDateTime.now());

    @Override
    public UserQuotations save(UserQuotations userQuotations, int userId) {
        return userQuotationsMock;
    }

    @Override
    public boolean delete(int id, int userId) {
        return true;
    }

    @Override
    public UserQuotations get(int id, int userId) {
        return userQuotationsMock;
    }

    @Override
    public List<UserQuotations> getAll() {
        List<UserQuotations> userQuotationsList = new ArrayList<>();
        userQuotationsList.add(userQuotationsMock);
        return userQuotationsList;
    }

    @Override
    public List<UserQuotations> getAll(int userId) {
        List<UserQuotations> userQuotationsList = new ArrayList<>();
        userQuotationsList.add(userQuotationsMock);
        return userQuotationsList;
    }

    @Override
    public List<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<UserQuotations> userQuotationsList = new ArrayList<>();
        userQuotationsList.add(userQuotationsMock);
        return userQuotationsList;
    }

    @Override
    public int getUserId(int userQuotationsId){
        return 0;
    }
}
