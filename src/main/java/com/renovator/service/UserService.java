package com.renovator.service;

import com.renovator.dao.UserDao;
import com.renovator.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Transactional
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Transactional
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Transactional
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    @Transactional
    public User getUserWithOpenId(String openId) {
        return userDao.getUserWithOpenId(openId);
    }

    /**
     *
     * @param name
     * @param contact
     * @param address
     * @param birthday
     * @param balance
     * @return users
     * @throws ParseException
     */
    @Transactional
    public List<User> searchUsers(String name, String contact, String address, String birthday, String balance) throws ParseException {
        return userDao.searchUsers(name, contact, address, birthday, balance);
    }

    @Transactional
    public void bindAccount(String username, String contact, String email, String openId) {
        User user=new User();
        user.setName(username);
        user.setContact(contact);
        user.setEmail(email);
        user.setOpenId(openId);
        userDao.addUser(user);
    }
}
