package com.example.demo.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Task;

@Service
public class DataTools {
	/**
	 * 工具类，用于将Task的列表存入文件和从文件中读取Task列表
	 */
	
	@Value("${todo.tools.filename}")
	private String pathname;
	
	public String getPathname() {
		return pathname;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	public DataTools(String pathname) {
		this.pathname = pathname;
	}

	public DataTools() {
	}

	public List<Task> readTasksFromFile() {
		/* 从文件读取数据到字符串 */
		String jsonString = null;
		try {
			jsonString = FileUtils.readFileToString(new File(pathname), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonString);
		/* 从字符串解析出Task列表 */
		List<Task> tasks = JSON.parseArray(jsonString, Task.class);
		return tasks;
	}
	public int writeTasksToFile(List<Task> tasks) {
		/* 将Task列表转化成Json字符串 */
		String jsonString = JSON.toJSONString(tasks);
		/* 从字符串解析出Task列表 */
		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter(new File(pathname));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			fWriter.write(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}
}
