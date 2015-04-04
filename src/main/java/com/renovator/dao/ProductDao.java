package com.renovator.dao;

import com.renovator.pojo.Product;
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
public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Product getProduct(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public List<Product> getProductList() {
        return (List<Product>) sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.Product").list();
    }

    public boolean addProduct(Product product) {
        try {
            Serializable productId = sessionFactory.getCurrentSession().save(product);
            logger.debug("Add product {} {}", productId, product.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            sessionFactory.getCurrentSession().update(product);
            logger.debug("Update product {}", product.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        try {
            Product product = (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
            sessionFactory.getCurrentSession().delete(product);
            logger.debug("Delete product {}", product);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}

