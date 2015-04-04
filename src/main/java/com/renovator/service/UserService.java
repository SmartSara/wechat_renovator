package com.renovator.service;

import com.renovator.dao.UserDao;
import com.renovator.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
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
}
