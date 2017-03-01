package iserba.dao;

import iserba.model.UserQuotations;

import java.time.LocalDateTime;
import java.util.Collection;

public interface UserQuotationsDAO {
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
}
