package com.example.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.model.Task;
import com.example.demo.tools.DataTools;

public class TaskService {
	DataTools dataTools = new DataTools();
	
	List<Task> getAllTasks(){
		return dataTools.readTasksFromFile();
	}

	public void addNewTask(Task newTask) {
        List<Task> tasks = dataTools.readTasksFromFile();
        tasks.add(newTask);
        dataTools.writeTasksToFile(tasks);
	}
	public void deleteATask(long id) {
        List<Task> tasks = dataTools.readTasksFromFile();
        for(Task task:tasks) {
        	if(task.getId()==id) {
        		tasks.remove(task);
        		dataTools.writeTasksToFile(tasks);
        		return;
        	}
        }
	}

	public void update(Task task) {
	}
}
