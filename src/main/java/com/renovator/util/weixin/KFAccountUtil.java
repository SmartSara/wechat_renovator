package com.renovator.util.weixin;

import org.json.JSONException;
import org.json.JSONObject;

import com.renovator.util.WeixinUtil;
import com.renovator.util.task.AccessTokenService;

/**
 * @author wade
 * @version 2015年4月21日 下午10:19:23
 */
public class KFAccountUtil extends WeixinUtil {

    public static String create_kf_account_url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";

    public static String createKfAccount() throws JSONException {
        JSONObject postData = new JSONObject();
        postData.put("kf_account", "wade");
        postData.put("nickname", "小德子");
        postData.put("password", "abc123");
        System.out.println(postData.toString());
        String url = create_kf_account_url.replace(ACCESS_TOKEN, AccessTokenService.getAccessToken());
        System.out.println(url);
        JSONObject result = httpRequest(url, POST, postData.toString());
        return (String) result.get("errmsg");
    }

    public static void main(String[] args) throws JSONException {
        String createKfAccount = createKfAccount();
        System.out.println(createKfAccount);
    }

}
