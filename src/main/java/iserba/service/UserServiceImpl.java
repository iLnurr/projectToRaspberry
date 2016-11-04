package iserba.service;

import iserba.dao.UserDAO;
import iserba.model.User;
import iserba.model.UserQuotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAO;

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean delete(int id) {
        return userDAO.delete(id);
    }

    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public int getUserIdByUserName(String userName) {
        List<User> userList = userDAO.getAll();
        int userId=0;
        for(User user : userList){
            if (user.getName().equals(userName)){
                userId = user.getId();
            }
        }
        return userId;
    }
}
