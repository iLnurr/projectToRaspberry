package iserba.service;

import iserba.dao.UserQuotationsDAO;
import iserba.model.UserQuotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Service
public class UserQuotationsServiceImpl implements UserQuotationsService {
    @Autowired
    UserQuotationsDAO userQuotationsDAO;

    @Override
    public UserQuotations save(UserQuotations userQuotations, int userId) {
        return userQuotationsDAO.save(userQuotations, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return userQuotationsDAO.delete(id, userId);
    }

    @Override
    public UserQuotations get(int id, int userId) {
        return userQuotationsDAO.get(id, userId);
    }

    @Override
    public Collection<UserQuotations> getAll() {
        return userQuotationsDAO.getAll();
    }

    @Override
    public Collection<UserQuotations> getAll(int userId) {
        return userQuotationsDAO.getAll(userId);
    }

    @Override
    public Collection<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return userQuotationsDAO.getBetween(startDate, endDate, userId);
    }

    @Override
    public int getUserId(int userQuotationsId) {
        return userQuotationsDAO.getUserId(userQuotationsId);
    }

    @Override
    public void sortByDate(List<UserQuotations> list) {
        list.sort((o1, o2) -> {
            if (o1.getDateTime().isAfter(o2.getDateTime())){
                return -1;
            } else if (o1.getDateTime().isBefore(o2.getDateTime())){
                return 1;
            }
            return 0;
        });
    }

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public String getFormattedDate(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }
}