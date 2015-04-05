package com.renovator.service;

import org.springframework.stereotype.Service;

/**
 * Created by darlingtld on 2015/4/5.
 */
@Service
public class NotificationService {

    public void notifyBalance() {
        System.out.println("notify balance");
    }

    public void notifyBirthday() {
        System.out.println("notify birthday");
    }
}
