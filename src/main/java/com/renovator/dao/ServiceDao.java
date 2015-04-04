package com.renovator.dao;

import com.renovator.pojo.Service;
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
public class ServiceDao {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Service getService(int id) {
        return (Service) sessionFactory.getCurrentSession().get(Service.class, id);
    }

    public List<Service> getServiceList() {
        return (List<Service>) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.Service");
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
}

