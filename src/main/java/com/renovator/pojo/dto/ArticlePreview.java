package com.renovator.pojo.dto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;



public class ArticlePreview {
	
	int id ;
	
	String title;
	
	byte []  cover;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
//	public File getCover() {
//		File coverFile  = new File(title);
//		try {
//			IOUtils.copy(new ByteArrayInputStream(cover), new FileOutputStream(coverFile));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return coverFile;
//		
//	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}
	
	
	
	

}
