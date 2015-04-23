package com.renovator.service;

import java.io.File;
import java.util.List;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpMassGroupMessage;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;

import com.renovator.pojo.dto.material.Article;
import com.renovator.util.PropertyHolder;

/**
 * @author wade
 * @version 2015年4月23日 上午12:24:29
 */
public class WeixinService {

    private static WxMpServiceImpl wxMpService;
    static {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        String appid = "wx088c19c112a7794e";
        String appsecret = "7a3e0e4ee7dd2a70e17c897101c4dacc";
        config.setAppId(appid); // 设置微信公众号的appid
        config.setSecret(appsecret); // 设置微信公众号的app corpSecret
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
    }

    public static WxMpServiceImpl getService() {
        return wxMpService;
    }

    public static void send(String massMsgNews, List<com.renovator.pojo.dto.material.Article> articles) throws WxErrorException {
        

        WxMpMassNews news = new WxMpMassNews();

        for (Article article : articles) {
            
            File file = new File(PropertyHolder.MATERIAL_PICTURE_DIR,article.getCover());
            // 先上传图文消息里需要的图片
            WxMediaUploadResult uploadMediaRes = wxMpService.mediaUpload(WxConsts.CUSTOM_MSG_IMAGE, file);
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
         //动态维护的groupId为100
         massGroupMessage.setGroupId(100L);
         WxMpMassSendResult massResult =wxMpService.massGroupMessageSend(massGroupMessage);
         System.out.println(massResult.getErrorMsg());
         
    }

}
