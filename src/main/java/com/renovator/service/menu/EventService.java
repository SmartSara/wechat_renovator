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
        article.setTitle("����˺�");
        article.setDescription("�󶨻�Ա�˺ţ�ʹ��΢�Ż�ȡ������Ϣ");
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
        article.setTitle("��˾���");
        article.setDescription("����鿴���̵�ַ����ϵ��ʽ��Ӫҵʱ��");
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
        article.setTitle("������Ʒ��ѯ");
        article.setDescription("�����ѯ������Ʒ");
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
        article.setTitle("��ǰ���ڽ��еĵ��̻");
        article.setDescription("����鿴��ǰ���ڽ��еĵ��̻");
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
        article.setTitle("������Ʒչʾ");
        article.setDescription("����鿴������Ʒչʾ");
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
        article.setTitle("��Ա�����ѣ����������ѡ���Ա���������ѣ�");
        article.setDescription("������û�Ա�����ѣ����������ѡ���Ա���������ѣ�");
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
        article.setTitle("ԤԼ�����ջ�/�ͻ�");
        article.setDescription("���ԤԼ�����ջ�/�ͻ�");
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
        article.setTitle("������������Ϣ�У��Ժ����԰�~~");
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
            article.setTitle("����Ŀǰ״̬");
            article.setDescription("����ǰ��û�д�����Ķ���");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/order.jpg");
            articleList.add(article);
        } else {
            Article article = new Article();
            article.setTitle("����Ŀǰ״̬");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/order.jpg");
            articleList.add(article);

            for (com.renovator.pojo.Service service : serviceList) {
                Article record = new Article();
                record.setTitle(String.format("���� ��%s\n��Ʒ ��%s\n����״̬ ��%s", service.getTs(), service.getProduct().getName(), service.getStatus()));
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
        article.setTitle("��Ա�����");
        article.setPicUrl(PropertyHolder.SERVER + "/repository/images/vip_card.jpg");
        articleList.add(article);

        Article username = new Article();
        username.setTitle(String.format("��Ա�� ��%s", user.getName()));
        articleList.add(username);

        Article balance = new Article();
        balance.setTitle(String.format("��Ա����� ��%s Ԫ", user.getBalance()));
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
            article.setTitle("��Ա�����Ѽ�¼");
            article.setDescription("����û�����ѹ�Ŷ");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/expense.jpg");
            articleList.add(article);
        } else {
            Article article = new Article();
            article.setTitle("��Ա�����Ѽ�¼");
            article.setDescription("����鿴�����������Ѽ�¼");
            article.setPicUrl(PropertyHolder.SERVER + "/repository/images/expense.jpg");
            article.setUrl(PropertyHolder.SERVER + "/expense/expense_record.html?openId=" + fromUserName);
            articleList.add(article);

            //list the latest three expense record
            for (com.renovator.pojo.Service service : serviceList) {
                Article record = new Article();
                record.setTitle(String.format("���� ��%s\n��Ʒ ��%s", service.getTs(), service.getProduct().getName()));
                articleList.add(record);
            }
        }

        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);

    }
}
