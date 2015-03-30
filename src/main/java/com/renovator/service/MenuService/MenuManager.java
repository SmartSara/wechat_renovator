package com.renovator.service.MenuService;

/**
 * Created by darlingtld on 2015/2/20.
 */


import com.renovator.pojo.*;
import com.renovator.util.PropertyHolder;
import com.renovator.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 菜单管理器类
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = PropertyHolder.APPID;
        // 第三方用户唯一凭证密钥
        String appSecret = PropertyHolder.APPSECRET;

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (0 == result)
                log.info("菜单创建成功！");
            else
                log.info("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        ClickButton btn11 = new ClickButton();
        btn11.setName(PropertyHolder.MENU_MEMBERSHIP_BALANCE);
        btn11.setKey(PropertyHolder.MENU_MEMBERSHIP_BALANCE);

        ClickButton btn12 = new ClickButton();
        btn12.setName(PropertyHolder.MENU_MEMBERSHIP_EXPENSE);
        btn12.setKey(PropertyHolder.MENU_MEMBERSHIP_EXPENSE);

        ClickButton btn13 = new ClickButton();
        btn13.setName(PropertyHolder.MENU_MEMBERSHIP_BIRTH_NOTIFICATION);
        btn13.setKey(PropertyHolder.MENU_MEMBERSHIP_BIRTH_NOTIFICATION);

        ClickButton btn14 = new ClickButton();
        btn14.setName(PropertyHolder.MENU_MEMBERSHIP_CARD_EXPIRATION);
        btn14.setKey(PropertyHolder.MENU_MEMBERSHIP_CARD_EXPIRATION);

        ClickButton btn15 = new ClickButton();
        btn15.setName(PropertyHolder.MENU_MEMBERSHIP_CARD_BALANCE_NOTIFICATION);
        btn15.setKey(PropertyHolder.MENU_MEMBERSHIP_CARD_BALANCE_NOTIFICATION);

        ClickButton btn21 = new ClickButton();
        btn21.setName(PropertyHolder.MENU_CURRENT_ORDER_STATUS);
        btn21.setKey(PropertyHolder.MENU_CURRENT_ORDER_STATUS);

        ClickButton btn22 = new ClickButton();
        btn22.setName(PropertyHolder.MENU_APPOINTMENT_RECEIVE);
        btn22.setKey(PropertyHolder.MENU_APPOINTMENT_RECEIVE);

        ClickButton btn23 = new ClickButton();
        btn23.setName(PropertyHolder.MENU_APPOINTMENT_FETCH);
        btn23.setKey(PropertyHolder.MENU_APPOINTMENT_FETCH);

        ClickButton btn24 = new ClickButton();
        btn24.setName(PropertyHolder.MENU_PRODUCT_SHOWCASE);
        btn24.setKey(PropertyHolder.MENU_PRODUCT_SHOWCASE);

        ClickButton btn25 = new ClickButton();
        btn25.setName(PropertyHolder.MENU_PRODUCT_INQUIRY);
        btn25.setKey(PropertyHolder.MENU_PRODUCT_INQUIRY);

        ClickButton btn31 = new ClickButton();
        btn31.setName(PropertyHolder.MENU_SERVICE_PRICE_INQUIRY);
        btn31.setKey(PropertyHolder.MENU_SERVICE_PRICE_INQUIRY);

        ClickButton btn32 = new ClickButton();
        btn32.setName(PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY);
        btn32.setKey(PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY);

        ClickButton btn33 = new ClickButton();
        btn33.setName(PropertyHolder.MENU_COOPERATION);
        btn33.setKey(PropertyHolder.MENU_COOPERATION);

        ClickButton btn34 = new ClickButton();
        btn34.setName(PropertyHolder.MENU_ABOUT_US);
        btn34.setKey(PropertyHolder.MENU_ABOUT_US);

        ClickButton btn35 = new ClickButton();
        btn35.setName(PropertyHolder.MENU_MORE_INFO);
        btn35.setKey(PropertyHolder.MENU_MORE_INFO);

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName(Button.TYPE_CLICK);
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName(Button.TYPE_CLICK);
        mainBtn2.setSub_button(new Button[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName(Button.TYPE_CLICK);
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33, btn34, btn35});

        /**
         * 每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
