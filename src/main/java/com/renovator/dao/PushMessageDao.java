package com.renovator.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renovator.pojo.dto.Preview;
import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.util.MaterialType;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Repository
public class PushMessageDao {
	private static final Logger logger = LoggerFactory
			.getLogger(PushMessageDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public String addPushMessageTask(PushMessageTask message) {
		try {
			Serializable id = sessionFactory.getCurrentSession().save(message);
			logger.debug("Add push message task {} {}", id, message.toString());
			return id.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "-1";
		}
	}

	public List<Preview> getPreview(String type) {
		String table = "article";
		if(type.contains("ARTICLE")){
			table = "article";
		}
		String sql = String.format("select pushMessageTask.id as id ,title ,cover from pushMessageTask , article where %s.id = SUBSTRING_INDEX(pushMessageTask.msg,';',1) order by pushMessageTask.id desc", table,table);
		@SuppressWarnings("unchecked")
		List<Preview> previews = sessionFactory
				.getCurrentSession()
				.createSQLQuery(sql)
				.setResultTransformer(
						Transformers.aliasToBean(Preview.class)).list();
		return previews;
	}


}
