package com.renovator.dao;

import com.renovator.pojo.Product;
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
public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Product getProduct(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public List<Product> getProductList(int limit) {
        Query query = sessionFactory.getCurrentSession().createQuery("from com.renovator.pojo.Product order by ts");
        return limit==0?query.list():query.setMaxResults(limit).list();
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

    public List<Product> searchProduct(String name, String description, String price, String discount, String ts) throws ParseException {
        logger.debug("Search product with name={}, description={},price={},discount={},ts={}", name, description, price, discount, ts);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        if (!"".equalsIgnoreCase(name)) {
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        if (!"".equalsIgnoreCase(description)) {
            criteria.add(Restrictions.like("description", "%" + description + "%"));
        }
        if (!"".equalsIgnoreCase(ts)) {
            criteria.add(Restrictions.eq("ts", new SimpleDateFormat("yyyy-MM-dd").parse(ts)));
        }
        if (!"".equalsIgnoreCase(price)) {
            criteria.add(Restrictions.eq("price", Double.parseDouble(price)));
        }
        if (!"".equalsIgnoreCase(discount)) {
            criteria.add(Restrictions.like("discount", Float.parseFloat(discount)));
        }
        return criteria.list();
    }


    public List<Product> getProductListByCategory(String category, int limit) {
        switch (category.toLowerCase().trim()) {
            case "shoe":
                category = Product.Category.CATEGORY_SHOE;
                break;
            case "bag":
                category = Product.Category.CATEGORY_BAG;
                break;
            case "leather":
                category = Product.Category.CATEGORY_LEATHER;
                break;
            case "coat":
                category = Product.Category.CATEGORY_COAT;
                break;
            default:
                category = Product.Category.CATEGORY_SHOE;
        }
        Query query = sessionFactory.getCurrentSession().createQuery(String.format("from Product where category='%s' order by ts desc", category));
        return limit == 0 ? query.list() : query.setMaxResults(limit).list();
    }

    public boolean addProductDetails(Product product) {
        try {
            Serializable productId = sessionFactory.getCurrentSession().save(product);
            logger.debug("Add product {} {}", productId, product.toString());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public Product getProductDetails(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }
}

