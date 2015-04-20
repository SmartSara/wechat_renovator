package com.renovator.service;

import com.renovator.dao.UserDao;
import com.renovator.pojo.AccessToken;
import com.renovator.pojo.User;
import com.renovator.util.PropertyHolder;
import com.renovator.util.WeixinUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Service
public class UserService {

    private static final String GET_OPEN_IDS_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Transactional
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Transactional
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Transactional
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    @Transactional
    public User getUserWithOpenId(String openId) {
        return userDao.getUserWithOpenId(openId);
    }

    /**
     * @param name
     * @param contact
     * @param address
     * @param birthday
     * @param balance
     * @return users
     * @throws ParseException
     */
    @Transactional
    public List<User> searchUsers(String name, String contact, String address, String birthday, String balance) throws ParseException {
        return userDao.searchUsers(name, contact, address, birthday, balance);
    }

    @Transactional
    public void bindAccount(String username, String contact, String email, String openId) throws Exception {
        if (userDao.getUserByContact(contact) != null) {
            throw new Exception("该手机号已注册！");
        }
        User user = new User();
        user.setName(username);
        user.setContact(contact);
        user.setEmail(email);
        user.setOpenId(openId);
        userDao.addUser(user);
    }

    public List<String> getAllFansOpenIds() {
        AccessToken at = WeixinUtil.getAccessToken(PropertyHolder.APPID, PropertyHolder.APPSECRET);
        String url = GET_OPEN_IDS_URL.replace("ACCESS_TOKEN", at.getToken()).replace("NEXT_OPENID", "");
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        List<String> fansOpenIds = new ArrayList<>();
        if (null != jsonObject) {
            try {
                if (jsonObject.has("errcode") && 0 != jsonObject.getInt("errcode")) {
                    logger.error("Error getting fans' open ids. errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                } else {
                    logger.debug("Total fans {}, fetched fans {}", jsonObject.getInt("total"), jsonObject.getInt("count"));
                    JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("openid");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        fansOpenIds.add(jsonArray.getString(i));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        logger.info(fansOpenIds.toString());
        return fansOpenIds;
    }
}
