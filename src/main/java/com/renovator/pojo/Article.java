package com.renovator.pojo;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "title")
	private String title;

	@Column(name = "cover", columnDefinition = "BLOB NOT NULL")
	private byte[] cover;

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

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
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
		return "Article [id=" + id + ", title=" + title + ", cover.size="
				+ cover.length + ", content=" + content + ", ts="
				+ ts + "]";
	}

}
