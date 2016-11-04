package iserba.service;

import iserba.model.UserQuotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public interface UserQuotationsService {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    // null if updated quotations do not belong to userId
    UserQuotations save(UserQuotations userQuotations, int userId);

    // false if quotations do not belong to userId
    boolean delete(int id, int userId);

    // null if quotations do not belong to userId
    UserQuotations get(int id, int userId);

    Collection<UserQuotations> getAll();

    // ORDERED dateTime
    Collection<UserQuotations> getAll(int userId);

    // ORDERED dateTime
    Collection<UserQuotations> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    int getUserId(int userQuotationsId);

    void sortByDate(List<UserQuotations> list);

    String getFormattedDate(LocalDateTime localDateTime);
}
