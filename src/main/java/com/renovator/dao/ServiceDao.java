package com.renovator.dao;

import com.renovator.pojo.Service;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
public class ServiceDao {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Service getService(int id) {
        return (Service) sessionFactory.getCurrentSession().get(Service.class, id);
    }

    public List<Service> getServiceList() {
        return (List<Service>) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.Service").list();
    }

    public boolean addService(Service service) {
        try {
            Serializable serviceId = sessionFactory.getCurrentSession().save(service);
            logger.debug("Add service {} {}", serviceId, service.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean updateService(Service service) {
        try {
            sessionFactory.getCurrentSession().update(service);
            logger.debug("Update service {}", service.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteService(int serviceId) {
        try {
            Service service = (Service) sessionFactory.getCurrentSession().get(Service.class, serviceId);
            sessionFactory.getCurrentSession().delete(service);
            logger.debug("Delete service {}", service);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<Service> getServiceListByUserId(int userId, int limit) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(String.format("from com.renovator.pojo.Service where user.id=%d order by ts desc", userId));
            List<Service> serviceList = limit == 0 ? query.list() : query.setMaxResults(limit).list();
            logger.debug("Get service list of userId {}", userId);
            logger.debug(serviceList.toString());
            return serviceList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Service> searchService(String orderId, String type, String price, String ts, String username, String contact, String productName) throws ParseException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Service.class);
        if (!"".equalsIgnoreCase(orderId)) {
            criteria.add(Restrictions.like("orderId", Integer.parseInt(orderId)));
        }
        if (!"".equalsIgnoreCase(type)) {
            criteria.add(Restrictions.like("type", "%" + type + "%"));
        }
        if (!"".equalsIgnoreCase(price)) {
            criteria.add(Restrictions.eq("price", Double.parseDouble(price)));
        }
        if (!"".equalsIgnoreCase(ts)) {
            criteria.add(Restrictions.eq("ts", new SimpleDateFormat("yyyy-MM-dd").parse(ts)));
        }
        if (!"".equalsIgnoreCase(username)) {
            criteria.createCriteria("user").add(Restrictions.like("name", "%" + username + "%"));
        }
        if (!"".equalsIgnoreCase(contact)) {
            criteria.createCriteria("user").add(Restrictions.like("contact", "%" + contact + "%"));
        }
        if (!"".equalsIgnoreCase(productName)) {
            criteria.createCriteria("product").add(Restrictions.like("name", "%" + productName + "%"));
        }
        return criteria.list();
    }

    public List<Service> getUncheckedServiceListByUserId(int userId) {
        return sessionFactory.getCurrentSession().createQuery(String.format("from com.renovator.pojo.Service where user.id = %d and status != '%s'", userId, Service.Status.CHECKED)).list();
    }

    public List<Service> getServiceListByOpenId(String openId) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(String.format("from com.renovator.pojo.Service where user.openId='%s' order by ts desc", openId));
            List<Service> serviceList = query.list();
            logger.debug("Get service list of openId {}", openId);
            logger.debug(serviceList.toString());
            return serviceList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}

