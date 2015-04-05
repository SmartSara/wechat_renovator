package com.renovator.controller;

import com.renovator.pojo.Service;
import com.renovator.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return serviceService.getServiceListByUserId(userId);
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
}
