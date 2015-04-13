package com.renovator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.renovator.pojo.dto.Preview;
import com.renovator.pojo.dto.material.Article;
import com.renovator.service.MaterialService;
import com.renovator.util.PropertyHolder;

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

	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public @ResponseBody String addArticleMaterial(
			@RequestParam("cover") MultipartFile cover,
			@RequestParam("title") String title,
			@RequestParam("content") String content) {

		System.out.println(title);
		System.out.println("Cover size : " + cover.getSize());
		Article article = new Article();
		
		String coverName = cover.getOriginalFilename();
		
		article.setTitle(title);
		article.setContent(content);
		article.setCover(coverName);
		try {
			File storedCover =  new File(PropertyHolder.MATERIAL_PICTURE_DIR,coverName);
			storedCover.createNewFile();
			IOUtils.copy(cover.getInputStream(),new FileOutputStream(storedCover));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String artileNO = materialService.addArticle(article);
		
		return artileNO;
	}
	
	@RequestMapping(value = "/article/noCover/update", method = RequestMethod.POST)
	public @ResponseBody String updateArticleMaterialWithoutCover(
			@RequestParam("id") int id,
			@RequestParam("title") String title,
			@RequestParam("content") String content) {

		Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setContent(content);
		materialService.updateArticle(article);
		return "ok";
	}
	
	@RequestMapping(value = "/article/update", method = RequestMethod.POST)
	public @ResponseBody String updateArticleMaterial(
			@RequestParam("id") int id,
			@RequestParam(value = "cover") MultipartFile cover,
			@RequestParam("title") String title,
			@RequestParam("content") String content) {

		System.out.println(title);
		System.out.println("Cover size : " + cover.getSize());
		Article article = new Article();
		
		String coverName = cover.getOriginalFilename();
		article.setCover(coverName);
		article.setId(id);
		try {
			File storedCover =  new File(PropertyHolder.MATERIAL_PICTURE_DIR,coverName);
			storedCover.createNewFile();
			IOUtils.copy(cover.getInputStream(),new FileOutputStream(storedCover));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		article.setTitle(title);
		article.setContent(content);
		materialService.updateArticle(article);
		
		return "ok";
	}
	
	@RequestMapping(value = "/article/preview")
	public @ResponseBody Object getArticlePreview() {
		
		List<Preview> articlePreviews = materialService.getArticlePreview();

		 return articlePreviews;
	}
	
	
	@RequestMapping(value = "/article/{id}")
	public @ResponseBody Object getArticleByID(@PathVariable int id) {
		
		Article article = materialService.getArticleById(id);

		 return article;
	}
	
	@RequestMapping(value = "/article/list")
	public @ResponseBody Object getArticleList() {
		
		List<Preview> articlePreviews = materialService.getArticlePreview();

		 return articlePreviews;
	}

}
