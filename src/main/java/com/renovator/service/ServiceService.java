package com.renovator.service;

import com.renovator.dao.ServiceDao;
import com.renovator.pojo.Service;
import com.renovator.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by darlingtld on 2015/4/4.
 */
@org.springframework.stereotype.Service
public class ServiceService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceService.class);

    @Autowired
    private ServiceDao serviceDao;

    @Transactional
    public com.renovator.pojo.Service getService(int id) {
        return serviceDao.getService(id);
    }

    @Transactional
    public List<com.renovator.pojo.Service> getServiceList() {
        return serviceDao.getServiceList();
    }

    @Transactional
    public boolean addService(com.renovator.pojo.Service service) {
        return serviceDao.addService(service);
    }

    @Transactional
    public boolean updateService(com.renovator.pojo.Service service) {
        return serviceDao.updateService(service);
    }

    @Transactional
    public boolean deleteService(int serviceId) {
        return serviceDao.deleteService(serviceId);
    }

    @Transactional
    public List<Service> getServiceListByUserId(int userId) {
        return serviceDao.getServiceListByUserId(userId);
    }

    @Transactional
    public List<Service> searchServices(String orderId, String type, String price, String ts, String username, String contact, String productName) {
        return serviceDao.searchService(orderId, type, price, ts, username, contact, productName);
    }
}
