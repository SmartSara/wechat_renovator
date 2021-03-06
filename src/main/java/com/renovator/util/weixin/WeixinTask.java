
package com.renovator.util.weixin;

import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.pojo.dto.material.Article;
import com.renovator.service.PushMessageService;
import com.renovator.service.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class WeixinTask {

    private static final Logger logger = LoggerFactory
            .getLogger(WeixinTask.class);

    private volatile boolean   completed = false;
    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WeixinService      weixinService;

    ScheduledExecutorService  executor =  Executors.newScheduledThreadPool(2);

    public void doSendJob() {


        if (!completed) {
            final PushMessageTask earliestTask = pushMessageService.getEarliestTask();
            if(earliestTask ==  null ){
                return ;
            }

            final List<Article> articles = pushMessageService.getToBeSendTaskArticles(earliestTask);

            if(articles == null || articles.isEmpty()){
                return ;
            }
            executor.schedule(new Runnable() {
                public void run() {
                    String result = weixinService.massGroupMessageSend(articles);
                    if (result.equals("sucess")) {
                        pushMessageService.setTaskStatus(earliestTask, "SUCCESS");
                        completed = true;
                        logger.info("do send job successfully");
                    } else {
                        completed = false;
                    }
                }
            }, earliestTask.getScheduledTime().getTime() -System.currentTimeMillis() , TimeUnit.MILLISECONDS);


        }
    }

    public void resetJob() {
        logger.info("do reset job");
        this.completed = false;
    }

    public void doSendToWadeJob() {
        if (!completed) {
            final PushMessageTask earliestTask = pushMessageService.getEarliestTask();
            if(earliestTask ==  null ){
                return ;
            }

            final List<Article> articles = pushMessageService.getToBeSendTaskArticles(earliestTask);

            if(articles == null || articles.isEmpty()){
                return ;
            }
            executor.schedule(new Runnable() {
                public void run() {
                    weixinService.customSend();
                    pushMessageService.setTaskStatus(earliestTask, "SUCCESS");
                }
            },  earliestTask.getScheduledTime().getTime() -System.currentTimeMillis() , TimeUnit.MILLISECONDS);
        }
    }

}
