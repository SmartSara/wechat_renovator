package com.renovator.service.menu;

import com.renovator.pojo.message.req.TextMessage;
import com.renovator.util.MessageUtil;
import com.renovator.util.PropertyHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Renovator service
 */
@Service
public class RenovatorService {

    /**
     * @param request
     * @return xml
     */
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            String respContent = "你好";

            Map<String, String> requestMap = MessageUtil.parseXml(request);

            String fromUserName = requestMap.get("FromUserName");
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "你好" + MessageUtil.REQ_MESSAGE_TYPE_TEXT;
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "你好" + MessageUtil.REQ_MESSAGE_TYPE_IMAGE;
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "你好" + MessageUtil.REQ_MESSAGE_TYPE_LOCATION;
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "你好" + MessageUtil.REQ_MESSAGE_TYPE_LINK;
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "你好" + MessageUtil.REQ_MESSAGE_TYPE_VOICE;
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event");
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "你好" + MessageUtil.EVENT_TYPE_SUBSCRIBE;
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");

                    if (eventKey.equals(PropertyHolder.MENU_MEMBERSHIP_BALANCE)) {
                        respContent = PropertyHolder.MENU_MEMBERSHIP_BALANCE;
                    } else if (eventKey.equals(PropertyHolder.MENU_MEMBERSHIP_EXPENSE)) {
                        respContent = PropertyHolder.MENU_MEMBERSHIP_EXPENSE;
                    } else if (eventKey.equals(PropertyHolder.MENU_MEMBERSHIP_BIRTH_NOTIFICATION)) {
                        respContent = PropertyHolder.MENU_MEMBERSHIP_BIRTH_NOTIFICATION;
                    } else if (eventKey.equals(PropertyHolder.MENU_MEMBERSHIP_CARD_EXPIRATION)) {
                        respContent = PropertyHolder.MENU_MEMBERSHIP_CARD_EXPIRATION;
                    } else if (eventKey.equals(PropertyHolder.MENU_MEMBERSHIP_CARD_BALANCE_NOTIFICATION)) {
                        respContent = PropertyHolder.MENU_MEMBERSHIP_CARD_BALANCE_NOTIFICATION;
                    } else if (eventKey.equals(PropertyHolder.MENU_CURRENT_ORDER_STATUS)) {
                        respContent = PropertyHolder.MENU_CURRENT_ORDER_STATUS;
                    } else if (eventKey.equals(PropertyHolder.MENU_APPOINTMENT_RECEIVE)) {
                        respContent = PropertyHolder.MENU_APPOINTMENT_RECEIVE;
                    } else if (eventKey.equals(PropertyHolder.MENU_APPOINTMENT_FETCH)) {
                        respContent = PropertyHolder.MENU_APPOINTMENT_FETCH;
                    } else if (eventKey.equals(PropertyHolder.MENU_PRODUCT_SHOWCASE)) {
                        respContent = PropertyHolder.MENU_PRODUCT_SHOWCASE;
                    } else if (eventKey.equals(PropertyHolder.MENU_PRODUCT_INQUIRY)) {
                        respContent = PropertyHolder.MENU_PRODUCT_INQUIRY;
                    } else if (eventKey.equals(PropertyHolder.MENU_SERVICE_PRICE_INQUIRY)) {
                        respContent = PropertyHolder.MENU_SERVICE_PRICE_INQUIRY;
                    } else if (eventKey.equals(PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY)) {
                        respContent = PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY;
                    } else if (eventKey.equals(PropertyHolder.MENU_COOPERATION)) {
                        respContent = PropertyHolder.MENU_COOPERATION;
                    } else if (eventKey.equals(PropertyHolder.MENU_ABOUT_US)) {
                        respContent = PropertyHolder.MENU_ABOUT_US;
                    } else if (eventKey.equals(PropertyHolder.MENU_MORE_INFO)) {
                        respContent = PropertyHolder.MENU_MORE_INFO;
                    }
                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.messageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }

}