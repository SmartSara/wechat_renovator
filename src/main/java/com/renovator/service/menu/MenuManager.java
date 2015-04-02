package com.renovator.service.menu;

/**
 * Created by darlingtld on 2015/2/20.
 */


import com.renovator.pojo.AccessToken;
import com.renovator.pojo.button.Button;
import com.renovator.pojo.button.ClickButton;
import com.renovator.pojo.button.ComplexButton;
import com.renovator.pojo.button.Menu;
import com.renovator.util.PropertyHolder;
import com.renovator.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        String appId = PropertyHolder.APPID;
        String appSecret = PropertyHolder.APPSECRET;

        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            if (0 == result) {
                log.info("菜单创建成功");
            } else {
                log.info("菜单创建失败，错误码：{}", result);
            }
        }
    }

    private static Menu getMenu() {
        ClickButton btn11 = new ClickButton();
        btn11.setName(PropertyHolder.MENU_MEMBERSHIP_BALANCE);
        btn11.setKey(PropertyHolder.MENU_MEMBERSHIP_BALANCE_KEY);

        ClickButton btn12 = new ClickButton();
        btn12.setName(PropertyHolder.MENU_MEMBERSHIP_EXPENSE);
        btn12.setKey(PropertyHolder.MENU_MEMBERSHIP_EXPENSE_KEY);

        ClickButton btn13 = new ClickButton();
        btn13.setName(PropertyHolder.MENU_CURRENT_ORDER_STATUS);
        btn13.setKey(PropertyHolder.MENU_CURRENT_ORDER_STATUS_KEY);

        ClickButton btn14 = new ClickButton();
        btn14.setName(PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH);
        btn14.setKey(PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH_KEY);

        ClickButton btn15 = new ClickButton();
        btn15.setName(PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION);
        btn15.setKey(PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION_KEY);


        ClickButton btn21 = new ClickButton();
        btn21.setName(PropertyHolder.MENU_PRODUCT_SHOWCASE);
        btn21.setKey(PropertyHolder.MENU_PRODUCT_SHOWCASE_KEY);

        ClickButton btn22 = new ClickButton();
        btn22.setName(PropertyHolder.MENU_PRODUCT_INQUIRY);
        btn22.setKey(PropertyHolder.MENU_PRODUCT_INQUIRY_KEY);

        ClickButton btn31 = new ClickButton();
        btn31.setName(PropertyHolder.MENU_ABOUT_US);
        btn31.setKey(PropertyHolder.MENU_ABOUT_US_KEY);

        ClickButton btn32 = new ClickButton();
        btn32.setName(PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY);
        btn32.setKey(PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY_KEY);

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName(PropertyHolder.CMENU_MEMBERSHIP);
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName(PropertyHolder.CMENU_SERVICE);
        mainBtn2.setSub_button(new Button[]{btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName(PropertyHolder.CMENU_COMPANY);
        mainBtn3.setSub_button(new Button[]{btn31, btn32});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
