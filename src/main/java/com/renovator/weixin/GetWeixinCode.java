//package com.renovator.weixin;
//
//import com.renovator.util.PropertyHolder;
//
//import java.net.URLEncoder;
//
///**
// * Created by tangld on 2015/4/7.
// */
//public class GetWeixinCode {
//
//    public static String  GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//
//    //获取授权请求
//
//    public static String getCodeRequest(){
//
//        String result = null;
//
//        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8(PropertyHolder.APPID));
//
//        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8(Constants.REDIRECT_URI));
//
//        GetCodeRequest = GetCodeRequest.replace("SCOPE", Constants.SCOPE);
//
//        result = GetCodeRequest;
//
//        return result;
//
//    }
//
//    //进行转码
//
//    public static String urlEnodeUTF8(String str){
//
//        String result = str;
//
//        try {
//
//            result = URLEncoder.encode(str, "UTF-8");
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        return result;
//
//    }
//
//}
