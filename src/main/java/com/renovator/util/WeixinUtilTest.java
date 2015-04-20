package com.renovator.util;

/**
 * Created by darlingtld on 2015/2/20.
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import antlr.collections.List;

import com.renovator.pojo.AccessToken;

/**
 * 公众平台通用接口工具类
 */
public class WeixinUtilTest {

    //获取openid
    public static String OPENIDLISTURL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    
    //获取组消息
    public static String GROUPLISTURL ="https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    
    //获取素材
    public static String MaterialListUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
    //上传消息素材
    public static String Upload_news_Url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    public static String GROUP_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    
    public static void main(String[] args) throws JSONException, IOException {

        String appid = "wx088c19c112a7794e";
        String appsecret = "7a3e0e4ee7dd2a70e17c897101c4dacc";
        AccessToken acceessToken = WeixinUtil.getAccessToken(appid, appsecret);
        
//        System.out.println(acceessToken.getToken());
//        String url = OPENIDLISTURL.replace("ACCESS_TOKEN", acceessToken.getToken());
//        String url = GROUPLISTURL.replace("ACCESS_TOKEN", acceessToken.getToken());
        
        Map <String,Object>  data = new HashMap<String, Object>();
   
        
        JSONObject json = new JSONObject(data);
        json.put("type", "news");
        json.put("offset", 0);
        json.put("count", 5);
//        System.out.println(json.toString());
        
         java.util.List<String> lines = Files.readAllLines(Paths.get("C:/Users/dewei.zdw/git/wechat_renovator/src/main/resources/news.txt"), Charset.defaultCharset());
        
//        String url = MaterialListUrl.replace("ACCESS_TOKEN", acceessToken.getToken());
        String url = GROUP_SEND_URL.replace("ACCESS_TOKEN", acceessToken.getToken());
        
        JSONObject textJson =  new JSONObject();
        
        JSONObject filterJson =  new JSONObject();
        filterJson.put("is_to_all", false);
        filterJson.put("group_id", "100");
        textJson.put("filter",filterJson);
        JSONObject contentJson =  new JSONObject();
        contentJson.put("content", "wade test2");
        textJson.put("text", contentJson);
        textJson.put("msgtype", "text");
        
        System.out.println(url);
        System.out.println(textJson.toString());
        JSONObject result = WeixinUtil.httpRequest(url, "POST", textJson.toString());
//        System.out.println(lines.get(0));
//        JSONObject result = WeixinUtil.httpRequest(url, "POST", lines.get(0));
        
        System.out.println(result.toString());
        

    }

}
