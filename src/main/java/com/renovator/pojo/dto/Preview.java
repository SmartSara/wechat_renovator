package com.renovator.pojo.dto;

import java.util.Date;



public class Preview {

	int id;

	String title;

	String cover;
	
	Date scheduledTime;
	
	String status;

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
    
    public Date getScheduledTime() {
        return scheduledTime;
    }
    
    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
	

}
