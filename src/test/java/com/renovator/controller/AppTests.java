package com.renovator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renovator.pojo.Product;
import com.renovator.pojo.Service;
import com.renovator.pojo.User;
import com.renovator.service.ProductService;
import com.renovator.service.ServiceService;
import com.renovator.service.UserService;
import com.renovator.util.PropertyHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml", "file:src/main/webapp/WEB-INF/spring-security.xml"})
public class AppTests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private ProductService productService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setName("灵达");
        user.setAddress("lingda 家");
        user.setBalance(99.8);
        user.setBirthday(new Date());
        user.setContact("1340218638");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        System.out.println(userJson);
        mockMvc.perform(post("/user/add").content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getProperty() throws Exception {
        System.out.println(PropertyHolder.MENU_ABOUT_US);
    }

    @Test
    public void addService() throws Exception {
        Service service = new Service();
        service.setOrderId(12345567);
        service.setType("sale");
        service.setPrice(87.5);
        service.setTs(new Date());
//        service.setUserId(2);
//        service.setProductId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String serviceJson = objectMapper.writeValueAsString(service);
        System.out.println(serviceJson);
        mockMvc.perform(post("/service/add").content(serviceJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void addProduct() throws Exception {
        Product product = new Product();
        product.setPrice(37.5);
        product.setTs(new Date());
        product.setName("灵达的神器");
        product.setDescription("猪猪的最爱");
        product.setDiscount(0.85f);
        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(product);
        System.out.println(productJson);
        mockMvc.perform(post("/service/add").content(productJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getService() throws Exception {
        mockMvc.perform(get("/service/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void searchUser() throws Exception {
//        mockMvc.perform(get("/user/search?name=lingda&contact=&address=&birthday=&balance=")).andExpect(status().isOk()).andDo(print());
        String name = "";
        String contact = "";
        String address = "家";
        String birthday = "2015-04-04";
        String balance = "";
        List<User> userList = userService.searchUsers(name, contact, address, birthday, balance);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void searchService() throws Exception {
//        mockMvc.perform(get("/service/search?order_id=&type=sale&price=&ts=&username=&contact=&product_name=")).andExpect(status().isOk()).andDo(print());
        String order_id = "";
        String type = "sale";
        String contact = "";
        String price = "";
        String ts = "";
        String username="";
        String product_name="";
        List<Service> serviceList = serviceService.searchServices(order_id, type, price, ts, username, contact, product_name);
        for (Service service : serviceList) {
            System.out.println(service);
        }
    }

    @Test
    public void searchProduct() throws Exception {
//        mockMvc.perform(get("/service/search?order_id=&type=sale&price=&ts=&username=&contact=&product_name=")).andExpect(status().isOk()).andDo(print());
        String name = "神器";
        String description = "";
        String price = "";
        String discount = "";
        String ts = "";
        List<Product> productList = productService.searchProduct(name, description, price, discount, ts);
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
