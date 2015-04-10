package com.renovator.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.renovator.pojo.Article;
import com.renovator.pojo.dto.ArticlePreview;
import com.renovator.service.MaterialService;

/**
 * Created by darlingtld on 2015/4/4.
 */
@Scope("prototype")
@Controller
@RequestMapping("/material")
public class MaterialController {
	private static final Logger logger = LoggerFactory
			.getLogger(MaterialController.class);
	@Autowired
	private MaterialService materialService;

	@RequestMapping(value = "/{materialNo}/article/add", method = RequestMethod.POST)
	public @ResponseBody String addArticle(@PathVariable String materialNo,
			@RequestParam("cover") MultipartFile cover,
			@RequestParam("title") String title,
			@RequestParam("content") String content) {

		System.out.println(title);
		System.out.println(content);
		System.out.println("Cover size : " + cover.getSize());
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		try {
			article.setCover(cover.getBytes());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return materialService.addArticle(materialNo, article);
	}
	
	@RequestMapping(value = "/article/preview")
	public @ResponseBody Object getArticlePreview() {
		
		List<ArticlePreview> articlePreviews = materialService.getArticlePreview();

		 return articlePreviews;
	}

}
