package com.renovator.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;
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
		if (type.contains("ARTICLE")) {
			table = "article";
		}
		String sql = String
				.format("select pushMessageTask.id as id ,title ,cover from pushMessageTask , article where %s.id = SUBSTRING_INDEX(pushMessageTask.msg,',',1) order by pushMessageTask.id desc",
						table, table);
		@SuppressWarnings("unchecked")
		List<Preview> previews = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(Preview.class))
				.list();
		return previews;
	}

	public List<Preview> getPreview(String type, int pushMessageTaskId) {

		PushMessageTask pushMessageTask = (PushMessageTask) sessionFactory
				.getCurrentSession().get(PushMessageTask.class,
						pushMessageTaskId);

		String msg = pushMessageTask.getMsg();

		// String inStr = msg.substring(msg.indexOf(",")+1);

		String table = "article";
		if (type.contains("ARTICLE")) {
			table = "article";
		}
		String sql = "select id ,title ,cover from ${table} where id in (${in}) order by field(id,${in})";

		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("table", table);
		valueMap.put("in", msg);
		sql = StrSubstitutor.replace(sql, valueMap);
		@SuppressWarnings("unchecked")
		List<Preview> previews = sessionFactory.getCurrentSession()
				.createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(Preview.class))
				.list();
		return previews;
	}

	public String delArticleInPushMessage(int pushMessageTaskId, int articleId) {
		
		PushMessageTask task = (PushMessageTask) sessionFactory
				.getCurrentSession().get(PushMessageTask.class,
						pushMessageTaskId);

		String msg = task.getMsg();
		if (msg.equals(""+articleId)) {
			sessionFactory.getCurrentSession().delete(task);
			return "del";
		} else {
			String regex = String
					.format("^%1$s,|,%1$s$|(?<=,)%1$s,", articleId);
			String newMsg = msg.replaceAll(regex, "");
			task.setMsg(newMsg);
			sessionFactory.getCurrentSession().saveOrUpdate(task);
			return "ok";
		}

	}

	public Object deletePushMessage(String type, int pushMessageTaskId) {
		
		 sessionFactory.getCurrentSession().createQuery("delete PushMessageTask where id = ?").setParameter(0, pushMessageTaskId).executeUpdate();
		 
		 return "ok";
	}

}
