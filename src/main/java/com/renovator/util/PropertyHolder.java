package com.renovator.util;


import java.io.IOException;
import java.util.Properties;

/**
 * Created by tangld on 2015/3/30.
 */
public class PropertyHolder {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(PropertyHolder.class.getClassLoader().getResourceAsStream("renovator.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final String HEADER_MSG = "msg";

    public static final String TOKEN = prop.getProperty("wechat.token");
    public static final String APPID = prop.getProperty("wechat.app_id");
    public static final String APPSECRET = prop.getProperty("wechat.app_secret");
    public static final String SERVER = prop.getProperty("wechat.server");

    public static final String CMENU_MEMBERSHIP = prop.getProperty("cmenu.membership");
    public static final String CMENU_SERVICE = prop.getProperty("cmenu.service");
    public static final String CMENU_COMPANY = prop.getProperty("cmenu.company");

    public static final String MENU_MEMBERSHIP_BALANCE = prop.getProperty("menu.membership_balance");
    public static final String MENU_MEMBERSHIP_EXPENSE = prop.getProperty("menu.membership_expense");
    public static final String MENU_CURRENT_ORDER_STATUS = prop.getProperty("menu.current_order_status");
    public static final String MENU_APPOINTMENT_RECEIVE_FETCH = prop.getProperty("menu.appointment_receive_fetch");
    public static final String MENU_MEMBERSHIP_NOTIFICATION = prop.getProperty("menu.membership_notification");
    public static final String MENU_PRODUCT_SHOWCASE = prop.getProperty("menu.product_showcase");
    public static final String MENU_PRODUCT_INQUIRY = prop.getProperty("menu.product_inquiry");
    public static final String MENU_CURRENT_SHOP_ACTIVITY = prop.getProperty("menu.current_shop_activity");
    public static final String MENU_ABOUT_US = prop.getProperty("menu.about_us");

    public static final String MENU_MEMBERSHIP_BALANCE_KEY = prop.getProperty("menu.membership_balance_key");
    public static final String MENU_MEMBERSHIP_EXPENSE_KEY = prop.getProperty("menu.membership_expense_key");
    public static final String MENU_CURRENT_ORDER_STATUS_KEY = prop.getProperty("menu.current_order_status_key");
    public static final String MENU_APPOINTMENT_RECEIVE_FETCH_KEY = prop.getProperty("menu.appointment_receive_fetch_key");
    public static final String MENU_MEMBERSHIP_NOTIFICATION_KEY = prop.getProperty("menu.membership_notification_key");
    public static final String MENU_PRODUCT_SHOWCASE_KEY = prop.getProperty("menu.product_showcase_key");
    public static final String MENU_PRODUCT_INQUIRY_KEY = prop.getProperty("menu.product_inquiry_key");
    public static final String MENU_CURRENT_SHOP_ACTIVITY_KEY = prop.getProperty("menu.current_shop_activity_key");
    public static final String MENU_ABOUT_US_KEY = prop.getProperty("menu.about_us_key");

    public static final String MATERIAL_PICTURE_DIR = prop.getProperty("material.picture.dir");
    
    public static final Boolean IS_DEBUG_MODE = Boolean.valueOf(prop.getProperty("debug"));


}
