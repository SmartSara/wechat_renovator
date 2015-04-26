package com.renovator.service.menu;

import com.renovator.exception.UserNotFoundException;
import com.renovator.pojo.User;
import com.renovator.pojo.message.resp.Article;
import com.renovator.pojo.message.resp.NewsMessage;
import com.renovator.service.ServiceService;
import com.renovator.service.UserService;
import com.renovator.util.MessageUtil;
import com.renovator.util.PropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/26.
 */
@Service
public class EventService {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    public String doMembershipBinding(String fromUserName, String toUserName) {
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

    public String doAboutUs(String fromUserName, String toUserName) {
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
        article.setPicUrl(PropertyHolder.SERVER + "/images/logo.jpg");
        article.setUrl(PropertyHolder.SERVER + "/mall/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    public String doProductInquiry(String fromUserName, String toUserName) {
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

    public String doCurrentShopActivity(String fromUserName, String toUserName) {
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

    public String doProductShowCase(String fromUserName, String toUserName) {
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
        article.setPicUrl(PropertyHolder.SERVER + "/repository/images/mall.jpg");
        article.setUrl(PropertyHolder.SERVER + "/mall/index.html");
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    public String doMembershipNotification(String fromUserName, String toUserName) throws UserNotFoundException {
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
        article.setPicUrl(PropertyHolder.SERVER + "/repository/images/notification.jpeg");
        article.setUrl(PropertyHolder.SERVER + "/notification/setting.html?openId=" + fromUserName);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    public String doAppointmentReceiveFetch(String fromUserName, String toUserName) throws UserNotFoundException {
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
        article.setPicUrl(PropertyHolder.SERVER + "/repository/images/appointment.jpg");
        article.setUrl(PropertyHolder.SERVER + "/appointment/appointment.html?openId=" + fromUserName);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    public String doErrorHandler(String fromUserName, String toUserName) {
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

    public String doCurrentOrderStatus(String fromUserName, String toUserName) throws UserNotFoundException {
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
                record.setTitle(String.format("日期 ：%s\n商品 ：%s\n订单状态 ：%s", service.getTs(), service.getProduct().getName(), service.getStatus()));
                articleList.add(record);
            }

        }


        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    public String doMembershipBalance(String fromUserName, String toUserName) throws UserNotFoundException {
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

    public String doMembershipExpense(String fromUserName, String toUserName) throws UserNotFoundException {
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
                record.setTitle(String.format("日期 ：%s\n商品 ：%s", service.getTs(), service.getProduct().getName()));
                articleList.add(record);
            }
        }

        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);

    }
}
