package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Task;
import com.example.demo.tools.DataTools;
//@SpringBootTest
public class TaskServiceTest {
	@Mock
	private DataTools dataTools;
	
	@InjectMocks
	private TaskService taskService = new TaskService();
	List<Task> tasks;
	public TaskServiceTest() {
		MockitoAnnotations.initMocks(this);
	}
    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
    }
	@Test
	public void shouldGetAllTasks() {
		when(dataTools.readTasksFromFile()).thenReturn(tasks);

		List<Task> all = taskService.getAllTasks();

		assertEquals(tasks, all);
	}
	@Test
	public void shouldAddATask() {
		when(dataTools.readTasksFromFile()).thenReturn(tasks);
		int tasks1 = taskService.getAllTasks().size();
		Task newTask = new Task(1,"sleep");
		taskService.addNewTask(newTask);
		int tasks2 = taskService.getAllTasks().size();
		assertEquals(tasks1+1, tasks2);
        verify(dataTools).writeTasksToFile(any());
	}
}
