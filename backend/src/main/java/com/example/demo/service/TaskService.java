package com.example.demo.service;


import java.util.List;

import com.example.demo.model.Task;
import com.example.demo.tools.DataTools;

public class TaskService {
	DataTools dataTools = new DataTools();
	
	List<Task> getAllTasks(){
		return dataTools.readTasksFromFile();
	}
}
