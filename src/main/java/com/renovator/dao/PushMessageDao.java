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

import com.renovator.pojo.dto.NotificationType;
import com.renovator.pojo.dto.Preview;
import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.pojo.dto.material.Article;
import com.renovator.util.MaterialType;

/**
 * Created by darlingtld on 2015/4/3.
 */
@Repository
public class PushMessageDao {

    private static final Logger logger = LoggerFactory.getLogger(PushMessageDao.class);

    @Autowired
    private SessionFactory      sessionFactory;

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
        String sql = String.format("select pushMessageTask.id as id ,title ,cover from pushMessageTask , article where %s.id = SUBSTRING_INDEX(pushMessageTask.msg,',',1) order by pushMessageTask.id desc",
                                   table, table);
        @SuppressWarnings("unchecked")
        List<Preview> previews = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Preview.class)).list();
        return previews;
    }

    public List<Preview> getPreview(String type, int pushMessageTaskId) {

        PushMessageTask pushMessageTask = (PushMessageTask) sessionFactory.getCurrentSession().get(PushMessageTask.class,
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
        List<Preview> previews = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Preview.class)).list();
        return previews;
    }

    public String delArticleInPushMessage(int pushMessageTaskId, int articleId) {

        PushMessageTask task = (PushMessageTask) sessionFactory.getCurrentSession().get(PushMessageTask.class,
                                                                                        pushMessageTaskId);

        String msg = task.getMsg();
        if (msg.equals("" + articleId)) {
            sessionFactory.getCurrentSession().delete(task);
            return "del";
        } else {
            String regex = String.format("^%1$s,|,%1$s$|(?<=,)%1$s,", articleId);
            String newMsg = msg.replaceAll(regex, "");
            task.setMsg(newMsg);
            sessionFactory.getCurrentSession().saveOrUpdate(task);
            return "ok";
        }

    }

    public Object deletePushMessage(String type, int pushMessageTaskId) {

        sessionFactory.getCurrentSession().createQuery("delete PushMessageTask where id = ?").setParameter(0,
                                                                                                           pushMessageTaskId).executeUpdate();

        return "ok";
    }

    public Object updatePushMessageTaskMsgs(int pushMessageTaskId, String msgs) {
        sessionFactory.getCurrentSession().createQuery("update PushMessageTask set msg = ? where id = ?").setParameter(0,
                                                                                                                       msgs).setParameter(1,
                                                                                                                                          pushMessageTaskId).executeUpdate();
        return "ok";
    }

    public List<Article> getArticles(NotificationType type) {

        PushMessageTask pushMessageTask = (PushMessageTask) sessionFactory.getCurrentSession().createQuery("from PushMessageTask where type = ? order by scheduled_time,id desc").setFirstResult(0).setMaxResults(1).setString(0,
                                                                                                                                                                                                                               type.toString()).uniqueResult();
        String sql = String.format("select id ,title ,cover,content,ts from article where id in (%1$s) order by field(id,%1$s) ", pushMessageTask.getMsg());
        List<Article> results = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Article.class)).list();
        return results;
    }

}
