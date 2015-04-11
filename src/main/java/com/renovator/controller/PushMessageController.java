package com.renovator.controller;

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
import com.renovator.util.MaterialType;

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
			@RequestBody PushMessageTask message ) {
		pushMessageService.addPushMessage(message);
		return "ok";
	}
	
	@RequestMapping(value = "/{type}/preview")
	public @ResponseBody Object getArticlePreview(@PathVariable String type) {
		
		List<Preview> preview = pushMessageService.getPreview(type);

		 return preview;
	}

}
