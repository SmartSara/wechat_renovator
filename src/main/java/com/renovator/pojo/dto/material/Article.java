package com.renovator.pojo.dto.material;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name = "article")
@SelectBeforeUpdate
@DynamicUpdate
public class Article {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "title")
	private String title;

	@Column(name="cover")
	private String cover;

	@Column(name = "content", columnDefinition = "CLOB NOT NULL")
	private String content;

	@Column(name = "ts")
	private Date ts;

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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", cover="
				+ cover + ", content=" + content + ", ts="
				+ ts + "]";
	}

}
