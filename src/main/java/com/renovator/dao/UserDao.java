package com.renovator.dao;

import com.renovator.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        try {
            user = (User) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.User u where u.openId = " + openId).uniqueResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public List<User> searchUsers(String name, String contact, String address, String birthday, String balance) throws ParseException {
        logger.debug("Search users with name={}, contact={}, address={}, birthday={}, balance={}", name, contact, address, birthday, balance);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        if (!"".equalsIgnoreCase(name)) {
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (!"".equalsIgnoreCase(contact)) {
            criteria.add(Restrictions.like("contact", "%" + contact + "%"));
        }
        if (!"".equalsIgnoreCase(birthday)) {
            criteria.add(Restrictions.eq("birthday", new SimpleDateFormat("yyyy-MM-dd").parse(birthday)));
        }
        if (!"".equalsIgnoreCase(address)) {
            criteria.add(Restrictions.like("address", "%" + address + "%"));
        }
        if (!"".equalsIgnoreCase(balance)) {
            criteria.add(Restrictions.eq("balance", Double.parseDouble(balance)));
        }
        return criteria.list();
    }
}

