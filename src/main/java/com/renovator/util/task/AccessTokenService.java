package com.renovator.util.task;

import com.renovator.pojo.AccessToken;
import com.renovator.util.WeixinUtil;

/**
 * 定时任务 获取accessToken
 * 
 * @author wade
 * @version 2015年4月21日 上午12:08:20
 */

public class AccessTokenService {

    public static String access_token = "";

    public static String getAccessToken() {
        // TODO placeHolder
        String appid = "wx088c19c112a7794e";
        String appsecret = "7a3e0e4ee7dd2a70e17c897101c4dacc";
        AccessToken acceessToken = WeixinUtil.getAccessToken(appid, appsecret);
        return acceessToken.getToken();
    }

    // TODO cron job

}
