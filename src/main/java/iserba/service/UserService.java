package iserba.service;

import iserba.model.User;
import iserba.to.UserTo;

import java.util.List;

public interface UserService {
    User save(UserTo userTo);

    User update(UserTo userTo);
    // false if not found
    boolean delete(int id);

    // null if not found
    UserTo getTo(int id);

    User get(int id);

    // null if not found
    UserTo getByEmail(String email);

    List<UserTo> getAll();

    int getUserToIdByUserName(String userName);
}
