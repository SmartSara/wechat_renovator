package com.renovator.pojo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.renovator.util.CustomDateSerializer;

@Entity
@Table(name = "pushmessagetask")
public class PushMessageTask {	
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "type")
	private String type;

	@Column(name = "msg")
	private String msg;
	
    @JsonSerialize(using=CustomDateSerializer.class)
	@Column(name = "scheduled_time")
	private Date scheduledTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", type=" + type + ", msg=" + msg
				+ ", scheduledTime=" + scheduledTime + "]";
	}
	
	

}
