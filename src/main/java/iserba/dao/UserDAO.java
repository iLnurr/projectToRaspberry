package iserba.dao;

import iserba.model.User;

import java.util.List;

public interface UserDAO {
    User save(User user);

    User update(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    // null if not found
    User getByName(String name);

    List<User> getAll();
}
