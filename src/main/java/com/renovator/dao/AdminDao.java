package com.renovator.dao;

import com.renovator.pojo.Administrator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by darlingtld on 2015/4/5.
 */
@Repository
public class AdminDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public Administrator findByUserName(String username) {

        List<Administrator> administrators = sessionFactory.getCurrentSession()
                .createQuery("from Administrator where username=?")
                .setParameter(0, username)
                .list();

        if (administrators.size() > 0) {
            return administrators.get(0);
        } else {
            return null;
        }

    }

}
