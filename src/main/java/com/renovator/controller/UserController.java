package com.renovator.controller;

import com.renovator.pojo.User;
import com.renovator.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getUserList(HttpServletRequest request, HttpServletResponse response) {
        return userService.getUserList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserList(@PathVariable("id") int userId, HttpServletRequest request, HttpServletResponse response) {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void addUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        userService.addUser(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public void updateUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteUser(@RequestParam("id") int userId, HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(userId);
    }
}
