package com.renovator.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renovator.pojo.dto.Preview;
import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.service.PushMessageService;

/**
 * Created by darlingtld on 2015/4/4.
 */
@Scope("prototype")
@Controller
@RequestMapping("/pushMessage")
public class PushMessageController {
	private static final Logger logger = LoggerFactory
			.getLogger(PushMessageController.class);
	@Autowired
	private PushMessageService pushMessageService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String addArticleMaterial( 
                                                    @RequestBody PushMessageTask task) {
        pushMessageService.addPushMessage(task);
        return "ok";
    }

	@RequestMapping(value = "/{type}/preview")
	public @ResponseBody Object getArticlePreview(@PathVariable String type) {

		List<Preview> preview = pushMessageService.getPreview(type);

		return preview;
	}

	@RequestMapping(value = "/{type}/preview/{pushMessageTaskId}")
	public @ResponseBody Object getArticlePreview(@PathVariable String type,
			@PathVariable int pushMessageTaskId) {

		List<Preview> preview = pushMessageService.getPreview(type,
				pushMessageTaskId);
		return preview;
	}

	@RequestMapping(value = "/del/{pushMessageTaskId}/{articleId}")
	public @ResponseBody Object delArticleInPushMessage(
			@PathVariable int pushMessageTaskId, @PathVariable int articleId) {
		
		return pushMessageService
				.delArticleInPushMessage(pushMessageTaskId, articleId);
	}
	@RequestMapping(value = "/delete/{type}/{pushMessageTaskId}")
	public @ResponseBody Object delPushMessage(
			@PathVariable String type, @PathVariable int pushMessageTaskId) {
		return pushMessageService.deletePushMessage(type,pushMessageTaskId);
	}
	
	@RequestMapping(value= "/update/msgs")
	public @ResponseBody Object updatePushMessageTaskMsgs(@RequestParam int pushMessageTaskId ,@RequestParam String msgs){
	    
	   return  pushMessageService.updatePushMessageTaskMsgs(pushMessageTaskId,msgs);
	    
	}
	
	   @RequestMapping(value= "/update/scheduledTime")
	    public @ResponseBody Object updatePushMessageTaskScheduledTime(@RequestParam int pushMessageTaskId ,@RequestParam java.util.Date modifyScheduledTime){
	        
	       return  pushMessageService.updatePushMessageTaskScheduledTime(pushMessageTaskId,modifyScheduledTime);
	        
	    }

}
