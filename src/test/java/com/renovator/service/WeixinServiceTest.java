package com.renovator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.renovator.pojo.dto.material.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class WeixinServiceTest {

	@Autowired
	protected WeixinService weixinService;
	
	@Test
	public void scheduleTaskTest() throws InterruptedException{
	    Thread.sleep(TimeUnit.MINUTES.toMillis(1L));
	}

	@Test
	public void customMessageSendTest(){
	    
	    String openid = "osNcxswDGQTSkNs2L03jXedbO1Q8";//wade
	    
	    List<WxMpCustomMessage.WxArticle> articles =  new ArrayList<>();
        WxMpCustomMessage.WxArticle article1 = new WxMpCustomMessage.WxArticle();
        article1.setUrl("www.baidu.com");
        article1.setPicUrl("http://www.baidu.com/img/baidu_jgylogo3.gif");
        article1.setDescription("Is Really A Happy Day");
        article1.setTitle("Happy Day");
        WxMpCustomMessage.WxArticle article2 = new WxMpCustomMessage.WxArticle();
        article2.setUrl("http://bbs.hupu.com/12507040.html");
        article2.setPicUrl("http://i1.hoopchina.com.cn/user/671/19731671/19731671_big_3.jpg");
//        article2.setDescription("WHERE AMAZING HAPPEND");
        article2.setTitle("NBA PLAYOFF ROCKETS");
        articles.add(article1);
        articles.add(article2);
        
	    
	    weixinService.customMessageSend(openid, articles);
	}
	
	@Test
	public void massGroupMessageSendTest(){
	    
	    List<Article> articles = new ArrayList<Article>();
	    Article  article =  new Article();
	    article.setTitle("wade大帅哥");
	    article.setContent("Test");
	    //cover的目录是renovator中配置的pic地址，这个设计以后改善
	    article.setCover("tG.jpg");
	    articles.add(article);
	    
	    weixinService.massGroupMessageSend(articles);
	}
}
