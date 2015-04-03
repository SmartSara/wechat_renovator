package com.renovator.dao;

import com.renovator.pojo.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Repository
public class UserDao {
    private static final Logger loggger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public User getUser(int id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    public List<User> getUserList(){
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.User");
    }
}
