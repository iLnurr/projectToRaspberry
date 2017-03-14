package iserba.service;

import iserba.dao.UserDAO;
import iserba.dao.UserQuotationsDAO;
import iserba.model.UserQuotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserQuotationsServiceImpl implements UserQuotationsService {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    @Autowired
    UserQuotationsDAO userQuotationsDAO;

    @Autowired
    UserDAO userDAO;

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
        list.sort(Comparator.comparing(UserQuotations::getDateTime));
    }

    @Override
    public String getFormattedDate(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    @Override
    public List<String> getSortedQuotations() {
        List<UserQuotations> userQuotationsList = (List<UserQuotations>) getAll();
        List<String> result = userQuotationsList
                .stream()
                .map(uq ->
                        uq.getDateTime().toLocalDate().toString() + " " +
                        userDAO.get(getUserId(uq.getId())).getName() + ":" +
                        uq.getDescription())
                .collect(Collectors.toList());
        List<String> reversedCopy = result.subList(0, result.size());
        Collections.reverse(reversedCopy);
        return result;
    }
}