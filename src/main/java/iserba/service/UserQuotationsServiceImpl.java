package iserba.service;

import iserba.dao.UserDAO;
import iserba.dao.UserQuotationsDAO;
import iserba.model.UserQuotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserQuotationsServiceImpl implements UserQuotationsService {
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
        List<String> result = new ArrayList<>();
        List<UserQuotations> userQuotationsList = (List<UserQuotations>) getAll();
        for (UserQuotations uq: userQuotationsList){
            String userName = userDAO.get(getUserId(uq.getId())).getName();
            String date = uq.getDateTime().toLocalDate().toString();
            String quotation = uq.getDescription();
            result.add(date + " " + userName + ": " + quotation);
        }
        List<String> reversedCopy = result.subList(0, result.size());
        Collections.reverse(reversedCopy);
        return result;
    }
}