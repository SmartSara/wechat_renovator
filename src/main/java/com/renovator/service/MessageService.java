package com.renovator.service;

import org.springframework.stereotype.Service;

/**
 * Created by darlingtld on 2015/4/6.
 */
@Service
public class MessageService {
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access
    public static final String UPLOAD_IMAGE_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";// 上传多媒体文件
    public static final String UPLOAD_FODDER_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
    public static final String GET_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get"; // url
    public static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";

    public void sendMessage(String xmlMsg){

    }


}
