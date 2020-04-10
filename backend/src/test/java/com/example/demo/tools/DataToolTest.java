package com.example.demo.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Task;

public class DataToolTest {
	@Autowired
	private DataTools dataTools = new DataTools();

	@AfterEach
	void tearDown() {
		dataTools.writeTasksToFile(Arrays.asList(new Task(1L, "test","2020-04-01 00:00:01")));
	}

	@Test
	public void shouldReadTasks() throws IOException {
		List<Task> tasks = dataTools.readTasksFromFile();
		assertEquals(1, tasks.size());
		assertEquals(1L, tasks.get(0).getId());
		assertEquals("test", tasks.get(0).getContent());
		assertEquals("2020-04-01 00:00:01", tasks.get(0).getUpdatedTime());
	}

	@Test
	public void shouldWriteTasks() throws IOException {
		List<Task> tasks = Arrays.asList(new Task(1, "task1"), new Task(2, "task2"));
		dataTools.writeTasksToFile(tasks);
		List<Task> tasksRead = dataTools.readTasksFromFile();
		assertEquals(2, tasksRead.size());
		assertEquals(1L, tasksRead.get(0).getId());
		assertEquals(2L, tasksRead.get(1).getId());
		assertEquals("task1", tasksRead.get(0).getContent());
		assertEquals("task2", tasksRead.get(1).getContent());

	}
}
