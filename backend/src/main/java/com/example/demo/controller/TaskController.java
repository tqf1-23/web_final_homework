package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	public TaskService myService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getAllTasks() {
		List<Task> l = myService.getAllTasks();
		return l.isEmpty() ? new String("No Task on list") : l;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Task> addATask(@RequestBody Task newTask) {
		return ResponseEntity.status(HttpStatus.CREATED).body(myService.addNewTask(newTask));
	}
	
	
}
