package iserba.service;

import iserba.dao.UserDAO;
import iserba.dao.UserQuotationsDAO;
import iserba.model.User;
import iserba.model.UserQuotations;
import iserba.to.UserQuotationsConverter;
import iserba.to.UserQuotationsTo;
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

    @Autowired
    UserQuotationsConverter converter;

    @Override
    public UserQuotations save(UserQuotationsTo userQuotationsTo) {
        UserQuotations userQuotations = converter.createFromDto(userQuotationsTo);
        String userName = userQuotationsTo.getUserName();
        User user = userDAO.getByName(userName);
        Integer userId = user.getId();
        return userQuotationsDAO.save(userQuotations, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return userQuotationsDAO.delete(id, userId);
    }

    @Override
    public UserQuotationsTo get(int id, int userId) {
        UserQuotations uq = userQuotationsDAO.get(id, userId);
        return converter.createFromEntity(uq);
    }

    @Override
    public Collection<UserQuotationsTo> getAll() {
        Collection<UserQuotations> l = userQuotationsDAO.getAll();
        return converter.createFromEntities(l);
    }

    @Override
    public Collection<UserQuotationsTo> getAll(int userId) {
        Collection<UserQuotations> l = userQuotationsDAO.getAll(userId);
        return converter.createFromEntities(l);
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
        List<UserQuotationsTo> userQuotationsList = (List<UserQuotationsTo>) getAll();
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