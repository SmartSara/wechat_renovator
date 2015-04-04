package com.renovator.dao;

import com.renovator.pojo.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public User getUser(int id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    public List<User> getUserList() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.User").list();
    }

    public boolean addUser(User user) {
        try {
            Serializable userId = sessionFactory.getCurrentSession().save(user);
            logger.debug("Add user {} {}", userId, user.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            logger.debug("Update user {}", user.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
            sessionFactory.getCurrentSession().delete(user);
            logger.debug("Delete user {}", user);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public User getUserWithOpenId(String openId) {
        User user = null;
        try{
            user = (User) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.User where openId = "+openId).uniqueResult();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return user;
    }
}

