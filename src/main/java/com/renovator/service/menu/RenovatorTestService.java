package com.renovator.service.menu;

import com.renovator.exception.UserNotFoundException;
import com.renovator.pojo.User;
import com.renovator.pojo.message.req.TextMessage;
import com.renovator.pojo.message.resp.Article;
import com.renovator.pojo.message.resp.NewsMessage;
import com.renovator.service.ServiceService;
import com.renovator.service.UserService;
import com.renovator.util.MessageUtil;
import com.renovator.util.PropertyHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Renovator service
 */
@Service
public class RenovatorTestService {

    private static final Logger logger = LoggerFactory.getLogger(RenovatorTestService.class);

    @Autowired
    private EventService eventService;

    /**
     * @param request
     * @return xml
     */
    public String processRequest(HttpServletRequest request) {
        String fromUserName = null;
        String toUserName = null;
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            fromUserName = requestMap.get("FromUserName");
            toUserName = requestMap.get("ToUserName");

            String msgType = requestMap.get("MsgType");

            String content = requestMap.get("Content").trim();

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                if (PropertyHolder.MENU_MEMBERSHIP_BALANCE_KEY.equals(content)) {
                    return eventService.doMembershipBalance(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_MEMBERSHIP_EXPENSE_KEY.equals(content)) {
                    return eventService.doMembershipExpense(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_CURRENT_ORDER_STATUS_KEY.equals(content)) {
                    return eventService.doCurrentOrderStatus(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH_KEY.equals(content)) {
                    return eventService.doAppointmentReceiveFetch(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION_KEY.equals(content)) {
                    return eventService.doMembershipNotification(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_PRODUCT_SHOWCASE_KEY.equals(content)) {
                    return eventService.doProductShowCase(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_PRODUCT_INQUIRY_KEY.equals(content)) {
                    return eventService.doProductInquiry(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_ABOUT_US_KEY.equals(content)) {
                    return eventService.doAboutUs(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY_KEY.equals(content)) {
                    return eventService.doCurrentShopActivity(fromUserName, toUserName);
                }
            }
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            String respContent = String.format("请回复数字\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n" +
                            "回复%s：%s\n",
                    PropertyHolder.MENU_MEMBERSHIP_BALANCE_KEY, PropertyHolder.MENU_MEMBERSHIP_BALANCE,
                    PropertyHolder.MENU_MEMBERSHIP_EXPENSE_KEY, PropertyHolder.MENU_MEMBERSHIP_EXPENSE,
                    PropertyHolder.MENU_CURRENT_ORDER_STATUS_KEY, PropertyHolder.MENU_CURRENT_ORDER_STATUS,
                    PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH_KEY, PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH,
                    PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION_KEY, PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION,
                    PropertyHolder.MENU_PRODUCT_SHOWCASE_KEY, PropertyHolder.MENU_PRODUCT_SHOWCASE,
                    PropertyHolder.MENU_PRODUCT_INQUIRY_KEY, PropertyHolder.MENU_PRODUCT_INQUIRY,
                    PropertyHolder.MENU_ABOUT_US_KEY, PropertyHolder.MENU_ABOUT_US,
                    PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY_KEY, PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY
            );
            textMessage.setContent(respContent);
            return MessageUtil.messageToXml(textMessage);

        } catch (UserNotFoundException e) {
            return eventService.doMembershipBinding(fromUserName, toUserName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return eventService.doErrorHandler(fromUserName, toUserName);

        }
    }


}