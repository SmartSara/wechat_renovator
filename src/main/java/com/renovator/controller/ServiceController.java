package com.renovator.controller;

import com.renovator.pojo.Service;
import com.renovator.pojo.User;
import com.renovator.service.ServiceService;
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
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Service> getServiceList(HttpServletRequest request, HttpServletResponse response) {
        return serviceService.getServiceList();
    }

    @RequestMapping(value = "list/{user_id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Service> getServiceListByUserId(@PathVariable("user_id") int userId, HttpServletRequest request, HttpServletResponse response) {
        return serviceService.getServiceListByUserId(userId, 0);
    }

    @RequestMapping(value = "list/open_id/{open_id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Service> getServiceListByOpenId(@PathVariable("open_id") String openId, HttpServletRequest request, HttpServletResponse response) {
        return serviceService.getServiceListByOpenId(openId);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Service getServiceList(@PathVariable("id") int serviceId, HttpServletRequest request, HttpServletResponse response) {
        return serviceService.getService(serviceId);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void addService(@RequestBody Service service, HttpServletRequest request, HttpServletResponse response) {
        serviceService.addService(service);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void updateService(@RequestBody Service service, HttpServletRequest request, HttpServletResponse response) {
        serviceService.updateService(service);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteService(@RequestParam("id") int serviceId, HttpServletRequest request, HttpServletResponse response) {
        serviceService.deleteService(serviceId);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Service> getUserList(@RequestParam("order_id") String orderId, @RequestParam("type") String type,
                              @RequestParam("price") String price, @RequestParam("ts") String ts,
                              @RequestParam("username") String username, @RequestParam("mobile") String contact,
                              @RequestParam("product_name") String productName,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            return serviceService.searchServices(orderId, type, price, ts, username, contact, productName);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setHeader(PropertyHolder.HEADER_MSG, e.getMessage());
            return null;
        }
    }
}
