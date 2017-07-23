package iserba.service;

import iserba.dao.UserDAO;
import iserba.dao.UserQuotationsDAO;
import iserba.model.User;
import iserba.model.UserQuotations;
import iserba.to.UserConverter;
import iserba.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserQuotationsDAO userQuotationsDAO;

    @Autowired
    UserConverter converter;

    @Override
    public User save(UserTo userTo) {
        User user = converter.createFromDto(userTo);
        return userDAO.save(user);
    }

    @Override
    public User update(UserTo userTo) {
        User user = converter.createFromDto(userTo);
        return userDAO.update(user);
    }

    @Override
    public boolean delete(int id) {
        return userDAO.delete(id);
    }

    @Override
    public UserTo getTo(int id) {
        User user = userDAO.get(id);
        return converter.createFromEntity(user);
    }

    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public UserTo getByEmail(String email) {
        User user = userDAO.getByEmail(email);
        return converter.createFromEntity(user);
    }

    @Override
    public List<UserTo> getAll() {
        List<User> users = userDAO.getAll();
        return converter.createFromEntities(users);
    }

    @Override
    public int getUserToIdByUserName(String userName) {
        User user = userDAO.getByName(userName);
        return user.getId();
    }
}
