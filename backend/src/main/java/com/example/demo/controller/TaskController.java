package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<Task> l = myService.getAllTasks();
		long maxid=0;
		for (Task task :l) {
			if(maxid<task.getId()) {
				maxid = task.getId();
			}
		}
		newTask.setId(maxid+1);
		newTask.setUpdatedTime();
		return ResponseEntity.status(HttpStatus.CREATED).body(myService.addNewTask(newTask));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Task> deleteTaskbyId(@PathVariable int id) {
		Task task = myService.deleteATask(id);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(task);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	public Object updateTaskById(@PathVariable int id, @RequestBody Task task) {
		Task task1 = myService.update(new Task(id, task.getContent()));
		if (task1 == null) {
			return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(task1);
		}
	}

}
