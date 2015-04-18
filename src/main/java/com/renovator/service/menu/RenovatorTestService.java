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
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

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
                    return doMembershipBalance(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_MEMBERSHIP_EXPENSE_KEY.equals(content)) {
                    return doMembershipExpense(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_CURRENT_ORDER_STATUS_KEY.equals(content)) {
                    return doCurrentOrderStatus(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_APPOINTMENT_RECEIVE_FETCH_KEY.equals(content)) {
                    return doAppointmentReceiveFetch(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_MEMBERSHIP_NOTIFICATION_KEY.equals(content)) {
                    return doMembershipNotification(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_PRODUCT_SHOWCASE_KEY.equals(content)) {
                    return doProductShowCase(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_PRODUCT_INQUIRY_KEY.equals(content)) {
                    return doProductInquiry(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_ABOUT_US_KEY.equals(content)) {
                    return doAboutUs(fromUserName, toUserName);
                } else if (PropertyHolder.MENU_CURRENT_SHOP_ACTIVITY_KEY.equals(content)) {
                    return doCurrentShopActivity(fromUserName, toUserName);
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
            return doMembershipBinding(fromUserName, toUserName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return doErrorHandler(fromUserName, toUserName);

        }
    }

    private String doMembershipBinding(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("请绑定账号");
        article.setDescription("绑定会员账号，使用微信获取更多信息");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/account_binding/index.html?open_id=" + fromUserName);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doAboutUs(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("公司简介");
        article.setDescription("点击查看店铺地址、联系方式、营业时间");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/mall/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doProductInquiry(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("寄卖商品查询");
        article.setDescription("点击查询寄卖商品");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/mall/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doCurrentShopActivity(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("当前正在进行的店铺活动");
        article.setDescription("点击查看当前正在进行的店铺活动");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/mall/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doProductShowCase(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("寄卖商品展示");
        article.setDescription("点击查看寄卖商品展示");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/mall/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doMembershipNotification(String fromUserName, String toUserName) throws UserNotFoundException {
        User user = userService.getUserWithOpenId(fromUserName);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("会员卡提醒（生日月提醒、会员卡到期提醒）");
        article.setDescription("点击设置会员卡提醒（生日月提醒、会员卡到期提醒）");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/pages/index.html?openId=" + fromUserName);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doAppointmentReceiveFetch(String fromUserName, String toUserName) throws UserNotFoundException {
        User user = userService.getUserWithOpenId(fromUserName);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("预约上门收货/送货");
        article.setDescription("点击预约上门收货/送货");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/pages/index.html?openId=" + fromUserName);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doErrorHandler(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("服务器正在休息中，稍后再试吧~~");
        article.setDescription("OooooOoooOooOo.");
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.png");
        article.setUrl(PropertyHolder.SERVER + "/pages/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doCurrentOrderStatus(String fromUserName, String toUserName) throws UserNotFoundException {
        User user = userService.getUserWithOpenId(fromUserName);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        List<com.renovator.pojo.Service> serviceList = serviceService.getUncheckedServiceListByUserId(user.getId());
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<>();
        if (null == serviceList || serviceList.isEmpty()) {
            Article article = new Article();
            article.setTitle("服务单目前状态");
            article.setDescription("您当前还没有待处理的订单");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/order.jpg");
            articleList.add(article);
        } else {
            Article article = new Article();
            article.setTitle("服务单目前状态");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/order.jpg");
            articleList.add(article);

            for (com.renovator.pojo.Service service : serviceList) {
                Article record = new Article();
                record.setTitle(String.format("日期 ：%s 商品 ：%s", service.getTs(), service.getProduct().getName()));
                articleList.add(record);
                Article status = new Article();
                status.setTitle(String.format("订单状态 ：%s", service.getStatus()));
                articleList.add(status);
            }

        }


        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    private String doMembershipBalance(String fromUserName, String toUserName) throws UserNotFoundException {
        User user = userService.getUserWithOpenId(fromUserName);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle("会员卡余额");
        article.setPicUrl(PropertyHolder.SERVER + "/repository/images/vip_card.jpg");
        articleList.add(article);

        Article username = new Article();
        username.setTitle(String.format("会员名 ：%s", user.getName()));
        articleList.add(username);

        Article balance = new Article();
        balance.setTitle(String.format("会员卡余额 ：%s 元", user.getBalance()));
        articleList.add(balance);

        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);

        return MessageUtil.messageToXml(newsMessage);
    }

    private String doMembershipExpense(String fromUserName, String toUserName) throws UserNotFoundException {
        User user = userService.getUserWithOpenId(fromUserName);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        List<com.renovator.pojo.Service> serviceList = serviceService.getServiceListByUserId(user.getId(), 3);
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<>();
        if (null == serviceList || serviceList.isEmpty()) {
            Article article = new Article();
            article.setTitle("会员卡消费记录");
            article.setDescription("您还没有消费过哦");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/expense.jpg");
            articleList.add(article);
        } else {
            Article article = new Article();
            article.setTitle("会员卡消费记录");
            article.setDescription("点击查看您的所有消费记录");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/expense.jpg");
            article.setUrl(PropertyHolder.SERVER + "/expense/expense_record.html?openId=" + fromUserName);
            articleList.add(article);

            //list the latest three expense record
            for (com.renovator.pojo.Service service : serviceList) {
                Article record = new Article();
                record.setTitle(String.format("日期 ：%s 商品 ：%s", service.getTs(), service.getProduct().getName()));
                articleList.add(record);
            }
        }

        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);

    }

}