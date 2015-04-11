package com.renovator.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.renovator.dao.MaterialDao;
import com.renovator.pojo.Product;
import com.renovator.pojo.Service;
import com.renovator.pojo.User;
import com.renovator.pojo.dto.PushMessageTask;
import com.renovator.pojo.dto.material.Article;
import com.renovator.util.PropertyHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class DaoTests {

	@Autowired
	MaterialDao materialDao;
	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	protected WebApplicationContext wac;
	
	@Test
	@Transactional
	public void saveMaterialTest(){
		
		
		PushMessageTask material = new PushMessageTask();
		material.setMsg("1");
		material.setScheduledTime(new Date());
		material.setType("article");
		
		String id = materialDao.saveMaterial(material);
		
		
	}

	@Test
	@Transactional
	public void getArticleTest() throws Exception {
		List<Article> articles = materialDao.getArticles();

		File to = new File("C:\\Users\\wade\\Desktop\\test.jpg");
		for (Article article : articles) {
			
			System.out.println("content ==============");
			System.out.println(article.getContent());
			System.out.println("cover save as ==============");
//			Files.write(article.getCover(), to);
			
		}
	}

}
