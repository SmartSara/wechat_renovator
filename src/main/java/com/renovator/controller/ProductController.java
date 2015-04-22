package com.renovator.controller;

import com.renovator.pojo.Product;
import com.renovator.pojo.ProductDetails;
import com.renovator.service.ProductService;
import com.renovator.util.PropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/4.
 */
@Scope("prototype")
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "list/{limit}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProductList(@PathVariable("limit") int limit, HttpServletRequest request, HttpServletResponse response) {
        return productService.getProductList(limit);
    }

    @RequestMapping(value = "list/{category}/{limit}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProductListByCatogory(@PathVariable("category") String category, @PathVariable("limit") int limit, HttpServletRequest request, HttpServletResponse response) {
        return productService.getProductListByCategory(category, limit);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProduct(@PathVariable("id") int productId, HttpServletRequest request, HttpServletResponse response) {
        return productService.getProduct(productId);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void addProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        productService.addProduct(product);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void updateProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        productService.updateProduct(product);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteProduct(@RequestParam("id") int productId, HttpServletRequest request, HttpServletResponse response) {
        productService.deleteProduct(productId);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> searchProduct(@RequestParam("name") String name, @RequestParam("desc") String description,
                                @RequestParam("price") String price, @RequestParam("discount") String discount, @RequestParam("ts") String ts,
                                HttpServletRequest request, HttpServletResponse response) {
        try {
            return productService.searchProduct(name, description, price, discount, ts);
        } catch (ParseException e) {
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setHeader(PropertyHolder.HEADER_MSG, e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "add/details", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void addProductDetails(@RequestBody ProductDetails productDetails, HttpServletRequest request, HttpServletResponse response) {
        productService.addProductDetails(productDetails);
    }

    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ProductDetails getProductDetails(@PathVariable("id") int productDetailsId, HttpServletRequest request, HttpServletResponse response) {
        return productService.getProductDetails(productDetailsId);
    }
}
