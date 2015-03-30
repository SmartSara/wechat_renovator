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
    public static final String TOKEN = prop.getProperty("wechat.token");
    public static final String APPID = prop.getProperty("wechat.app_id");
    public static final String APPSECRET = prop.getProperty("wechat.app_secret");
    public static final String SERVER = prop.getProperty("wechat.server");
    //    #一、查询功能
//    menu.membership_balance=查询会员卡余额
//    menu.membership_expense=查询会员卡消费记录
//    menu.membership_service_price_inquiry=查询服务价格
    public static final String MENU_MEMBERSHIP_BALANCE = prop.getProperty("menu.membership_balance");
    public static final String MENU_MEMBERSHIP_EXPENSE = prop.getProperty("menu.membership_expense");
    public static final String MENU_SERVICE_PRICE_INQUIRY = prop.getProperty("menu.membership_service_price_inquiry");
    //    #二、跟踪功能
//    menu.current_order_status=服务单当前状态
    public static final String MENU_CURRENT_ORDER_STATUS = prop.getProperty("menu.current_order_status");
    //    #三、预约功能
//    menu.appointment_receive=预约上门收货
//    menu.appointment_fetch=预约上门取货
    public static final String MENU_APPOINTMENT_RECEIVE = prop.getProperty("menu.appointment_receive");
    public static final String MENU_APPOINTMENT_FETCH = prop.getProperty("menu.appointment_fetch");
    //    #四、提醒功能
//    menu.membership_birth_notification=会员生日月提醒
//    menu.membership_card_expiration=会员卡到期提醒
//    menu.membership_card_balance_notification=会员卡余额提醒
    public static final String MENU_MEMBERSHIP_BIRTH_NOTIFICATION = prop.getProperty("menu.membership_birth_notification");
    public static final String MENU_MEMBERSHIP_CARD_EXPIRATION = prop.getProperty("menu.membership_card_expiration");
    public static final String MENU_MEMBERSHIP_CARD_BALANCE_NOTIFICATION = prop.getProperty("menu.membership_card_balance_notification");
    //    #五、寄卖服务
//    menu.product_showcase=寄卖商品展示
//    menu.product_inquiry=寄卖商品查询
    public static final String MENU_PRODUCT_SHOWCASE = prop.getProperty("menu.product_showcase");
    public static final String MENU_PRODUCT_INQUIRY = prop.getProperty("menu.product_inquiry");
    //    六、活动推广
//    menu.current_shop_activity=当前正在进行的店铺活动
//    menu.cooperation=与其他品牌的合作
    public static final String MENU_CURRENT_SHOP_ACTIVITY = prop.getProperty("menu.current_shop_activity");
    public static final String MENU_COOPERATION = prop.getProperty("menu.cooperation");
    //    七、关于我们
//    menu.about_us=公司简介
//    menu.more_info=店铺地址，营业时间，联系方式
    public static final String MENU_ABOUT_US = prop.getProperty("menu.about_us");
    public static final String MENU_MORE_INFO = prop.getProperty("menu.more_info");


}
