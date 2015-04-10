package com.renovator.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renovator.dao.MaterialDao;
import com.renovator.pojo.Article;
import com.renovator.pojo.Material;
import com.renovator.pojo.dto.ArticlePreview;
import com.renovator.util.MaterialType;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Service
public class MaterialService {

	private static final Logger logger = LoggerFactory
			.getLogger(MaterialService.class);

	@Autowired
	private MaterialDao materialDao;

	@Transactional
	public String addArticle(Article article) {
		return materialDao.addArticle(article);
	}

	@Transactional
	public String addArticle(String materialNO, Article article) {

		String articleId = materialDao.addArticle(article);

		if ("blank".equals(materialNO)) {

			Material material = new Material();
			material.setMsg(articleId);
			material.setType(MaterialType.ARTICLE);
			material.setScheduledTime(new Date());
			materialNO = materialDao.saveMaterial(material);

		} else {
			materialDao.updateMaterial(materialNO, articleId);
		}

		return materialNO;
	}

	@Transactional
	public List<ArticlePreview> getArticlePreview() {
		
		return materialDao.getArticlePreview();
	}

}
