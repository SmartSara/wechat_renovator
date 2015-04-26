package com.renovator.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpMassGroupMessage;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.custombuilder.NewsBuilder;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;

import com.renovator.pojo.dto.material.Article;
import com.renovator.util.PropertyHolder;
import org.springframework.stereotype.Service;

/**
 * @author wade
 * @version 2015年4月23日 上午12:24:29
 */
@Service
public class WeixinService {

    private static final Logger logger = LoggerFactory.getLogger(WeixinService.class);

    private static WxMpServiceImpl wxMpService;

    // init WeixinMPService
    static {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        String appid = PropertyHolder.APPID;
        String appsecret = PropertyHolder.APPSECRET;
        config.setAppId(appid); // 设置微信公众号的appid
        config.setSecret(appsecret); // 设置微信公众号的app corpSecret
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
    }

    public static WxMpServiceImpl getService() {
        return wxMpService;
    }


    public void customSend() {

        String openid = "osNcxswDGQTSkNs2L03jXedbO1Q8";// wade
        WxMpCustomMessage.WxArticle article1 = new WxMpCustomMessage.WxArticle();
        article1.setUrl("www.baidu.com");
        article1.setPicUrl("http://www.baidu.com/img/baidu_jgylogo3.gif");
        article1.setDescription("Is Really A Happy Day");
        article1.setTitle("Happy Day");

        WxMpCustomMessage.WxArticle article2 = new WxMpCustomMessage.WxArticle();
        article2.setUrl("http://bbs.hupu.com/12507040.html");
        article2.setPicUrl("http://i1.hoopchina.com.cn/user/671/19731671/19731671_big_3.jpg");
        // article2.setDescription("WHERE AMAZING HAPPEND");
        article2.setTitle("NBA PLAYOFF ROCKETS");

        WxMpCustomMessage message = WxMpCustomMessage.NEWS().toUser(openid).addArticle(article2).build();
        try {
            wxMpService.customMessageSend(message);
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @return ok代表成功
     */
    public String customMessageSend(String openid, List<WxMpCustomMessage.WxArticle> articles) {

        NewsBuilder newsBuilder = WxMpCustomMessage.NEWS().toUser(openid);
        for (WxMpCustomMessage.WxArticle wxArticle : articles) {
            newsBuilder.addArticle(wxArticle);
        }
        WxMpCustomMessage customMessage = newsBuilder.build();
        try {
            wxMpService.customMessageSend(customMessage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return "error";
        }

        return "ok";

    }

    public void massGroupMessageSend(List<com.renovator.pojo.dto.material.Article> articles) {

        WxMpMassNews news = new WxMpMassNews();

        try {
            for (Article article : articles) {

                File file = new File(PropertyHolder.MATERIAL_PICTURE_DIR, article.getCover());
                // 先上传图文消息里需要的图片
                WxMediaUploadResult uploadMediaRes;
                uploadMediaRes = wxMpService.mediaUpload(WxConsts.CUSTOM_MSG_IMAGE, file);

                WxMpMassNews.WxMpMassNewsArticle article_send = new WxMpMassNews.WxMpMassNewsArticle();
                article_send.setTitle(article.getTitle());
                article_send.setContent(article.getContent());
                article_send.setThumbMediaId(uploadMediaRes.getMediaId());
                article_send.setShowCoverPic(true);
                news.addArticle(article_send);
            }

            WxMpMassUploadResult massUploadResult = wxMpService.massNewsUpload(news);
            WxMpMassGroupMessage massGroupMessage = new WxMpMassGroupMessage();
            massGroupMessage.setMsgtype(WxConsts.MASS_MSG_NEWS);
            massGroupMessage.setMediaId(massUploadResult.getMediaId());

            // 动态维护的groupId为100
            // 如果开发模式只会发送给Dev人员
            if (PropertyHolder.IS_DEBUG_MODE) {
                massGroupMessage.setGroupId(100L);
            }

            WxMpMassSendResult massResult = wxMpService.massGroupMessageSend(massGroupMessage);
            System.out.println(massResult.getErrorMsg());
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
        }

    }

}
