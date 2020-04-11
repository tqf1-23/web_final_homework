package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.tools.DataTools;
@Service
public class TaskService {
	DataTools dataTools = new DataTools();
	
	public List<Task> getAllTasks(){
		return dataTools.readTasksFromFile();
	}

	public Task addNewTask(Task newTask) {
        List<Task> tasks = dataTools.readTasksFromFile();
        tasks.add(newTask);
        dataTools.writeTasksToFile(tasks);
        return newTask;
	}
	public Task deleteATask(long id) {
        List<Task> tasks = dataTools.readTasksFromFile();
        for(Task task:tasks) {
        	if(task.getId()==id) {
        		tasks.remove(task);
        		dataTools.writeTasksToFile(tasks);
        		return task;
        	}
        }
        return null;
	}

	public Task update(Task task) {
		Task returnTask = null;
		return returnTask;
	}
}
