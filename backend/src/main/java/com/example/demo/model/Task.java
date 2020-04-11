package com.example.demo.model;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Task {
    private long ID;
    private String content;
    private Date updatedTime;
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    public Task() {
    }
    
    public Task(long id, String content) {
        this.ID = id;
        this.content = content;
        this.updatedTime = new Date();
    }
    public Task(long id, String content,String date) {
        this.ID = id;
        this.content = content;
        try {
			this.updatedTime = ft.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    public void setId(long id) {
    	this.ID =  id;
    }
    public long getId() {
        return ID;
    }

    public String getContent() {
        return content;
    }

    public String getUpdatedTime() {
        return ft.format(updatedTime);
    }

    public void setUpdatedTime() {
        this.updatedTime = new Date();
    }
    public void setUpdatedTime(String updatedTime) {
    	
        try {
			this.updatedTime = ft.parse(updatedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }


    public void setContent(String content) {
        this.content = content;
    }

	@Override
	public String toString() {
		return ID+","+content+","+updatedTime;
	}
    

}