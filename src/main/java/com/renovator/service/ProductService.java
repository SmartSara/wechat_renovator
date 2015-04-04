package com.renovator.service;

import com.renovator.dao.ProductDao;
import com.renovator.dao.ProductDao;
import com.renovator.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by darlingtld on 2015/4/4.
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductDao productDao;

    @Transactional
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Transactional
    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    @Transactional
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Transactional
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Transactional
    public boolean deleteProduct(int productId) {
        return productDao.deleteProduct(productId);
    }
}
