package com.renovator.service.menu;

import com.renovator.exception.UserNotFoundException;
import com.renovator.pojo.User;
import com.renovator.pojo.message.req.TextMessage;
import com.renovator.pojo.message.resp.Article;
import com.renovator.pojo.message.resp.NewsMessage;
import com.renovator.service.ServiceService;
import com.renovator.service.UserService;
import com.renovator.service.tuling.TulingApiService;
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
public class RenovatorService {

    private static final Logger logger = LoggerFactory.getLogger(RenovatorTestService.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private TulingApiService tulingApiService;

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

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event");
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    String respContent = "您好，欢迎关注雷诺维特奢侈品护理！";
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    textMessage.setContent(respContent);
                    return MessageUtil.messageToXml(textMessage);
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    if (PropertyHolder.MENU_MEMBERSHIP_BALANCE_KEY.equals(eventKey)) {
                        return eventService.doMembershipBalance(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_MEMBERSHIP_EXPENSE_KEY.equals(eventKey)) {
                        return eventService.doMembershipExpense(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_CURRENT_ORDER_STATUS_KEY.equals(eventKey)) {
                        return eventService.doCurrentOrderStatus(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH_KEY.equals(eventKey)) {
                        return eventService.doAppointmentReceiveFetch(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION_KEY.equals(eventKey)) {
                        return eventService.doMembershipNotification(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_PRODUCT_SHOWCASE_KEY.equals(eventKey)) {
                        return eventService.doProductShowCase(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_PRODUCT_INQUIRY_KEY.equals(eventKey)) {
                        return eventService.doProductInquiry(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_ABOUT_US_KEY.equals(eventKey)) {
                        return eventService.doAboutUs(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY_KEY.equals(eventKey)) {
                        return eventService.doCurrentShopActivity(fromUserName, toUserName);
                    }
                }
            } else {
                String content = requestMap.get("Content").trim();
                String respContent = tulingApiService.getTulingResult(content);
                // 回复文本消息
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                textMessage.setContent(respContent);
                return MessageUtil.messageToXml(textMessage);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return eventService.doMembershipBinding(fromUserName, toUserName);
        } catch (Exception e) {
            e.printStackTrace();
            return eventService.doErrorHandler(fromUserName, toUserName);
        }
        return null;
    }


}