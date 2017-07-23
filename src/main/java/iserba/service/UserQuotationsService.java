package iserba.service;

import iserba.model.UserQuotations;
import iserba.to.UserQuotationsTo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public interface UserQuotationsService {

    // null if updated quotations do not belong to userId
    UserQuotations save(UserQuotationsTo userQuotationsTo);

    // false if quotations do not belong to userId
    boolean delete(int id, int userId);

    // null if quotations do not belong to userId
    UserQuotationsTo get(int id, int userId);

    Collection<UserQuotationsTo> getAll();

    // ORDERED dateTime
    Collection<UserQuotationsTo> getAll(int userId);

    // ORDERED dateTime
    Collection<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    int getUserId(int userQuotationsId);

    void sortByDate(List<UserQuotations> list);

    String getFormattedDate(LocalDateTime localDateTime);

    List<String> getSortedQuotations();
}
