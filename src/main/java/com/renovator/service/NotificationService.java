package com.renovator.service;

import com.renovator.pojo.User;
import com.renovator.pojo.message.resp.Article;
import com.renovator.pojo.message.resp.NewsMessage;
import com.renovator.util.MessageUtil;
import com.renovator.util.PropertyHolder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/5.
 */
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private static final double BALANCE_NOTIFICATION_BALANCE = 100;

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    public void notifyBalance() {
        logger.info("Notify balance");
        List<User> userList = userService.getUserList();
        for (User user : userList) {
            if (user.getBalance() < BALANCE_NOTIFICATION_BALANCE) {
                sendNotificationMessage(PropertyHolder.APPID, user.getOpenId(), "余额不足" + BALANCE_NOTIFICATION_BALANCE + "元", "您的当前余额不足，请及时充值", PropertyHolder.SERVER + "/images/logo.png", PropertyHolder.SERVER + "/product_showcase/introduction.html");
            }
        }
    }


    public void notifyBirthday() {
        logger.info("Notify birthday");
        List<User> userList = userService.getUserList();
        for (User user : userList) {
            DateTime birthday = new DateTime(user.getBirthday());
            DateTime today = new DateTime();
            if (birthday.getDayOfYear() == today.getDayOfYear()) {
                sendNotificationMessage(PropertyHolder.APPID, user.getOpenId(), "生日快乐", user.getName() + ",祝您生日快乐！", PropertyHolder.SERVER + "/images/logo.png", PropertyHolder.SERVER + "/product_showcase/introduction.html");
            }
        }
    }

    private void sendNotificationMessage(String appid, String toOpenId, String title, String description, String picUrl, String url) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(toOpenId);
        newsMessage.setFromUserName(appid);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        messageService.sendMessage(MessageUtil.messageToXml(newsMessage));
    }
}
