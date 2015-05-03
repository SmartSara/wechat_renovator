package com.renovator.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renovator.dao.MaterialDao;
import com.renovator.dao.PushMessageDao;
import com.renovator.pojo.dto.NotificationType;
import com.renovator.pojo.dto.Preview;
import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.pojo.dto.material.Article;
import com.renovator.util.MaterialType;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Service
public class PushMessageService {

	private static final Logger logger = LoggerFactory
			.getLogger(PushMessageService.class);

	@Autowired
	private PushMessageDao pushMessageDao;


	@Transactional
	public void addPushMessage(PushMessageTask message) {
		
		pushMessageDao.addPushMessageTask(message);
	}

	@Transactional
	public List<Preview> getPreview(String type) {
		
		
		return pushMessageDao.getPreview(type);
	}

	@Transactional
	public List<Preview> getPreview(String type,int pushMessageTaskId) {
		return pushMessageDao.getPreview(type,pushMessageTaskId);
	}
	
	@Transactional
	public String delArticleInPushMessage(int pushMessageTaskId, int articleId) {
		 return  pushMessageDao.delArticleInPushMessage(pushMessageTaskId,articleId);
	}

	@Transactional
	public Object deletePushMessage(String type,int pushMessageTaskId) {
		
		return pushMessageDao.deletePushMessage(type,pushMessageTaskId);
	}

	@Transactional
    public Object updatePushMessageTaskMsgs(int pushMessageTaskId, String msgs) {
	    return pushMessageDao.updatePushMessageTaskMsgs(pushMessageTaskId,msgs);
    }

	@Transactional
    public List<Article> getArticles(NotificationType type) {
        
       return  pushMessageDao.getArticles(type);
        
    }

	@Transactional 
    public  List<Article> getToBeSendTaskArticles( PushMessageTask pushMessageTask) {
	    return pushMessageDao.getToBeSendTaskArticles(pushMessageTask);
    }
    
	@Transactional
    public PushMessageTask getEarliestTask(){
       return pushMessageDao.getEarliestTask();
    }

	@Transactional
    public void setTaskStatus(PushMessageTask earliestTask, String status) {
        pushMessageDao.setTaskStatus(earliestTask, status);
        
    }

	@Transactional
    public Object updatePushMessageTaskScheduledTime(int pushMessageTaskId, Date modifyScheduledTime) {
        return pushMessageDao.updatePushMessageTaskScheduledTime(pushMessageTaskId,modifyScheduledTime);
    }
}
