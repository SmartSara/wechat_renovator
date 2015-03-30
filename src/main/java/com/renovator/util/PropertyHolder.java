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
    //    #һ����ѯ����
//    menu.membership_balance=��ѯ��Ա�����
//    menu.membership_expense=��ѯ��Ա�����Ѽ�¼
//    menu.membership_service_price_inquiry=��ѯ����۸�
    public static final String MENU_MEMBERSHIP_BALANCE = prop.getProperty("menu.membership_balance");
    public static final String MENU_MEMBERSHIP_EXPENSE = prop.getProperty("menu.membership_expense");
    public static final String MENU_SERVICE_PRICE_INQUIRY = prop.getProperty("menu.membership_service_price_inquiry");
    //    #�������ٹ���
//    menu.current_order_status=���񵥵�ǰ״̬
    public static final String MENU_CURRENT_ORDER_STATUS = prop.getProperty("menu.current_order_status");
    //    #����ԤԼ����
//    menu.appointment_receive=ԤԼ�����ջ�
//    menu.appointment_fetch=ԤԼ����ȡ��
    public static final String MENU_APPOINTMENT_RECEIVE = prop.getProperty("menu.appointment_receive");
    public static final String MENU_APPOINTMENT_FETCH = prop.getProperty("menu.appointment_fetch");
    //    #�ġ����ѹ���
//    menu.membership_birth_notification=��Ա����������
//    menu.membership_card_expiration=��Ա����������
//    menu.membership_card_balance_notification=��Ա���������
    public static final String MENU_MEMBERSHIP_BIRTH_NOTIFICATION = prop.getProperty("menu.membership_birth_notification");
    public static final String MENU_MEMBERSHIP_CARD_EXPIRATION = prop.getProperty("menu.membership_card_expiration");
    public static final String MENU_MEMBERSHIP_CARD_BALANCE_NOTIFICATION = prop.getProperty("menu.membership_card_balance_notification");
    //    #�塢��������
//    menu.product_showcase=������Ʒչʾ
//    menu.product_inquiry=������Ʒ��ѯ
    public static final String MENU_PRODUCT_SHOWCASE = prop.getProperty("menu.product_showcase");
    public static final String MENU_PRODUCT_INQUIRY = prop.getProperty("menu.product_inquiry");
    //    ������ƹ�
//    menu.current_shop_activity=��ǰ���ڽ��еĵ��̻
//    menu.cooperation=������Ʒ�Ƶĺ���
    public static final String MENU_CURRENT_SHOP_ACTIVITY = prop.getProperty("menu.current_shop_activity");
    public static final String MENU_COOPERATION = prop.getProperty("menu.cooperation");
    //    �ߡ���������
//    menu.about_us=��˾���
//    menu.more_info=���̵�ַ��Ӫҵʱ�䣬��ϵ��ʽ
    public static final String MENU_ABOUT_US = prop.getProperty("menu.about_us");
    public static final String MENU_MORE_INFO = prop.getProperty("menu.more_info");


}
