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
            String respContent = String.format("��ظ�����\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n" +
                            "�ظ�%s��%s\n",
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
        article.setTitle("����˺�");
        article.setDescription("�󶨻�Ա�˺ţ�ʹ��΢�Ż�ȡ������Ϣ");
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
        article.setTitle("��˾���");
        article.setDescription("����鿴���̵�ַ����ϵ��ʽ��Ӫҵʱ��");
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
        article.setTitle("������Ʒ��ѯ");
        article.setDescription("�����ѯ������Ʒ");
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
        article.setTitle("��ǰ���ڽ��еĵ��̻");
        article.setDescription("����鿴��ǰ���ڽ��еĵ��̻");
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
        article.setTitle("������Ʒչʾ");
        article.setDescription("����鿴������Ʒչʾ");
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
        article.setTitle("��Ա�����ѣ����������ѡ���Ա���������ѣ�");
        article.setDescription("������û�Ա�����ѣ����������ѡ���Ա���������ѣ�");
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
        article.setTitle("ԤԼ�����ջ�/�ͻ�");
        article.setDescription("���ԤԼ�����ջ�/�ͻ�");
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
        article.setTitle("������������Ϣ�У��Ժ����԰�~~");
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
                record.setTitle(String.format("���� ��%s ��Ʒ ��%s", service.getTs(), service.getProduct().getName()));
                articleList.add(record);
                Article status = new Article();
                status.setTitle(String.format("����״̬ ��%s", service.getStatus()));
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
                record.setTitle(String.format("���� ��%s ��Ʒ ��%s", service.getTs(), service.getProduct().getName()));
                articleList.add(record);
            }
        }

        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);

    }

}