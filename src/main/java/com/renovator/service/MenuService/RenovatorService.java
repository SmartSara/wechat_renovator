package com.renovator.service.MenuService;

import com.renovator.message.req.TextMessage;
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
     * 处理微信发来的请求
     *
     * @param request
     * @return xml
     */
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
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