package com.renovator.dao;

import com.daveayan.transformers.Transformer;
import com.renovator.pojo.User;
import com.renovator.pojo.dto.Preview;
import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.pojo.dto.material.Article;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Repository
public class MaterialDao {
	private static final Logger logger = LoggerFactory
			.getLogger(MaterialDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public String addArticle(Article article) {
		try {
			Serializable id = sessionFactory.getCurrentSession().save(article);
			logger.debug("Add article {} {}", id, article.toString());
			return id.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "-1";
		}
	}

	public List<Article> getArticles() {
		List articles = sessionFactory.getCurrentSession()
				.createQuery(" from com.renovator.pojo.Article").list();
		return articles;
	}

	public String saveMaterial(PushMessageTask material) {
		try {
			Serializable id = sessionFactory.getCurrentSession().save(material);
			logger.debug("Add notifaciton {} {}", id, material.toString());
			return id.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "-1";
		}
	}

	public void updateMaterial(String materialNO, String articleId) {

		sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"update notifaction set msg = concat (msg,';',?) where id = ?")
				.setString(0, articleId)
				.setInteger(1, Integer.parseInt(materialNO)).executeUpdate();

	}

	public List<Preview> getArticlePreview() {

		@SuppressWarnings("unchecked")
		List<Preview> articlePreviews = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select id  ,title ,cover from article order by id desc")
				.setResultTransformer(
						Transformers.aliasToBean(Preview.class)).list();
		return articlePreviews;
	}
	
	public List<Preview> getNotifactionPreview() {

		@SuppressWarnings("unchecked")
		List<Preview> articlePreviews = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select article.id as id  ,title ,cover from material , article where article.id = SUBSTRING_INDEX(material.msg,';',1) order by id asc")
				.setResultTransformer(
						Transformers.aliasToBean(Preview.class)).list();
		return articlePreviews;
	}

	public Article getArticleById(int id) {
		return (Article) sessionFactory.getCurrentSession().get(Article.class, id);
	}

	public void updateArticle(Article article) {
		if(article.getCover() == null){
			String sql = "";
			sql = "update article set title = :title ,content = :content where id = :id ";
			sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger("id", article.getId()).setString("title", article.getTitle()).setString("content",article.getContent()).executeUpdate();
		}else{
			sessionFactory.getCurrentSession().saveOrUpdate(article);
		}
	}

}
